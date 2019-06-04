package com.lcz.myutilapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lcz.myutilapp.R;
import com.lcz.myutilapp.base.App;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class ToastUtil {
    private static Toast sToast;
    private static Toast sMidToast;

    @SuppressLint("ShowToast")
    public static void show(CharSequence content) {
        if (null == sToast) {
            synchronized (ToastUtil.class) {
                if (null == sToast) {
                    sToast = Toast.makeText(App.getContext(), "", Toast.LENGTH_SHORT);
                }
            }
        }
        sToast.setText(content);
        sToast.show();
    }

    @SuppressLint("ShowToast")
    public static void show(int contentId) {
        if (null == sToast) {
            synchronized (ToastUtil.class) {
                if (null == sToast) {
                    sToast = Toast.makeText(App.getContext(), "", Toast.LENGTH_SHORT);
                }
            }
        }
        sToast.setText(contentId);
        sToast.show();
    }

    public static void showMiddle(String content) {
        sMidToast = createToast(App.getContext(), content);
        sMidToast.setDuration(Toast.LENGTH_SHORT);
        sMidToast.show();
    }

    private static Toast createToast(Context context, String content) {
        Toast tempToast;
        View layout = View.inflate(context, R.layout.model_view_toast, null);
        TextView tv_title = (TextView) layout.findViewById(R.id.mMsgLabel);
        if (!TextUtils.isEmpty(content)) {
            tv_title.setText(content);
            tv_title.setVisibility(View.VISIBLE);
        }

        tempToast = new Toast(context.getApplicationContext());
        tempToast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 528);
        tempToast.setDuration(Toast.LENGTH_LONG);
        tempToast.setView(layout);
        return tempToast;
    }
}
