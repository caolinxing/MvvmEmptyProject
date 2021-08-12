package com.zhulong.mine.view.login;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;
import com.zhulong.common.utils.RsaUtil;
import com.zhulong.library_base.binding.command.BindingCommand;
import com.zhulong.library_base.bus.event.SingleLiveEvent;
import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;
import com.zhulong.library_base.utils.ToastUtil;
import com.zhulong.library_base.utils.ToastUtils;
import com.zhulong.mine.config.MineConfig;
import com.zhulong.network.ApiCallBack;
import com.zhulong.network.BaseResponse;
import com.zhulong.network.bean.mine.login.PersonHeaderBean;
import com.zhulong.network.bean.mine.login.UserInfoBean;
import com.zhulong.network.bean.mine.login.ZlLoginBean;
import com.zhulong.network.config.CookieBean;
import com.zhulong.network.util.GsonUtils;
import com.zhulong.network.util.NetWorkUtil;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import io.rx_cache2.Reply;

/**
 * 应用模块:
 * <p>
 * 类描述: 登入ViewModel
 * <p>
 *
 * @since: clx
 * @date: 2021/7/12
 */
public class LoginViewModel extends BaseViewModel<LoginModel<BaseModel>> {
    //接口请求参数
    Map<String, String> requestMap = new HashMap<>();
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearBtnVisibility = new ObservableInt(View.INVISIBLE);
    //密码明/密文的显示隐藏绑定
    public ObservableInt pwdVisibility = new ObservableInt(View.INVISIBLE);

    public UIChangeObservable mUc = new UIChangeObservable();

    public LoginViewModel(@NonNull @NotNull Application application, LoginModel<BaseModel> model) {
        super(application, model);
        this.model = model;
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public static class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
        //协议勾选
        public SingleLiveEvent<Boolean> checkedAgreementSwitchEvent = new SingleLiveEvent<>();
    }


    /**
     * 账号输入框监听
     */
    public BindingCommand<String> accountEdiChangeListener = new BindingCommand<>(str -> {
        if (!TextUtils.isEmpty(str)) {
            clearBtnVisibility.set(View.VISIBLE);
        } else {
            clearBtnVisibility.set(View.INVISIBLE);
        }
    });

    /**
     * 密码输入框监听
     */
    public BindingCommand<String> pwdEdiChangeListener = new BindingCommand<>(str -> {
        if (!TextUtils.isEmpty(str)) {
            pwdVisibility.set(View.VISIBLE);
        } else {
            pwdVisibility.set(View.INVISIBLE);
        }
    });


    /**
     * 登录相关点击
     */
    public BindingCommand<View> clickLogin = new BindingCommand<>((view) -> {
        switch (view.getTag().toString()) {
            case "QQLogin":
                //QQ登录
                showToast("QQ登录");
                break;
            case "WeChatLogin":
                //微信登录
                showToast("微信登录");
                break;
            case "ZLLogin":
                //筑龙登录和手机号登录
                doAccountLogin();
                break;
            case "registerAccount":
                //注册账号
                showToast("注册账号");
                break;
            case "forgetPwd":
                //忘记密码
                showToast("忘记密码");
                break;
            default:
                break;
        }
    });

    /**
     * 页面其他点击
     */
    public BindingCommand<View> otherClick = new BindingCommand<>((view) -> {
        switch (view.getTag().toString()) {
            case "close":
                //关闭页面
                finish();
                break;
            case "startAgreementPage":
                //跳转协议页
                showToast("跳转协议页");
                break;
            case "passwordShowSwitchOnClickCommand":
                //密码显示开关
                //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
                mUc.pSwitchEvent.setValue(mUc.pSwitchEvent.getValue() == null || !mUc.pSwitchEvent.getValue());
                break;
            case "checkedAgreementOnClickCommand":
                //协议勾选
                mUc.checkedAgreementSwitchEvent.setValue(true);
                break;
            case "clearAccountOnClickCommand":
                //清除账号
                userName.set("");
                break;
            default:
                break;
        }
    });


    /**
     * 账号登录
     */
    private void doAccountLogin() {
        boolean isAgree = MMKV.defaultMMKV().getBoolean(MineConfig.KeyConfig.KEY_IS_AGREE_AGREEMENT, false);
        if (!isAgree) {
            showToast("请先同意服务协议和隐私政策");
            return;
        }
        //验证码功能去除传空字符
        requestMap.clear();
        requestMap.put("loginName", userName.get());
        requestMap.put("passwd", RsaUtil.encryptByPublic(password.get()));
        requestMap.put("fromUrl", "android");
        requestMap.put("ignoreMobile", "0");
        login(requestMap);
    }

    /**
     * 账号登陆
     */
    private void login(Map<String, String> params) {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        //RaJava登录
        model.onLogin(params).subscribe(new ApiCallBack<Reply<BaseResponse<ZlLoginBean>>>() {

            @Override
            public void onSuccess(Reply<BaseResponse<ZlLoginBean>> result) {
                ZlLoginBean loginBean = result.getData().getResult();
                Logger.i(loginBean.getUsername());
                UserInfoBean userInfo = getUserInfo();
                userInfo.setUid(loginBean.getUid());
                userInfo.setUname(loginBean.getUsername());
                saveUserInfo(userInfo);
                getUserHeader(loginBean.getUid());
                CookieBean cookieBean = new CookieBean();
                cookieBean.setIs_corp(loginBean.getIs_corp());
                cookieBean.setPcid(loginBean.getPcid());
                cookieBean.setZlid(loginBean.getZlid());
                cookieBean.setUsername(loginBean.getUsername());
                cookieBean.setUid(loginBean.getUid());
                NetWorkUtil.getInstance().setCookieInfo(cookieBean);
            }

            @Override
            public void onFail(int code, String wrongMsg, String result) {
                showToast(wrongMsg);
            }
        });
    }

    /**
     * 用户头信息
     */
    private void getUserHeader(String uid) {
        requestMap.clear();
        requestMap.put("zuid", uid);
        model.getUserHeader(requestMap).subscribe(new ApiCallBack<Reply<BaseResponse<PersonHeaderBean>>>() {
            @Override
            public void onSuccess(Reply<BaseResponse<PersonHeaderBean>> result) {
                PersonHeaderBean personHeaderBean = result.getData().getResult();
                MMKV.defaultMMKV().putString(MineConfig.KeyConfig.KEY_USER_HEADER_INFO, GsonUtils.toJson(personHeaderBean));
                showToast(ToastUtil.SUCCESS, personHeaderBean.getUsername() + "登入成功");
                finish();
            }

            @Override
            public void onFail(int code, String wrongMsg, String result) {
                showToast(wrongMsg);

            }
        });
    }

}
