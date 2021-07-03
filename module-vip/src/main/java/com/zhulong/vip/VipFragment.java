package com.zhulong.vip;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.library_base.fragment.MvvmLazyFragment;
import com.zhulong.library_base.viewmodel.IMvvmBaseViewModel;
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
public class VipFragment extends MvvmLazyFragment<VipFragmentVipBinding, IMvvmBaseViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.vip_fragment_vip;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
    }

    private void initView() {

    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected IMvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected void onRetryBtnClick() {

    }
}
