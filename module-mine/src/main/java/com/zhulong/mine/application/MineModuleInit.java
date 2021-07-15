package com.zhulong.mine.application;

import com.zhulong.common.IModuleInit;
import com.zhulong.library_base.base.BaseApplication;

/**
 * /**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @Author: clx
 * @CreateDate: 2021/7/3 15:59
 */
public class MineModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(BaseApplication application) {
        return false;
    }

    @Override
    public boolean onInitLow(BaseApplication application) {
        return false;
    }
}
