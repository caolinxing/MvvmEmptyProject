package com.zhulong.data.view.data_group_new;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.data.BR;
import com.zhulong.data.R;
import com.zhulong.data.application.DataViewModelFactory;
import com.zhulong.data.databinding.DataFragmentDataBinding;
import com.zhulong.data.view.IDataContractView;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

/**
 * 应用模块: Data
 * <p>
 * 类描述: 最新精华
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Data.PAGER_DATA_GroupNew)
public class DataGroupNewFragment extends BaseFragment<DataFragmentDataBinding, DataGroupNewViewModel> implements IDataContractView.IGroupNew.IView {

    public static DataGroupNewFragment newInstance() {
        return new DataGroupNewFragment();
    }

    public static DataGroupNewFragment newInstance(int type) {
        final DataGroupNewFragment fragment = new DataGroupNewFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.data_fragment_group_new;
    }

    @Override
    public DataGroupNewViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        DataViewModelFactory factory = DataViewModelFactory.getInstance(requireActivity().getApplication(), new DataGroupNewModel());
        return new ViewModelProvider(this, factory).get(DataGroupNewViewModel.class);

    }

    @Override
    public int initVariableId() {
        return BR.dataViewModel;
    }


}