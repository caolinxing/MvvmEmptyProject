package com.zhulong.mine.view.login;

import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.network.BaseResponse;
import com.zhulong.network.RetrofitUtil;
import com.zhulong.network.RetrofitUtil2;
import com.zhulong.network.RxTransformer;
import com.zhulong.network.bean.mine.login.ZlLoginBean;
import com.zhulong.network.config.NetWorkKeyConfig;

import java.util.Map;

import io.reactivex.Observable;
import io.rx_cache2.Reply;

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

   /* @Override
    public Observable<BaseResponse<ZlLoginBean>> onLogin(Map<String,String> params) {
        return RetrofitUtil
                .getInstance()
                .getPassportApi()
                .loginZl(params, NetWorkKeyConfig.PASSPORT_SECRECT_KEY)
                .compose(RxTransformer.<BaseResponse<ZlLoginBean>>transformer());

    }
*/

    public Observable<Reply<BaseResponse<ZlLoginBean>>> onLogin2(Map<String,String> params) {
        return RetrofitUtil2
                .getInstance()
                .loginZl(params)
                .compose(RxTransformer.<Reply<BaseResponse<ZlLoginBean>>>transformer());

    }

    @Override
    public io.reactivex.rxjava3.core.Observable<BaseResponse<ZlLoginBean>> onLogin(Map<String, String> params) {
        return null;
    }

    /*public Observable<BaseResponse<ZlLoginBean>> onLogi3(Map<String,String> params) {
        return RetrofitUtil2
                .getInstance()
                .getPassportApi()
                .loginZl(params, NetWorkKeyConfig.PASSPORT_SECRECT_KEY)
                .compose(RxTransformer.<BaseResponse<ZlLoginBean>>transformer());

    }
*/
}
