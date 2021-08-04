package com.zhulong.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.zhulong.common.utils.AppInfoUtil;
import com.zhulong.library_base.bean.AppDeviceInfo;
import com.zhulong.library_base.utils.RxDeviceTool;
import com.zhulong.main.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_splash);
        initDevice();
        new Handler().postDelayed(() -> {
          /*  if (MmkvHelper.getInstance().getMmkv().decodeBool("first",true)){
                //startActivity(new Intent(this, GuideActivity.class));
            }else {
                MainActivity.start(this);
            }*/
            MainActivity.start(SplashActivity.this);
            finish();

        },1000);
    }

    private void initDevice() {
        AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
        float screenDensity = RxDeviceTool.getScreenDensity(this);
        appDeviceInfo.setScreenDensity(screenDensity);
        int screenHeight = RxDeviceTool.getScreenHeight(this);
        appDeviceInfo.setScreenHeight(screenHeight);
        int screenWidth = RxDeviceTool.getScreenWidth(this);
        appDeviceInfo.setScreenWidth(screenWidth);
        String imei = RxDeviceTool.getIMEI(this);
        appDeviceInfo.setImei(imei);
        String deviceIdIMEI = RxDeviceTool.getDeviceIdIMEI(this);
        appDeviceInfo.setDeviceIdIMEI(deviceIdIMEI);
        String androidId = RxDeviceTool.getAndroidId(this);
        appDeviceInfo.setAndroidId(androidId);
        String macAddress = RxDeviceTool.getMacAddress(this);
        appDeviceInfo.setMacAddress(macAddress);
        String appVersionName = RxDeviceTool.getAppVersionName(this);
        appDeviceInfo.setAppVersionName(appVersionName);
        int appVersionNo = RxDeviceTool.getAppVersionNo(this);
        appDeviceInfo.setAppVersionNo(appVersionNo);
        //设备厂商
        String deviceName = RxDeviceTool.getBuildMANUFACTURER();
        appDeviceInfo.setDeviceName(deviceName);
        //唯一用户id
        String deviceId = RxDeviceTool.getDeviceIdIMEI(this);
        appDeviceInfo.setDeviceId(deviceId);
        appDeviceInfo.setAndroidVersionCode(RxDeviceTool.getSystem());

       /* //唯一标识序列号
        int i = MdidSdkHelper.InitSdk(getApplicationContext(), true, (b, idSupplier) -> {
            //根方法中,我们如果只需要oaid,则只获取oaid即可
            appDeviceInfo.setOaid(idSupplier.getOAID());

        });*/
        AppInfoUtil.getInstance().setDevice(appDeviceInfo);
    }
}