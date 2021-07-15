package com.zhulong.mine.view.login;

import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.network.BaseResponse;
import com.zhulong.network.RetrofitUtil;
import com.zhulong.network.RxTransformer;
import com.zhulong.network.bean.mine.login.ZlLoginBean;
import com.zhulong.network.config.NetWorkKeyConfig;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * 应用模块:
 * <p>
 * 类描述: Login Model层
 * <p>
 *
 * @since: clx
 * @date: 2021/7/12
 */
public class LoginModel<T> extends BaseModel implements ILoginContractView.IModel {

    @Override
    public Observable<BaseResponse<ZlLoginBean>> onLogin(Map<String,String> params) {
        return RetrofitUtil
                .getInstance()
                .getPassportApi()
                .loginZl(params, NetWorkKeyConfig.PASSPORT_SECRECT_KEY)
                .compose(RxTransformer.<BaseResponse<ZlLoginBean>>transformer());

    }
}
