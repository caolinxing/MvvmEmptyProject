package com.zhulong.vip.view;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.library_base.mvvm.base_view.BaseActivity;
import com.zhulong.vip.BR;
import com.zhulong.vip.R;
import com.zhulong.vip.databinding.VipFragmentVipBinding;

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
public class VipFragment extends BaseActivity<VipFragmentVipBinding, VipViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.vip_fragment_vip;
    }

    @Override
    public int initVariableId() {
        return BR.vipViewModel;
    }
}
