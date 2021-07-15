package com.zhulong.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;
import com.zhulong.mine.BR;
import com.zhulong.mine.R;
import com.zhulong.mine.databinding.MineFragmentMineBinding;

import androidx.annotation.Nullable;

/**
 * 应用模块: Mine
 * <p>
 * 类描述: 个人中心-fragment
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Mine.PAGER_MINE)
public class MineFragment extends BaseFragment<MineFragmentMineBinding, MineViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.mine_fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.mineViewModel;
    }
}