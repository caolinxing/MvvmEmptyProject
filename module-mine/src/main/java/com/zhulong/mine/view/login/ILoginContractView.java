package com.zhulong.mine.view.login;

import com.zhulong.library_base.mvvm.base_view.IBaseView;
import com.zhulong.network.BaseResponse;
import com.zhulong.network.bean.mine.login.PersonHeaderBean;
import com.zhulong.network.bean.mine.login.ZlLoginBean;

import java.util.Map;

import io.reactivex.Observable;
import io.rx_cache2.Reply;


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
        Observable<Reply<BaseResponse<ZlLoginBean>>> onLogin(Map<String,String> params);
        Observable<Reply<BaseResponse<PersonHeaderBean>>> getUserHeader(Map<String,String> params);
    }
}
