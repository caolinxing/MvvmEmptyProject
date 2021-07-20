package com.zhulong.vip.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;
import com.zhulong.vip.BR;
import com.zhulong.vip.R;
import com.zhulong.vip.databinding.VipFragmentVipBinding;

import androidx.annotation.Nullable;

/**
 * 应用模块: vip
 * <p>
 * 类描述: Vip-fragment
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Vip.PAGER_VIP)
public class VipFragment extends BaseFragment<VipFragmentVipBinding, VipViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.vip_fragment_vip;
    }

    @Override
    public int initVariableId() {
        return BR.vipViewModel;
    }
}
