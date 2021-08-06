
package com.zhulong.main.ui;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.common.utils.MmkvHelper;
import com.zhulong.library_base.mvvm.base_view.BaseActivity;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;
import com.zhulong.main.R;
import com.zhulong.main.adapter.MainPageAdapter;
import com.zhulong.main.databinding.MainActivityMainBinding;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainActivity extends BaseActivity<MainActivityMainBinding, BaseViewModel> {

    private ArrayList<Fragment> fragments;
    private MainPageAdapter mainPageAdapter;

    public static void start(Context context) {
        MmkvHelper.getInstance().getMmkv().encode("first", false);
        context.startActivity(new Intent(context, MainActivity.class));
    }


    private void initFragment() {
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation();
        Fragment classFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Lesson.PAGER_LESSON).navigation();
        Fragment vipFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Vip.PAGER_VIP).navigation();
        Fragment dataFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Data.PAGER_DATA).navigation();
        Fragment mineFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Mine.PAGER_MINE).navigation();
        fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(classFragment);
        fragments.add(vipFragment);
        fragments.add(dataFragment);
        fragments.add(mineFragment);
        mainPageAdapter.setData(fragments);
    }


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.main_activity_main;
    }


    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public void initData() {
        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        binding.viewPager.setOffscreenPageLimit(0);
        binding.viewPager.setAdapter(mainPageAdapter);
        binding.bottomView.enableItemShiftingMode(false);
        binding.bottomView.enableAnimation(false);
        binding.bottomView.enableShiftingMode(false);
        binding.bottomView.setItemIconTintList(null);
        binding.bottomView.setItemTextAppearanceActive(R.style.main_bottom_selected_text);
        binding.bottomView.setItemTextAppearanceInactive(R.style.main_bottom_normal_text);
        binding.bottomView.setupWithViewPager(binding.viewPager);
        initFragment();
    }
}