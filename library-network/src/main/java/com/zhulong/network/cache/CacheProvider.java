package com.zhulong.network.cache;

import com.zhulong.network.BaseResponse;
import com.zhulong.network.bean.mine.login.ZlLoginBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * 应用模块:
 * <p>
 * 类描述: RxCache 提供者
 * <p>
 *
 * @since: clx
 * @date: 2021/7/17
 */
public interface CacheProvider {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<BaseResponse<ZlLoginBean>>> loginZl(Observable<BaseResponse<ZlLoginBean>> zlLoginBean, EvictProvider evictProvider);
}
