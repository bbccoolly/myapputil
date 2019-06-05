package com.lcz.mua.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class VersionUtil {

    /**
     * 获取版本号
     */
    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        int versionCode = 0;
        try {
            PackageInfo info = pm.getPackageInfo(context
                    .getApplicationContext().getPackageName(), 0);

            versionCode = info.versionCode;

        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return versionCode;
    }

    /**
     * 获取版本名
     */
    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = pm.getPackageInfo(context
                    .getApplicationContext().getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return versionName;
    }

}
