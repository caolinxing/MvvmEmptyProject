package com.zhulong.mine.view.login;

import com.zhulong.library_base.mvvm.base_view.IBaseView;
import com.zhulong.network.BaseResponse;
import com.zhulong.network.bean.mine.login.ZlLoginBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * /**
 * 应用模块:
 * <p>
 * 类描述:Login view层
 * <p>
 *
 * @Author: clx
 * @CreateDate: 2021/7/12 16:04
 */
public interface ILoginContractView {

    interface ILoginView extends IBaseView {
    }
    interface IModel{
        Observable<BaseResponse<ZlLoginBean>> onLogin(Map<String,String> params);
    }
}
