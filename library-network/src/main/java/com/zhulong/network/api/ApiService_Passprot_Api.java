package com.zhulong.network.api;


import com.zhulong.network.BaseResponse;
import com.zhulong.network.bean.mine.login.PersonHeaderBean;
import com.zhulong.network.bean.mine.login.ZlLoginBean;
import com.zhulong.network.config.ApiConfig;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

public interface ApiService_Passprot_Api {
    //账号登录
    @Headers({DOMAIN_NAME_HEADER + ApiConfig.ApiName.BASE_URL_PASSPORT_NAME})
    @FormUrlEncoded
    @POST("openapi/user/userLoginNewAuth")
    Observable<BaseResponse<ZlLoginBean>> loginZl(@FieldMap Map<String, String> params, @Field("apiSecret") String apiSecret);

    //获取用户头信息（PersonHeader）
    @Headers({DOMAIN_NAME_HEADER + ApiConfig.ApiName.BASE_URL_PASSPORT_NAME})
    @FormUrlEncoded
    @POST("api/getUserHeaderForMobile")
    Observable<BaseResponse<PersonHeaderBean>> getUserHeaderForMobile(@FieldMap Map<String, String> map, @Field("apiSecret") String apiSecret);

}


