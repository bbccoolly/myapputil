package com.lcz.mua.util;

import android.util.Log;

import com.lcz.mua.BuildConfig;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class Trace {

    /**
     * 日志开关 true:打开; false：关闭
     */
    public static final boolean IS_DEBUG = BuildConfig.DEBUGABLE; //true;

    private static Trace logger = null;

    private static final String PREFIX_NAME = "bsb_   ";

    private Trace() {
    }

    public static Trace getTracer() {
        if (logger == null) {
            logger = new Trace();
        }
        return logger;
    }

    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            return "[第" + st.getLineNumber() + "行]";
        }
        return null;
    }

    public void v(String tag, Object o) {
        if (!IS_DEBUG)
            return;
        String name = getFunctionName();
        if (name != null) {
            Log.v(PREFIX_NAME + tag, name + " - " + o);
        } else {
            Log.v(PREFIX_NAME + tag, o.toString());
        }
    }

    public void d(String tag, Object o) {
        if (!IS_DEBUG)
            return;
        String name = getFunctionName();
        if (name != null) {
            Log.d(PREFIX_NAME + tag, name + " - " + o);
        } else {
            Log.d(PREFIX_NAME + tag, o.toString());
        }
    }

    public void i(String tag, Object o) {
        if (!IS_DEBUG) {
            return;
        }
        String name = getFunctionName();
        if (name != null) {
            Log.i(PREFIX_NAME + tag, name + "-" + o);
        } else {
            Log.i(PREFIX_NAME + tag, o.toString());
        }
    }

    public void w(String tag, Object o) {
        if (!IS_DEBUG) {
            return;
        }
        String name = getFunctionName();
        if (name != null) {
            Log.w(PREFIX_NAME + tag, name + " - " + o);
        } else {
            Log.w(PREFIX_NAME + tag, o.toString());
        }
    }

    public void e(String tag, Object o) {
        if (!IS_DEBUG)
            return;
        String name = getFunctionName();
        if (name != null) {
            Log.e(PREFIX_NAME + tag, name + " - " + o);
        } else {
            Log.e(PREFIX_NAME + tag, o.toString());
        }

    }

}
