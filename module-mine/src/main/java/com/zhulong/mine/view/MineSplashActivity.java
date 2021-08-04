package com.zhulong.mine.view;

import android.os.Bundle;
import android.view.View;

import com.zhulong.common.utils.AppInfoUtil;
import com.zhulong.library_base.bean.AppDeviceInfo;
import com.zhulong.library_base.mvvm.base_view.BaseActivity;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;
import com.zhulong.library_base.utils.RxDeviceTool;
import com.zhulong.mine.R;
import com.zhulong.mine.databinding.MineActivitySplashBinding;
import com.zhulong.mine.view.login.LoginActivity;
import com.zhulong.mine.view.mine.MineFragment;

/**
 * 应用模块:
 * <p>
 * 类描述: 用户中心入口只供Model单独运行
 * <p>
 *
 * @since: clx
 * @date: 2021/7/29
 */
public class MineSplashActivity extends BaseActivity<MineActivitySplashBinding, BaseViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.mine_activity_splash;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public void initData() {
        initDevice();
        startContainerActivity(MineFragment.class.getCanonicalName());
        binding.btnGoCenter.setOnClickListener(v -> startContainerActivity(MineFragment.class.getCanonicalName()));
        binding.btnGoLogin.setOnClickListener(v -> startActivity(LoginActivity.class));
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