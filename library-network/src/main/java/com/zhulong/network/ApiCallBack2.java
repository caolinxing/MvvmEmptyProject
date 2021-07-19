package com.zhulong.network;



import com.zhulong.network.interceptor.ApiException;
import com.zhulong.network.util.GsonUtils;
import com.zhulong.network.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class ApiCallBack2<M> implements Observer<M> {

    private String wrongMsg;

    public abstract void onSuccess(M result);

    public abstract void onFail(int code, String wrongMsg, String result);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(M modelBean) {

        String json = GsonUtils.toJson(modelBean);
        try {
            JSONObject jsonObject = new JSONObject(JSONTokener(json));
            if (jsonObject.getInt("errNo") != 0) {
                String msg = "没有msg字段";
                if (jsonObject.has("msg")) {
                    msg = jsonObject.getString("msg");
                }
                onFail(jsonObject.getInt("errNo"), msg, json);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        onSuccess(modelBean);
    }

    public String JSONTokener(String in) {
        // consume an optional byte order mark (BOM) if it exists
        if (in != null && in.startsWith("\ufeff")) {
            in = in.substring(1);
        }
        return in;
    }

    @Override
    public void onError(Throwable e) {
        int code = 9999;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            code = httpException.code();
            if (code == 500 || code == 404) {
                wrongMsg = "服务器出错!";
            }
        } else if (e instanceof ConnectException) {
            wrongMsg = "网络断开,请打开网络!";
        } else if (e instanceof SocketTimeoutException) {
            wrongMsg = "网络连接超时!";
        } else if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            code = apiException.getErrorCode();
            wrongMsg = "发生未知错误:" + apiException.getMsg();
        } else {
            LogUtil.v("接口错误：" + e.getMessage());
        }
        onFail(code, wrongMsg, "");
    }

    @Override
    public void onComplete() {

    }
}
