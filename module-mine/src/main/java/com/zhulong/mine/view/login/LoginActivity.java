package com.zhulong.mine.view.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhulong.library_base.mvvm.base_view.BaseActivity;
import com.zhulong.mine.BR;
import com.zhulong.mine.R;
import com.zhulong.mine.application.AppViewModelFactory;
import com.zhulong.mine.databinding.MineActivityLoginBinding;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.rxjava3.annotations.Nullable;

public class LoginActivity extends BaseActivity<MineActivityLoginBinding, LoginViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.mine_activity_login;
    }



    @Override
    public int initVariableId() {
        return BR.loginViewModel;
    }

    @Override
    public void initViewObservable() {
        View loginLinCodeView = binding.loginLinAccountView;
        ImageView iv_show_psw = loginLinCodeView.findViewById(R.id.iv_show_eye);
        viewModel.uc.pSwitchEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
                if (viewModel.uc.pSwitchEvent.getValue()) {
                    //密码可见
                    //在xml中定义id后,使用binding可以直接拿到这个view的引用,不再需要findViewById去找控件了
                    iv_show_psw.setSelected(true);
                } else {
                    iv_show_psw.setSelected(false);
                }
            }
        });
    }
}