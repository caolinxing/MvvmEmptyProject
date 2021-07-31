package com.zhulong.mine.view;

import android.os.Bundle;

import com.zhulong.library_base.mvvm.base_view.BaseActivity;
import com.zhulong.mine.R;
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
public class MineSplashActivity extends BaseActivity {


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
        startContainerActivity(MineFragment.class.getCanonicalName());
    }
}