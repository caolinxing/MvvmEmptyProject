package com.zhulong.mine.view;

import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.zhulong.common.adapter.ScreenAutoAdapter;
import com.zhulong.library_base.activity.MvvmBaseActivity;
import com.zhulong.library_base.viewmodel.IMvvmBaseViewModel;
import com.zhulong.mine.R;
import com.zhulong.mine.databinding.MineActivityLoginBinding;

public class LoginActivity extends MvvmBaseActivity<MineActivityLoginBinding, IMvvmBaseViewModel> {

    @Override
    public void initPageData() {
        viewDataBinding.loginTab.setTabTextColors(
                getResources().getColor(R.color.base_font_gray),
                getResources().getColor(R.color.base_red));

    }

    @Override
    public void initEvent() {
        //tab监听
        viewDataBinding.loginTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabName = tab.getText().toString().trim();
                if ("账号登录".contains(tabName)) {
                    viewDataBinding.loginLinAccountView.setVisibility(View.VISIBLE);
                    viewDataBinding.loginLinCodeView.setVisibility(View.GONE);
                } else {
                    viewDataBinding.loginLinAccountView.setVisibility(View.GONE);
                    viewDataBinding.loginLinCodeView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //点击监听
        viewDataBinding.loginIvClose.setOnClickListener(mClickListener);
        viewDataBinding.loginBtnLogin.setOnClickListener(mClickListener);
        viewDataBinding.loginIvPrivacy.setOnClickListener(mClickListener);
        viewDataBinding.loginIvCheckBoxPrivacy.setOnClickListener(mClickListener);
        viewDataBinding.loginIvWx.setOnClickListener(mClickListener);
        viewDataBinding.loginIvQq.setOnClickListener(mClickListener);
        viewDataBinding.loginTvRegister.setOnClickListener(mClickListener);
        viewDataBinding.loginTvForgetPwd.setOnClickListener(mClickListener);

    }

    private View.OnClickListener mClickListener = v -> {
        if (v.getId() == R.id.login_iv_close) {
            //finish();
        } else if (v.getId() == R.id.login_iv_qq) {
        } else if (v.getId() == R.id.login_iv_wx) {
        } else if (v.getId() == R.id.login_tv_forget_pwd) {
        } else if (v.getId() == R.id.login_tv_register) {
        } else if (v.getId() == R.id.login_btn_login) {
        } else if (v.getId() == R.id.login_iv_privacy) {
        } else if (v.getId() == R.id.login_iv_check_box_privacy) {
        }
    };

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
        return R.layout.mine_activity_login;
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected void setScreenAuto() {
        ScreenAutoAdapter.match(this, 375.0f);
    }


}