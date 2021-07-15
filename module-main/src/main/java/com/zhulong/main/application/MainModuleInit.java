package com.zhulong.main.application;

import com.blankj.utilcode.util.Utils;
import com.kingja.loadsir.core.LoadSir;
import com.orhanobut.logger.Logger;
import com.zhulong.common.IModuleInit;
import com.zhulong.common.adapter.ScreenAutoAdapter;
import com.zhulong.library_base.base.BaseApplication;
import com.zhulong.library_base.loadsir.EmptyCallback;
import com.zhulong.library_base.loadsir.ErrorCallback;
import com.zhulong.library_base.loadsir.LoadingCallback;
import com.zhulong.library_base.loadsir.TimeoutCallback;

/**
 *
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 * @Author: clx
 * @CreateDate: 2021/7/3 15:59
 */
public class MainModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(BaseApplication application) {
        ScreenAutoAdapter.setup(application);
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new TimeoutCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
        Utils.init(application);
        Logger.i("main组件初始化完成 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(BaseApplication application) {
        return false;
    }
}
