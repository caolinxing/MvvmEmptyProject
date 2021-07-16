package com.zhulong.mine.view.login;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.zhulong.common.utils.RsaUtil;
import com.zhulong.library_base.binding.command.BindingAction;
import com.zhulong.library_base.binding.command.BindingCommand;
import com.zhulong.library_base.binding.command.BindingConsumer;
import com.zhulong.library_base.bus.event.SingleLiveEvent;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;
import com.zhulong.library_base.utils.ToastUtils;
import com.zhulong.network.ApiCallBack;
import com.zhulong.network.bean.mine.login.ZlLoginBean;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @since: clx
 * @date: 2021/7/12
 */
public class LoginViewModel extends BaseViewModel<LoginModel> {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearBtnVisibility = new ObservableInt();
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();
    private LoginModel loginModel;

    public String a = "登录";
    public LoginViewModel(@NonNull @NotNull Application application) {
        super(application);
    }
    public LoginViewModel(@NonNull @NotNull Application application, LoginModel model) {
        super(application, model);
        loginModel = model;
    }

    public class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }


    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });
    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    public BindingCommand passwordShowSwitchOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null || !uc.pSwitchEvent.getValue());
        }
    });
    //用户名输入框焦点改变的回调事件
    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                clearBtnVisibility.set(View.VISIBLE);
            } else {
                clearBtnVisibility.set(View.INVISIBLE);
            }
        }
    });
    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Logger.i("点击了");
            Map<String,String> requestMap = new HashMap<>();
            //验证码功能去除传空字符
            requestMap.put("ZLSessionID", "");
            requestMap.put("seccode", "");
            requestMap.put("loginName", userName.get());
            requestMap.put("passwd", RsaUtil.encryptByPublic(password.get()));
            requestMap.put("cookieday", "");
            requestMap.put("fromUrl", "android");
            requestMap.put("ignoreMobile", "0");
            login(requestMap);
        }
    });

    /**
     * 网络模拟一个登陆操作
     **/
    private void login(Map<String,String> params) {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        //RaJava登录
        loginModel.onLogin(params).subscribe(new ApiCallBack<ZlLoginBean>() {

            @Override
            public void onSuccess(ZlLoginBean result) {
            }

            @Override
            public void onFail(int code, String wrongMsg, String result) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
