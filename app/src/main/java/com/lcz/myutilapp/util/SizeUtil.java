package com.lcz.myutilapp.util;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class SizeUtil {
    public static int dp2px(float dpValue) {
        final float scale = UIUtil.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = UIUtil.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
