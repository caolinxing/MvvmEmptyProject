package com.zhulong.empty;


import com.orhanobut.logger.Logger;
import com.youth.banner.util.LogUtils;
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
 * @CreateDate: 2021/6/28 9:55
 */
public class MyAppcation extends BaseApplication {
    @Override
    public void onCreate()
    {
        super.onCreate();
        Logger.v("MyAppcation");
        setsDebug(BuildConfig.DEBUG);
        // 初始化需要初始化的组件
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}
