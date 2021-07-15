
package com.zhulong.main.ui;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhulong.common.adapter.ScreenAutoAdapter;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.common.utils.MmkvHelper;
import com.zhulong.library_base.viewmodel.IMvvmBaseViewModel;
import com.zhulong.main.R;
import com.zhulong.main.adapter.MainPageAdapter;
import com.zhulong.main.databinding.MainActivityMainBinding;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainActivity extends MvvmBaseActivity<MainActivityMainBinding, IMvvmBaseViewModel> {

    private ArrayList<Fragment> fragments;
    private MainPageAdapter mainPageAdapter;

    public static void start(Context context){
        MmkvHelper.getInstance().getMmkv().encode("first",false);
        context.startActivity(new Intent(context,MainActivity.class));
    }

    @Override
    public void initPageData() {
        initFragment();
        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        viewDataBinding.viewPager.setOffscreenPageLimit(1);
        viewDataBinding.viewPager.setAdapter(mainPageAdapter);
        viewDataBinding.bottomView.enableItemShiftingMode(false);
        viewDataBinding.bottomView.enableAnimation(false);
        viewDataBinding.bottomView.enableShiftingMode(false);
        viewDataBinding.bottomView.setItemIconTintList(null);

        viewDataBinding.bottomView.setItemTextAppearanceActive(R.style.main_bottom_selected_text);
        viewDataBinding.bottomView.setItemTextAppearanceInactive(R.style.main_bottom_normal_text);
        viewDataBinding.bottomView.setupWithViewPager(viewDataBinding.viewPager);
    }

    @Override
    public void initEvent() {
        viewDataBinding.bottomView.setOnNavigationItemSelectedListener(item -> true);
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
    protected IMvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected void setScreenAuto() {
        ScreenAutoAdapter.match(this, 375.0f);
    }



}