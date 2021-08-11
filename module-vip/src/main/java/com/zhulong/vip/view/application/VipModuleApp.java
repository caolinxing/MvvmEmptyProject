package com.zhulong.vip.view.application;

import com.orhanobut.logger.Logger;
import com.zhulong.common.config.ModuleLifecycleConfig;
import com.zhulong.library_base.BuildConfig;
import com.zhulong.library_base.base.BaseApplication;

/**
 * /**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @Author: clx
 * @CreateDate: 2021/7/6 17:32
 */
public class VipModuleApp extends BaseApplication {

    private static VipModuleApp vipModuleApp;

    @Override
    public void onCreate() {
        super.onCreate();
        vipModuleApp = this;
        setDebug(BuildConfig.DEBUG);
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        Logger.v("MineModuleApp");

    }
    public static VipModuleApp getInstance() {
        return vipModuleApp;
    }

}
