package com.zhulong.network.api;


public interface OkHttpCallBack<M> {
    void onSuccess(M bean);
    void onFail(int errorCode, String wrongMsg);
}
