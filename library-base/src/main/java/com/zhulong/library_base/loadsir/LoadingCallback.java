package com.zhulong.library_base.loadsir;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.zhulong.library_base.R;

/**
 * 应用模块:
 * <p>
 * 类描述: 等待加载
 * <p>
 */
public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.base_layout_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
