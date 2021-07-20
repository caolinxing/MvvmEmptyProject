package com.zhulong.network.api;

import com.zhulong.network.config.ApiConfig;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

public interface ApiService_Bbs_Api {

    @Headers({DOMAIN_NAME_HEADER + ApiConfig.ApiName.BASE_URL_BBS_NAME})
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    @GET("openapi/thread/getfeedback")
    Observable<Object> GetFeedback(@QueryMap Map<String,String> params);

}
