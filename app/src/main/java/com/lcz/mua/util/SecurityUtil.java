package com.lcz.mua.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.lcz.mua.R;
import com.lcz.mua.widget.themed.ThemedIcon;
import com.orhanobut.hawk.Hawk;

import java.security.MessageDigest;

/**
 * desc TODO
 * Created by lcz on 2019/5/17.
 */
public class SecurityUtil {

    public static void setPasswordOnDelete(boolean passwordOnDelete) {
        Hawk.put("password_on_delete", passwordOnDelete);
    }

    public static void setPasswordOnHidden(boolean passwordOnHidden) {
        Hawk.put("password_on_hidden", passwordOnHidden);
    }

    public static void setFingerprintUnlock(boolean passwordOnHidden) {
        Hawk.put("fingerprint_security", passwordOnHidden);
    }

    public static boolean isPasswordSet() {
        return Hawk.get("password_hash", null) != null;
    }

    public static boolean isPasswordOnHidden() {
        return Hawk.get("password_hash", null) != null && Hawk.get("password_on_hidden", false);
    }

    public static boolean isFingerprintUsed() {
        return Hawk.get("fingerprint_security", false);
    }

    public static boolean isPasswordOnDelete() {
        return Hawk.get("password_hash", null) != null && Hawk.get("password_on_delete", false);
    }

    private static boolean checkPassword(String pass) {
        return sha256(pass).equals(Hawk.get("password_hash", null));
    }

    public static boolean setPassword(String newValue) {
        return Hawk.put("password_hash", sha256(newValue));
    }

    public static boolean clearPassword() {
        return Hawk.delete("password_hash");
    }
    public static void authenticateUser(Activity activity, AuthCallBack passwordInterface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        CancellationSignal mCancellationSignal = new CancellationSignal();

        final View view = activity.getLayoutInflater().inflate(R.layout.model_dialog_password, null);
        final TextView passwordDialogTitle = view.findViewById(R.id.password_dialog_title);
        final CardView passwordDialogCard = view.findViewById(R.id.password_dialog_card);
        final EditText editTextPassword = view.findViewById(R.id.password_edittxt);
        final ThemedIcon fingerprintIcon = view.findViewById(R.id.fingerprint_icon);

        builder.setView(view);

        builder.setPositiveButton(activity.getString(R.string.ok_action).toUpperCase(), (dialog, which) -> {
            // NOTE: set this empty, later will be overwrite to avoid the dismiss
        });

        builder.setNegativeButton(activity.getString(R.string.cancel).toUpperCase(), (dialog, which) -> hideKeyboard(activity, editTextPassword.getWindowToken()));

        AlertDialog dialog = builder.create();
        dialog.show();
        showKeyboard(activity);
        editTextPassword.requestFocus();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && SecurityUtil.isFingerprintUsed()) {
            FingerprintHandler fingerprintHandler = new FingerprintHandler(activity, mCancellationSignal);
            if (fingerprintHandler.isFingerprintSupported()) {
                fingerprintHandler.setOnFingerprintResult(new FingerprintHandler.CallBack() {
                    @Override
                    public void onSuccess() {
                        hideKeyboard(activity, editTextPassword.getWindowToken());
                        dialog.dismiss();
                        passwordInterface.onAuthenticated();
                    }

                    @Override
                    public void onError(String s) {
                        // TODO: 9/9/17 siplaymessage
                    }
                });

                fingerprintHandler.startAuth();
            } else {
                fingerprintIcon.setVisibility(View.GONE);
            }
        } else {
            fingerprintIcon.setVisibility(View.GONE);
        }

        dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            if (checkPassword(editTextPassword.getText().toString())) {
                hideKeyboard(activity, editTextPassword.getWindowToken());
                mCancellationSignal.cancel();
                dialog.dismiss();
                passwordInterface.onAuthenticated();
            } else {
                editTextPassword.getText().clear();
                editTextPassword.requestFocus();
                passwordInterface.onError();
            }
        });
    }


    private static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private static void hideKeyboard(Context context, IBinder token) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(token, 0);
    }

    private static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public interface AuthCallBack {
        void onAuthenticated();

        void onError();
    }
}
