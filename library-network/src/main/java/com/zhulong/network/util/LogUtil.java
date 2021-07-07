package com.zhulong.network.util;

import android.text.TextUtils;

import com.zhulong.network.BuildConfig;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;


public class LogUtil {

    protected static String TAG = "ZHU_LONG:";

    /**
     * the log disable lock when is true, the log is disabled, or the log is
     * enabled.
     */
    private static boolean disable = false;

    /**
     * the constructor
     */
    public static void canLog(String tag, boolean isLog) {
        if (!TextUtils.isEmpty(tag)) {
            TAG = tag;
        }
        disable = isLog;

    }

    /**
     * build the complete message
     *
     * @param message
     * @return
     */
    @NonNull
    private static String buildMessage(String message) {
        //		StackTraceElement ste = new Throwable().fillInStackTrace()
        //				.getStackTrace()[2];

        StringBuffer sb = new StringBuffer();
        sb.append(message);
        //		sb.append("\naddress: ");
        //		sb.append(ste.getClassName());
        //		sb.append(".");
        //		sb.append(ste.getMethodName());
        //		sb.append("()");
        return sb.toString();
    }

    /**
     * check if the log is disabled
     *
     * @return
     */
    private static boolean isEnabled() {
        if (BuildConfig.DEBUG) {
            disable = true;
        } else {
            disable = false;
        }
        return disable;
    }

    /**
     * send a VERBOSE log message.
     *
     * @param msg
     */
    public static void v(String msg) {
        if (!isEnabled()) {
            return;
        }
        v(TAG, buildMessage(msg));
    }

    /**
     * send a VERBOSE log message.
     *
     * @param msg
     */
    public static void v(@Nullable String tag, @Nullable String msg) {
        if (!isEnabled()) {
            return;
        }
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0) {
            return;
        }

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            android.util.Log.v(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                android.util.Log.v(tag, logContent);
            }
            android.util.Log.v(tag, msg);
        }
    }

    /**
     * send a VERBOSE log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void v(String msg, Throwable throwable) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.v(TAG, buildMessage(msg), throwable);
    }

    /**
     * send a DEBUG log message
     *
     * @param msg
     */
    public static void d(String msg) {
        if (!isEnabled()) {
            return;
        }
        d(TAG, buildMessage(msg));
    }

    /**
     * send a DEBUG log message
     *
     * @param msg
     */
    public static void d(String tag, @NonNull String msg) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.d(tag, msg);
    }

    /**
     * send a DEBUG log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void d(String msg, Throwable throwable) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.d(TAG, buildMessage(msg), throwable);
    }

    /**
     * send a INFO log message
     *
     * @param msg
     */
    public static void i(String msg) {
        if (!isEnabled()) {
            return;
        }
        i(TAG, buildMessage(msg));
    }

    /**
     * send a INFO log message
     *
     * @param msg
     */
    public static void i(String tag, @NonNull String msg) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.i(tag, msg);
    }

    /**
     * send a INFO log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void i(String msg, Throwable throwable) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.i(TAG, buildMessage(msg), throwable);
    }

    /**
     * send a ERROR log message
     *
     * @param msg
     */
    public static void e(String msg) {
        if (!isEnabled()) {
            return;
        }
        e(TAG, buildMessage(msg));

    }

    /**
     * send a ERROR log message
     *
     * @param msg
     */
    public static void e(String tag, @NonNull String msg) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.e(tag, msg);
    }

    /**
     * send a ERROR log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void e(String msg, Throwable throwable) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.e(TAG, buildMessage(msg), throwable);
    }

    /**
     * send a WARN log message
     *
     * @param msg
     */
    public static void w(String msg) {
        if (!isEnabled()) {
            return;
        }
        w(TAG, buildMessage(msg));
    }

    /**
     * send a WARN log message
     *
     * @param msg
     */
    public static void w(String tag, @NonNull String msg) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.w(tag, msg);
    }

    /**
     * send a WARN log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void w(String msg, Throwable throwable) {
        if (!isEnabled()) {
            return;
        }
        android.util.Log.w(TAG, buildMessage(msg), throwable);
    }
}

