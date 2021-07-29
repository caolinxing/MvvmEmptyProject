package com.zhulong.common.utils;

import com.tencent.mmkv.MMKV;
import com.zhulong.library_base.bean.AppDeviceInfo;
import com.zhulong.library_base.config.BaseConfig;
import com.zhulong.library_base.utils.GsonUtils;

/**
 * 应用模块:
 * <p>
 * 类描述: App 信息工具类
 * <p>
 *
 * @since: clx
 * @date: 2021/7/28
 */
public class AppInfoUtil {
    public volatile static AppInfoUtil mInstance= null;

    public static AppInfoUtil getInstance() {
        if (mInstance==null){
            synchronized (AppInfoUtil.class){
                if (mInstance==null){
                    mInstance = new AppInfoUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取设备信息，如果设备信息没有，将从本地读取
     *
     * @return
     */
    public AppDeviceInfo getDevice() {
        String json = MMKV.defaultMMKV().getString(BaseConfig.KeyConfig.KEY_DEVICE_INFO,null);
        if (json!=null){
            AppDeviceInfo appDeviceInfo = GsonUtils.fromLocalJson(json, AppDeviceInfo.class);
            return appDeviceInfo;
        }
        return null;
    }
    public void setDevice(AppDeviceInfo device) {
        String json_app_info = GsonUtils.toJson(device);
        MMKV.defaultMMKV().putString(BaseConfig.KeyConfig.KEY_DEVICE_INFO,json_app_info);
    }

}
