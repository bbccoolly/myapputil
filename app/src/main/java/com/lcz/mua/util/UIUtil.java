package com.lcz.mua.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.lcz.mua.base.App;

import java.util.List;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class UIUtil {
    public static Context getContext() {
        return App.getContext();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static float getDimen(int resId) {
        return getResources().getDimension(resId);
    }

    public static Handler getMainHandler() {
        return App.getMainHandler();
    }

    public static long getMainThreadId() {
        return App.getMainThreadId();
    }

    public static void post(Runnable task) {
        int myTid = android.os.Process.myTid();

        if (myTid == getMainThreadId()) {
            // 在主线程中执行的
            task.run();
        } else {
            // 在子线程中执行的
            getMainHandler().post(task);
        }
    }

    /**
     * 执行延时任务
     */
    public static void postDelayed(Runnable task, int delayed) {
        getMainHandler().postDelayed(task, delayed);
    }

    /**
     * 移除任务
     */
    public static void removeCallbacks(Runnable task) {
        getMainHandler().removeCallbacks(task);
    }

    @NonNull
    public static String getString(@StringRes int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    public static boolean isTopActivity(Activity activity) {
        String packageName = activity.getComponentName().getClassName();
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            System.out.println("---------------包名-----------" + tasksInfo.get(0).topActivity.getClassName());
            //应用程序位于堆栈的顶层
            if (packageName.equals(tasksInfo.get(0).topActivity.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public static String getTopActivityName(Context context) {
        String topActivityClassName = null;
        ActivityManager activityManager =
                (ActivityManager) (context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            topActivityClassName = f.getClassName();
        }
        return topActivityClassName;
    }
}
