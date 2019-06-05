package com.lcz.mua.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lcz.mua.R;
import com.lcz.mua.base.BaseActivity;
import com.lcz.mua.util.FingerprintHandler;
import com.lcz.mua.util.SecurityUtil;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class FingerActivity extends BaseActivity {
    @BindView(R.id.mSCPW)
    SwitchCompat mSCPW;
    @BindView(R.id.mActionPW)
    RelativeLayout mActionPW;
    @BindView(R.id.mSCFinger)
    SwitchCompat mSCFinger;
    @BindView(R.id.mActionFinger)
    RelativeLayout mActionFinger;

    private FingerprintHandler fingerprintHandler;


    @Override
    protected int initViewId() {
        return R.layout.acitivity_finger;
    }


    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintHandler = new FingerprintHandler(this, null);
            if (fingerprintHandler.isFingerprintSupported()) {
                mActionFinger.setVisibility(View.VISIBLE);
            } else {
                mActionFinger.setVisibility(View.GONE);
            }
        } else {
            mActionFinger.setVisibility(View.GONE);
        }

        /** - SWITCHES - **/
        /** - ACTIVE SECURITY - **/
        mSCPW.setChecked(SecurityUtil.isPasswordSet());
        mSCPW.setClickable(false);

        /** - FINGERPRINT - **/
        mSCFinger.setChecked(Hawk.get("fingerprint_security", false));
        mSCFinger.setClickable(false);
    }

    @Override
    protected void process(Bundle savedInstanceState) {

    }


    @OnClick({R.id.mActionPW, R.id.mActionFinger})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mActionPW:
                mSCPW.setChecked(!mSCPW.isChecked());
                if (mSCPW.isChecked()) setPasswordDialog();
                else {
                    SecurityUtil.clearPassword();
                    mSCFinger.setChecked(false);
                    SecurityUtil.setFingerprintUnlock(mSCFinger.isChecked());
                }
                toggleEnabledChild(mSCPW.isChecked());
                break;
            case R.id.mActionFinger:
                mSCFinger.setChecked(!mSCFinger.isChecked());
                SecurityUtil.setFingerprintUnlock(mSCFinger.isChecked());
                break;

        }
    }

    private void setPasswordDialog() {
        AlertDialog.Builder passwordDialog = new AlertDialog.Builder(mContext);
        View rootView = getLayoutInflater().inflate(R.layout.model_dialog_set_password, null);
        final TextView passwordDialogTitle = (TextView) rootView.findViewById(R.id.password_dialog_title);
        final CardView passwordDialogCard = (CardView) rootView.findViewById(R.id.password_dialog_card);
        final EditText editTextPassword = (EditText) rootView.findViewById(R.id.password_edittxt);
        final EditText editTextConfirmPassword = (EditText) rootView.findViewById(R.id.confirm_password_edittxt);
        passwordDialog.setView(rootView);

        AlertDialog dialog = passwordDialog.create();
        dialog.setCancelable(false);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.cancel).toUpperCase(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toggleResetSecurity();
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.ok_action).toUpperCase(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editTextPassword.length() > 3) {
                    if (editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())) {
                        if (SecurityUtil.setPassword(editTextPassword.getText().toString())) {
                            mSCPW.setChecked(true);
                            toggleEnabledChild(true);
                            Toast.makeText(getApplicationContext(), R.string.remember_password_message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, R.string.error_contact_developer, Toast.LENGTH_SHORT).show();
                            toggleResetSecurity();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.password_dont_match, Toast.LENGTH_SHORT).show();
                        toggleResetSecurity();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.error_password_length, Toast.LENGTH_SHORT).show();
                    toggleResetSecurity();
                }
            }
        });
        dialog.show();
    }


    private void toggleResetSecurity (){
        mSCPW.setChecked(false);
        toggleEnabledChild(mSCPW.isChecked());
        SecurityUtil.clearPassword();
    }


    private void toggleEnabledChild(boolean enable) {
        findViewById(R.id.mSCPW).setClickable(enable);
    }
}
