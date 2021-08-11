package com.zhulong.data.view.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.data.BR;
import com.zhulong.data.R;
import com.zhulong.data.application.DataViewModelFactory;
import com.zhulong.data.databinding.DataFragmentDataBinding;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

/**
 * 应用模块: Data
 * <p>
 * 类描述: 资料-fragment
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Data.PAGER_DATA)
public class DataFragment extends BaseFragment<DataFragmentDataBinding, DataViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.data_fragment_data;
    }
    @Override
    public DataViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        DataViewModelFactory factory = DataViewModelFactory.getInstance(requireActivity().getApplication(), new DataModel());
        return new ViewModelProvider(this, factory).get(DataViewModel.class);

    }
    @Override
    public int initVariableId() {
        return BR.dataViewModel;
    }
}