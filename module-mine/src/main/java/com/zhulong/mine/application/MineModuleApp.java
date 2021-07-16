package com.zhulong.mine.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.zhulong.common.adapter.ScreenAutoAdapter;
import com.zhulong.common.config.ModuleLifecycleConfig;
import com.zhulong.library_base.BuildConfig;
import com.zhulong.library_base.base.BaseApplication;

import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * /**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @Author: clx
 * @CreateDate: 2021/7/6 17:32
 */
public class MineModuleApp extends BaseApplication {

    private static MineModuleApp mineModuleApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mineModuleApp = this;
        setsDebug(BuildConfig.DEBUG);
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.v("MineModuleApp");

    }
    public static MineModuleApp getInstance() {
        return mineModuleApp;
    }

}
