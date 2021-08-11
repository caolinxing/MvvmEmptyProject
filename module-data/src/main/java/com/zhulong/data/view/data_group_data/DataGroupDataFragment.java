package com.zhulong.data.view.data_group_data;

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
import androidx.lifecycle.ViewModelProvider;

/**
 * 应用模块: Data
 * <p>
 * 类描述: 资料小组
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Data.PAGER_DATA_GroupData)
public class DataGroupDataFragment extends BaseFragment<DataFragmentDataBinding, DataGroupDataViewModel> implements IDataContractView.IGroupData.IView {
    public static final int GROUP_DATA_TYPE  = 1;  //1资料小组 2问答小组
    public static final String GROUP_DATA_TYPE_KEY  = "type";

    public static DataGroupDataFragment newInstance(int type) {
        final DataGroupDataFragment fragment = new DataGroupDataFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt(GROUP_DATA_TYPE_KEY, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.data_fragment_group_data;
    }
    @Override
    public DataGroupDataViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        DataViewModelFactory factory = DataViewModelFactory.getInstance(requireActivity().getApplication(), new DataGroupDataModel());
        return new ViewModelProvider(this, factory).get(DataGroupDataViewModel.class);

    }
    @Override
    public int initVariableId() {
        return BR.dataViewModel;
    }
}