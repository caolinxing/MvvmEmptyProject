package com.zhulong.mine.application;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.zhulong.common.adapter.ScreenAutoAdapter;

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
public class ModuleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAutoAdapter.setup(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.v("UserModuleApp");
    }
}
