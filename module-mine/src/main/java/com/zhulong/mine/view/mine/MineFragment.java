package com.zhulong.mine.view.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jaeger.library.StatusBarUtil;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;
import com.zhulong.mine.BR;
import com.zhulong.mine.R;
import com.zhulong.mine.application.MineViewModelFactory;
import com.zhulong.mine.databinding.MineFragmentMineBinding;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
    public void onResume() {
        super.onResume();
        viewModel.loginAfterUpdate();
        setStatusBar(true);
    }

    @Override
    public void setMenuVisibility(boolean isVisibleToUser) {
        super.setMenuVisibility(isVisibleToUser);
        setStatusBar(isVisibleToUser);
    }

    private void setStatusBar(boolean isVisibleToUser) {
        if (viewModel != null) {
            if (isVisibleToUser) {
                if (viewModel.isVip.get() == 1) {
                    StatusBarUtil.setColor(requireActivity(), ContextCompat.getColor(requireActivity(), R.color.base_color_7e6b5a), 0);
                } else {
                    StatusBarUtil.setColor(requireActivity(), ContextCompat.getColor(requireActivity(), R.color.base_color_2a292e), 0);
                }
            } else {
                StatusBarUtil.setColor(requireActivity(), ContextCompat.getColor(requireActivity(), R.color.base_color_2a292e), 0);
            }
        }
    }


    @Override
    public void initData() {
        super.initData();
        viewModel.getUserHeader();
    }

    @Override
    public MineViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        MineViewModelFactory factory = MineViewModelFactory.getInstance(requireActivity().getApplication(), new MineModel<>());
        return new ViewModelProvider(this, factory).get(MineViewModel.class);

    }

    @Override
    public int initVariableId() {
        return BR.mineViewModel;
    }
}