package com.zhulong.mine.view.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;
import com.zhulong.mine.BR;
import com.zhulong.mine.R;
import com.zhulong.mine.databinding.MineFragmentMineBinding;
import com.zhulong.mine.view.login.LoginViewModel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

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
    public void initData() {
        super.initData();
    }

    @Override
    public MineViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        return new ViewModelProvider(this).get(MineViewModel.class);

    }

    @Override
    public int initVariableId() {
        return BR.mineViewModel;
    }
}