package com.zhulong.mine.view.mine;

import com.zhulong.library_base.mvvm.base_view.IBaseView;
import com.zhulong.network.BaseResponse;
import com.zhulong.network.bean.mine.login.PersonHeaderBean;

import java.util.Map;

import io.reactivex.Observable;
import io.rx_cache2.Reply;

/**
 * /**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @Author: clx
 * @CreateDate: 2021/7/29 11:30
 */
public interface IMineContractView {
    interface IView extends IBaseView {
    }
    interface IModel{
        Observable<Reply<BaseResponse<PersonHeaderBean>>> getUserHeader(Map<String,String> params);
    }
}
