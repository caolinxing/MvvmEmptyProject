package com.zhulong.network;

import android.util.Log;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.zhulong.network.api.ApiService_Passprot_Api;
import com.zhulong.network.bean.mine.login.PersonHeaderBean;
import com.zhulong.network.bean.mine.login.ZlLoginBean;
import com.zhulong.network.cache.CacheProvider;
import com.zhulong.network.config.DeviceInfo;
import com.zhulong.network.config.NetWorkKeyConfig;
import com.zhulong.network.interceptor.PersistenceCookieJar;
import com.zhulong.network.interceptor.TokenHeaderInterceptor;
import com.zhulong.network.util.LogUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zhulong.network.config.ApiConfig.ApiUrl.BASE_EDU_URL;

public class RetrofitUtil {

    //private final ApiService_Edu_Api ApiServiceEduApi;
    private final ApiService_Passprot_Api ApiServicePassprotApi;
   // private final ApiService_Bbs_Api ApiServiceBbsApi;
    private String baseUrl = "";
    private final String TAG = "RetrofitUtil";
    private Retrofit mRetrofit;
    private OkHttpClient.Builder mOkHttpClient;
    public static volatile RetrofitUtil instance = null;
    private static final int READ_TIMEOUT = 15;//读取超时时间,单位秒
    private static final int CONN_TIMEOUT = 15;//连接超时时间,单位秒
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private static File mCacheDir;
    private final CacheProvider mCacheProvider;

    public static void init(File cacheDir){
        mCacheDir = cacheDir;
        if (mCacheDir==null){
            LogUtil.e("PRETTY_LOGGER","RxCache初始化失败");
        }else {
            LogUtil.v("PRETTY_LOGGER","RxCache初始化成功");
        }
    }

    public RetrofitUtil() {
        //添加公参
        HashMap<String, String> publicParams = new HashMap<>();

        DeviceInfo device = getDevice();
        if (device!=null){
            publicParams.put("devices", device.getDeviceName()+"");
            publicParams.put("system", device.getAndroidVersionCode()+"");
            publicParams.put("version", device.getAppVersionName()+"");
            publicParams.put("unique_id", device.getDeviceId()+"");
        }
        publicParams.put("appid", "301");
        publicParams.put("client_id", "205");
        //ApiUtils.Openapi
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        this.mOkHttpClient = new OkHttpClient().newBuilder();
        //信任所有服务器地址
        //创建管理器
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] x509Certificates,
                    String s) throws java.security.cert.CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] x509Certificates,
                    String s) throws java.security.cert.CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        }};
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            //为OkHttpClient设置sslSocketFactory
            mOkHttpClient.sslSocketFactory(sslContext.getSocketFactory());

        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpClient build = RetrofitUrlManager.getInstance()
                .with(mOkHttpClient)
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new TokenHeaderInterceptor(publicParams))
                .addInterceptor(httpLoggingInterceptor)
                .cookieJar(new PersistenceCookieJar())
                .build();
        /*ApiServiceBbsApi = new RxCache.Builder()
                .persistence(mCacheDir, new GsonSpeaker())
                .using(ApiService_Bbs_Api.class);
        ApiServiceEduApi = new RxCache.Builder()
                .persistence(mCacheDir, new GsonSpeaker())
                .using(ApiService_Edu_Api.class);
        ApiServicePassprotApi = new RxCache.Builder()
                .persistence(mCacheDir, new GsonSpeaker())
                .using(ApiService_Passprot_Api.class);*/
        mRetrofit = new Retrofit.Builder()
                .client(build)
                .baseUrl(BASE_EDU_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //ApiServiceEduApi = mRetrofit.create(ApiService_Edu_Api.class);
        //ApiServicePassprotApi = mRetrofit.create(ApiService_Passprot_Api.class);
        mCacheProvider = new RxCache.Builder()
                .persistence(mCacheDir, new GsonSpeaker())
                .using(CacheProvider.class);
        //ApiServiceBbsApi = mRetrofit.create(ApiService_Bbs_Api.class);
        ApiServicePassprotApi = mRetrofit.create(ApiService_Passprot_Api.class);

    }

/*
    public ApiService_Edu_Api getEduApi() {
        return ApiServiceEduApi;
    }*/


    public ApiService_Passprot_Api getPassportApi() {
        return ApiServicePassprotApi;
    }

   /* public ApiService_Bbs_Api getBbsApi(){
        return ApiServiceBbsApi;
    }

*/
    public static RetrofitUtil getInstance(){
        if (instance == null) {
            synchronized (RetrofitUtil.class) {
                if (instance == null) {
                    instance = new RetrofitUtil();
                }
            }
        }
        return instance;
    }

    public <T> T createService(final Class<T> service) {
        validateServiceInterface(service);//校验接口合法性
        return mRetrofit.create(service);
    }

    private <T> void validateServiceInterface(Class<T> service) {
        if (service == null) {
            Log.i(TAG, "validateServiceInterface: " + "服务接口不能为空！");
        }
        assert service != null;
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {// 用来判定该 Interface 是否继承其他 Interface
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }
    public DeviceInfo getDevice() {
        String json = MMKV.defaultMMKV().getString(NetWorkKeyConfig.KEY_DEVICE_INFO,null);
        if (json!=null){
            DeviceInfo appDeviceInfo = new Gson().fromJson(json,DeviceInfo.class);
            return appDeviceInfo;
        }
        return null;
    }

    /**---------------接口请求----------------------------接口请求--------------------------------------接口请求--------------------------------------------**/
    //真正进行数据请求和缓存的接口
    public Observable<Reply<BaseResponse<ZlLoginBean>>> loginZl(Map<String,String> params) {
        return mCacheProvider.loginZl(ApiServicePassprotApi.loginZl(params,NetWorkKeyConfig.PASSPORT_SECRECT_KEY),new EvictProvider(true));
    }
    //真正进行数据请求和缓存的接口
    public Observable<Reply<BaseResponse<PersonHeaderBean>>> getUserHeader(Map<String,String> params) {
        return mCacheProvider.getUserHeaderForMobile(ApiServicePassprotApi.getUserHeaderForMobile(params,NetWorkKeyConfig.PASSPORT_SECRECT_KEY),new EvictProvider(true));
    }

}
