package com.zhulong.lesson.view.lesson_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.adapter.TabViewPagerAdapter;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.lesson.BR;
import com.zhulong.lesson.R;
import com.zhulong.lesson.databinding.LessonFragmentHomeBinding;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

/**
 * 应用模块: Data
 * <p>
 * 类描述: 训练营-fragment
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Lesson.PAGER_LESSON)
public class LessonFragment extends BaseFragment<LessonFragmentHomeBinding, LessonViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.lesson_fragment_home;
    }

    @Override
    public LessonViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        return new ViewModelProvider(this).get(LessonViewModel.class);

    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        //切换页面
        binding.viewPager.setAdapter(new TabViewPagerAdapter(getChildFragmentManager(), viewModel.mTabData, viewModel.fragmentList));
        binding.tabLayout.setViewPager(binding.viewPager);
        binding.tabLayout.setIndicatorWidth(46);
        binding.tabLayout.setCurrentTab(viewModel.defaultTabIndex);
        binding.viewPager.setCurrentItem(viewModel.defaultTabIndex);

    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initFragment();
    }
}