package com.zhulong.mine.view.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.jaeger.library.StatusBarUtil;
import com.tencent.mmkv.MMKV;
import com.zhulong.common.router.RouterActivityPath;
import com.zhulong.common.utils.AppInfoUtil;
import com.zhulong.library_base.bean.AppDeviceInfo;
import com.zhulong.library_base.mvvm.base_view.BaseActivity;
import com.zhulong.library_base.utils.RxDeviceTool;
import com.zhulong.mine.BR;
import com.zhulong.mine.R;
import com.zhulong.mine.application.MineViewModelFactory;
import com.zhulong.mine.config.MineConfig;
import com.zhulong.mine.databinding.MineActivityLoginBinding;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
/**
 * 应用模块:
 * <p>
 * 类描述: 登录
 * <p>
 *
 * @since: clx
 * @date: 2021/7/29
 */
@Route(path = RouterActivityPath.Mine.PAGER_MINE_LOGIN)
public class LoginActivity extends BaseActivity<MineActivityLoginBinding, LoginViewModel> {

    @Override
    protected void onStart() {
        super.onStart();
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.base_color_f4),0);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.mine_activity_login;
    }

    @Override
    public LoginViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        MineViewModelFactory factory = MineViewModelFactory.getInstance(getApplication(),new LoginModel<>());
        return new ViewModelProvider(this, factory).get(LoginViewModel.class);
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

    @Override
    public void initData() {
        boolean isAgree = MMKV.defaultMMKV().getBoolean(MineConfig.KeyConfig.KEY_IS_AGREE_AGREEMENT, false);
        binding.loginIvCheckBoxPrivacy.setSelected(isAgree);
        initDevice();
    }

    @Override
    public int initVariableId() {
        return BR.loginViewModel;
    }

    @Override
    public void initViewObservable() {
        //密码明/密文切换
        LoginViewModel.UIChangeObservable.pSwitchEvent.observe(this, aBoolean -> {
            //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
            if (aBoolean) {
                //密码可见
                binding.ivShowEye.setSelected(true);
                binding.loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//显示
            } else {
                binding.ivShowEye.setSelected(false);
                binding.loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏
            }
            //光标移动到最后
            binding.loginPassword.setSelection(binding.loginPassword.getText().length());

        });
        //协议勾选
        LoginViewModel.UIChangeObservable.checkedAgreementSwitchEvent.observe(this, aBoolean -> {
            boolean isSelect = !binding.loginIvCheckBoxPrivacy.isSelected();
            binding.loginIvCheckBoxPrivacy.setSelected(isSelect);
            MMKV.defaultMMKV().putBoolean(MineConfig.KeyConfig.KEY_IS_AGREE_AGREEMENT, isSelect);
        });
        binding.loginTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    binding.linAccountLogin.setVisibility(View.VISIBLE);
                    binding.linCodeLogin.setVisibility(View.GONE);
                } else {
                    binding.linAccountLogin.setVisibility(View.GONE);
                    binding.linCodeLogin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}