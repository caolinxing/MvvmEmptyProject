package com.zhulong.network.config;

import java.io.Serializable;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @since: clx
 * @date: 2021/7/7
 */
public class DeviceInfo implements Serializable {
    private String deviceName;
    //屏幕宽高
    private int screenWidth;
    private int screenHeight;
    private String deviceId;
    private String user_agent;// agent
    private String imei;
    private String deviceIdIMEI;
    private String androidId;
    private String macAddress;
    private String appVersionName;
    private int appVersionNo;
    //序列号
    private String oAid;
    private String androidVersionCode;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDeviceIdIMEI() {
        return deviceIdIMEI;
    }

    public void setDeviceIdIMEI(String deviceIdIMEI) {
        this.deviceIdIMEI = deviceIdIMEI;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public int getAppVersionNo() {
        return appVersionNo;
    }

    public void setAppVersionNo(int appVersionNo) {
        this.appVersionNo = appVersionNo;
    }

    public String getoAid() {
        return oAid;
    }

    public void setoAid(String oAid) {
        this.oAid = oAid;
    }

    public String getAndroidVersionCode() {
        return androidVersionCode;
    }

    public void setAndroidVersionCode(String androidVersionCode) {
        this.androidVersionCode = androidVersionCode;
    }
}
