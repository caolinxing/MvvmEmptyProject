package com.zhulong.common;


import android.annotation.SuppressLint;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;
import com.zhulong.common.adapter.ScreenAutoAdapter;
import com.zhulong.library_base.base.BaseApplication;
import com.zhulong.network.RetrofitUtil;
import com.zhulong.network.config.ApiConfig;

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

import androidx.annotation.Nullable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * 应用模块:
 * <p>
 * 类描述: 通用库 & 基础库 自身初始化操作
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-25
 */
public class CommonModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(BaseApplication application) {
        // 初始化日志
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return application.isDebug();
            }
        });
        if (application.isDebug()) {
            ARouter.openLog(); // 开启日志
            ARouter.openDebug(); // 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
        }
        ScreenAutoAdapter.setup(application);
        RetrofitUtil.init(application.getFilesDir());
        RetrofitUtil.init(application.getFilesDir());
        ARouter.init(application);
        MMKV.initialize(application);
        handleSSLHandshake1();
        handleSSLHandshake();
        Logger.v("基础层初始化完毕 -- onInitAhead");
        initBaseUrl();
        return false;
    }

    public void initBaseUrl() {
        RetrofitUrlManager.getInstance().clearAllDomain();
        RetrofitUrlManager.getInstance().putDomain(ApiConfig.ApiName.BASE_URL_EDU_NAME, ApiConfig.ApiUrl.BASE_EDU_URL);
        RetrofitUrlManager.getInstance().putDomain(ApiConfig.ApiName.BASE_URL_PASSPORT_NAME, ApiConfig.ApiUrl.BASE_PASS_PORT);
        RetrofitUrlManager.getInstance().putDomain(ApiConfig.ApiName.BASE_URL_BBS_NAME, ApiConfig.ApiUrl.BASE_BBS_URL);
        RetrofitUrlManager.getInstance().putDomain(ApiConfig.ApiName.BASE_URL_THIRD_LOGIN_WX, ApiConfig.ApiUrl.BASE_LOGIN_WX);
        RetrofitUrlManager.getInstance().putDomain(ApiConfig.ApiName.BASE_URL_F_NAME, ApiConfig.ApiUrl.BASE_F_URL);
    }

    @Override
    public boolean onInitLow(BaseApplication application) {
        return false;
    }


    public static void handleSSLHandshake1() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
    /**
     * 忽略证书校验
     */
    public static SSLSocketFactory handleSSLHandshake() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception ignored) {
        }
        return sSLSocketFactory;
    }

    public static class TrustAllManager implements X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
