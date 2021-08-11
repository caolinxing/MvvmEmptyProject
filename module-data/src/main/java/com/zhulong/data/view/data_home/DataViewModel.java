package com.zhulong.data.view.data_home;

import android.app.Application;

import com.zhulong.data.view.data_group_data.DataGroupDataFragment;
import com.zhulong.data.view.data_group_new.DataGroupNewFragment;
import com.zhulong.library_base.binding.command.BindingCommand;
import com.zhulong.library_base.bus.event.SingleLiveEvent;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 应用模块:
 * <p>
 * 类描述: 资料[ViewModel]
 * <p>
 *
 * @since: clx
 * @date: 2021/8/11
 */
public class DataViewModel extends BaseViewModel {
    public final String[] mTabData = {"资料小组", "最新精华"};
    public List<Fragment> fragmentList = new ArrayList<>();
    public ObservableInt pagerIndex = new ObservableInt(0);
    public ObservableField<FragmentManager> childFragmentManager = new ObservableField<>();
    /**
     * 封装一个界面发生改变的观察者
     */
    public static class UIChangeObservable {
        //切换Fragment
        public static SingleLiveEvent<Integer> onFragmentSelectEvent = new SingleLiveEvent<>();
    }

    public DataViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void initFragment() {
        DataGroupDataFragment dataGroupDataFragment = DataGroupDataFragment.newInstance(DataGroupDataFragment.GROUP_DATA_TYPE);
        DataGroupNewFragment dataGroupNewFragment = DataGroupNewFragment.newInstance();
        fragmentList.add(dataGroupDataFragment);
        fragmentList.add(dataGroupNewFragment);

    }

    public BindingCommand<Integer> onTabSelectListener = new BindingCommand<>((index) -> {
        pagerIndex.set(index);
        UIChangeObservable.onFragmentSelectEvent.setValue(index);
    });
    public BindingCommand<Integer> onPagerSelectListener = new BindingCommand<>((index) -> {
        pagerIndex.set(index);
        UIChangeObservable.onFragmentSelectEvent.setValue(index);
    });
}
