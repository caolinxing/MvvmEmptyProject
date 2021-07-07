package com.zhulong.network.api;

import com.zhulong.network.ApiConfig;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

public interface ApiService_Bbs_Api {
    @Headers({DOMAIN_NAME_HEADER + ApiConfig.ApiName.BASE_URL_BBS_NAME})
    @GET("openapi/thread/getfeedback")
    Observable<Object> GetFeedback(@QueryMap Map<String,String> params);

}
