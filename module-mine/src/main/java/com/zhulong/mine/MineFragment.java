package com.zhulong.mine;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.library_base.fragment.MvvmLazyFragment;
import com.zhulong.library_base.viewmodel.IMvvmBaseViewModel;
import com.zhulong.mine.databinding.MineFragmentMineBinding;

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
public class MineFragment extends MvvmLazyFragment<MineFragmentMineBinding, IMvvmBaseViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_mine;
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