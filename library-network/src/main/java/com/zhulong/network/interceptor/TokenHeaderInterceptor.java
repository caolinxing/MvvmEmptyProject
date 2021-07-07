package com.zhulong.network.interceptor;

import android.text.TextUtils;

import com.zhulong.network.config.CookieBean;
import com.zhulong.network.util.Md5Util;
import com.zhulong.network.util.NetWorkUtil;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class TokenHeaderInterceptor implements Interceptor {
    //初始化Map对象
    Map<String, String> paramMap;

    public TokenHeaderInterceptor(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        CookieBean cookieInfo = NetWorkUtil.getInstance().getCookieInfo();
        //拿到原来的request
        Request oldRequest = chain.request();
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
        ;
        //拿到请求的url
        String url = oldRequest.url().toString();
        //判断是GET还是POST请求
        if (oldRequest.method().equalsIgnoreCase("GET")) {
            if (paramMap != null && paramMap.size() > 0) {
                StringBuilder urlBuilder = new StringBuilder(url);
                //拼接公共请求参数
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    urlBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
                }
                if (TextUtils.isEmpty(cookieInfo.getUid())) {
                    urlBuilder.append("&uid" + "=" + "0");
                } else {
                    urlBuilder.append("&uid" + "=" + cookieInfo.getUid());
                }
                String time = String.valueOf(NetWorkUtil.getCurrentTime());
                urlBuilder.append("&time" + "=" + time);
                url = urlBuilder.toString();
                //如果之前的url没有？号，我们需要手动给他添加一个？号
                if (!url.contains("?")) {
                    url = url.replaceFirst("&", "?");
                }

                //依据原来的request构造一个新的request,
                Request request = requestBuilder
                        .url(url)
                        .build();
                return chain.proceed(request);
            }
        } else {
            String apiSecret = "";
            if (paramMap != null && paramMap.size() > 0) {
                RequestBody body = oldRequest.body();
                if (body != null) {
                    if (body instanceof FormBody) {
                        FormBody formBody = (FormBody) body;
                        //1.把原来的的body里面的参数添加到新的body中
                        FormBody.Builder builder = new FormBody.Builder();
                        //为了防止重复添加相同的key和value
                        Map<String, String> temMap = new HashMap<>();
                        for (int i = 0; i < formBody.size(); i++) {
                            if (TextUtils.equals(formBody.name(i), "apiSecret")) {
                                apiSecret = formBody.value(i);

                            } else {
                                builder.add(formBody.name(i), formBody.value(i));
                                temMap.put(formBody.name(i), formBody.value(i));
                            }
                        }
                        //2.把公共请求参数添加到新的body中
                        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                            if (!temMap.containsKey(entry.getKey())) {
                                builder.add(entry.getKey(), entry.getValue());
                            }
                        }
                        String functionName = url.substring(url.lastIndexOf("/")).replace("/", "");
                        String replace = functionName.replace("\"", "");
                        if (TextUtils.isEmpty(cookieInfo.getUid())) {
                            builder.add("uid", "0");
                        } else {
                            builder.add("uid", cookieInfo.getUid());
                        }
                        String time = String.valueOf(NetWorkUtil.getCurrentTime());
                        builder.add("time", time);
                        builder.add("token", getToken(apiSecret, functionName, time));

                        //LogUtil.v("functionName: " + functionName);

                        FormBody newFormBody = builder.build();
                        //依据原来的request构造一个新的request,
                        Request newRequest = requestBuilder
                                .post(newFormBody)
                                .build();
                        return chain.proceed(newRequest);
                    } else if (body instanceof MultipartBody) {
                        MultipartBody formBody = (MultipartBody) body;

                        //1.把原来的的body里面的参数添加到新的body中
                        MultipartBody.Builder builder = new MultipartBody.Builder();
                        builder.setType(MultipartBody.FORM);
                        for (MultipartBody.Part part : formBody.parts()) {
                            RequestBody body1 = part.body();
                            Headers headers = part.headers();
                            if (headers != null && headers.size() > 0) {
                                String[] split = headers.value(0).replace(" ", "").replace("\"", "").split(";");
                                if (split.length == 2) {
                                    //文本
                                    String[] keys = split[1].split("=");
                                    if (keys.length > 1 && body1.contentLength() < 1024) {
                                        String key = keys[1];
                                        if (key.equals("apiSecret")) {
                                            Buffer buffer = new Buffer();
                                            part.body().writeTo(buffer);
                                            apiSecret = buffer.readUtf8().replaceAll("\"", "");
                                        }
                                        if (!key.equals("apiSecret")) {
                                            builder.addPart(part);
                                        }
                                    }
                                } else {
                                    builder.addPart(part);
                                }
                            }
                        }

                        //2.把公共请求参数添加到新的body中
                        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                            builder.addFormDataPart(entry.getKey(), entry.getValue());
                        }

                        String functionName = url.substring(url.lastIndexOf("/")).replace("/", "");

                        if (TextUtils.isEmpty(cookieInfo.getUid())) {
                            builder.addFormDataPart("uid", "0");
                        } else {
                            builder.addFormDataPart("uid",cookieInfo.getUid());
                        }
                        String time = String.valueOf(NetWorkUtil.getCurrentTime());
                        builder.addFormDataPart("time", time);

                        builder.addFormDataPart("token", getToken(apiSecret, functionName, time));
                        MultipartBody newMultipartBody = builder.build();
                        //依据原来的request构造一个新的request,
                        Request newRequest = requestBuilder
                                .post(newMultipartBody)
                                .build();
                        return chain.proceed(newRequest);
                    }
                }
            }
        }
        return chain.proceed(oldRequest);
    }

    public String getToken(String secrectKey, String functionName, String time) {
        String token = Md5Util.getStringMd5(new StringBuilder().append(paramMap.get("appid")).append(secrectKey).append(time).append(functionName).append(NetWorkUtil.getInstance().getCookieInfo().getUid()).toString());
        return token;
    }
}
