package com.lcz.myutilapp.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    private static App instance;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Looper mainLooper;
    private static Handler mainHandler;
    private static Thread mainThread;
    private static long mainThreadId;

    public static synchronized App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static Looper getMainThreadLooper() {
        return mainLooper;
    }

    public static Handler getMainHandler() {
        return mainHandler;
    }

    public static long getMainThreadId() {
        return mainThreadId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        mainThread = Thread.currentThread();
        mainLooper = getMainLooper();
        mainHandler = new Handler();
        mainThreadId = android.os.Process.myTid();
    }
}
