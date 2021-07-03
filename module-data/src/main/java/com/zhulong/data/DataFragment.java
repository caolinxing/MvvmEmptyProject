package com.zhulong.data;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.data.databinding.DataFragmentDataBinding;
import com.zhulong.library_base.fragment.MvvmLazyFragment;
import com.zhulong.library_base.viewmodel.IMvvmBaseViewModel;

/**
 * 应用模块: data
 * <p>
 * 类描述: 资料-fragment
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Data.PAGER_DATA)
public class DataFragment extends MvvmLazyFragment<DataFragmentDataBinding, IMvvmBaseViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.data_fragment_data;
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