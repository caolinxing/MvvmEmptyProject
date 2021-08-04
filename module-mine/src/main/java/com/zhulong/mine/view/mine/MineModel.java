package com.zhulong.mine.view.mine;

import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.network.BaseResponse;
import com.zhulong.network.RetrofitUtil;
import com.zhulong.network.RxTransformer;
import com.zhulong.network.bean.mine.login.PersonHeaderBean;

import java.util.Map;

import io.reactivex.Observable;
import io.rx_cache2.Reply;

/**
 * 应用模块:
 * <p>
 * 类描述: 个人中心Model
 * <p>
 *
 * @since: clx
 * @date: 2021/7/29
 */
public class MineModel<T> extends BaseModel implements IMineContractView.IModel {
    @Override
    public Observable<Reply<BaseResponse<PersonHeaderBean>>> getUserHeader(Map<String, String> params) {
        return RetrofitUtil
                .getInstance()
                .getUserHeader(params)
                .compose(RxTransformer.transformer());
    }
}
