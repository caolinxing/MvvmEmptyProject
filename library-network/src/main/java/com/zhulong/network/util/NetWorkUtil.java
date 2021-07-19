package com.zhulong.network.util;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.zhulong.network.config.CookieBean;
import com.zhulong.network.config.NetWorkKeyConfig;

import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @since: clx
 * @date: 2021/7/7
 */
public class NetWorkUtil {
    public volatile static NetWorkUtil mInstance;
    private final List<Cookie> cookieList = new ArrayList<>();
    public NetWorkUtil() {
    }
    public static NetWorkUtil getInstance(){
        if (mInstance==null){
            synchronized (NetWorkUtil.class){
                if (mInstance==null){
                    mInstance = new NetWorkUtil();
                }
            }
        }
        return mInstance;
    }

    public List<Cookie> getCookieList() {
        return cookieList;
    }

    public CookieBean getCookieInfo() {
        String json = MMKV.defaultMMKV().getString(NetWorkKeyConfig.KEY_COOKIE,null);
        if (json!=null){
            CookieBean cookieBean = new Gson().fromJson(json,CookieBean.class);
            return cookieBean;
        }
        return new CookieBean();
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

}
