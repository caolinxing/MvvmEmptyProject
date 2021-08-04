package com.zhulong.network.interceptor;


import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.zhulong.network.config.CookieBean;
import com.zhulong.network.config.NetWorkKeyConfig;
import com.zhulong.network.util.NetWorkUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class PersistenceCookieJar implements CookieJar {
    final HashMap<String, ArrayList<Cookie>> cache = new HashMap<>();

    //Http请求结束，Response中有Cookie时候回调
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        //内存中缓存Cookie
    }

    //Http发送请求前回调，Request中设置Cookie
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        //过期的Cookie
        ArrayList<Cookie> invalidCookies = new ArrayList<>();
        //有效的Cookie
        ArrayList<Cookie> validCookies = new ArrayList<>();
        ArrayList<Cookie> cookies = cache.get("cookie");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null) {
                    if (cookie.expiresAt() < System.currentTimeMillis()) {
                        //判断是否过期
                        invalidCookies.add(cookie);
                    } else {
                        validCookies.add(cookie);
                    }
                }
            }
        }
        CookieBean cookieBean = NetWorkUtil.getInstance().getCookieInfo();

        if (cookieBean != null) {
            for (int i = 0; i < 7; i++) {
                Cookie.Builder builder = new Cookie
                        .Builder();
                switch (i) {
                    case 0:
                        if (cookieBean.getUid()!=null){
                            validCookies.add(builder
                                    .name("uid")
                                    .domain("zhulong.com")
                                    .value(cookieBean.getUid())
                                    .path("/")
                                    .build());
                        }
                        break;
                    case 1:
                        if (cookieBean.getUsername()!=null) {
                            try {
                                validCookies.add(builder.name("username")
                                        .value(URLEncoder.encode(cookieBean.getUsername(), "UTF-8"))
                                        .domain("zhulong.com")
                                        .path("/")
                                        .build());
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 2:
                        if (cookieBean.getIs_corp()!=null) {
                            validCookies.add(builder.name("isCorp")
                                    .value(cookieBean.getIs_corp())
                                    .domain("zhulong.com")
                                    .path("/")
                                    .build());
                            break;
                        }
                    case 3:
                        if (cookieBean.getPcid()!=null) {
                            validCookies.add(builder.name("pcid")
                                    .value(cookieBean.getPcid())
                                    .domain("zhulong.com")
                                    .path("/")
                                    .build());
                        }
                        break;
                    case 4:
                        if (cookieBean.getSpecialty_id()!=null) {
                            validCookies.add(builder.name("specialty_id")
                                    .value(cookieBean.getSpecialty_id())
                                    .domain("zhulong.com")
                                    .path("/")
                                    .build());
                        }
                        break;
                    case 5:
                        if (cookieBean.getZlid() != null) {
                            validCookies.add(builder.name("ZLID")
                                    .value(cookieBean.getZlid())
                                    .domain("zhulong.com")
                                    .path("/")
                                    .build());
                        }
                    default:
                        break;
                }
            }
        }
        if (cookies!=null){
            //缓存中移除过期的Cookie
            if (invalidCookies.size() > 0) {
                //cookies.removeAll(invalidCookies);
            }
            cache.put(url.host(), validCookies);
            NetWorkUtil.getInstance().getCookieList().clear();
            NetWorkUtil.getInstance().getCookieList().addAll(validCookies);
        }
        return validCookies;
    }




}