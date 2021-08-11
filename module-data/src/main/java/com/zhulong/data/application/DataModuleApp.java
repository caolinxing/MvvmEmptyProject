package com.zhulong.data.application;

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
public class DataModuleApp extends BaseApplication {

    private static DataModuleApp dataModuleApp;

    @Override
    public void onCreate() {
        super.onCreate();
        dataModuleApp = this;
        setDebug(BuildConfig.DEBUG);
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        Logger.v("MineModuleApp");

    }
    public static DataModuleApp getInstance() {
        return dataModuleApp;
    }

}
