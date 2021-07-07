package com.zhulong.network;


import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * RxTransformer
 */
public class RxTransformer {

    /**
     * 无参数，仅做线程切换
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> transformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.throttleFirst(500, TimeUnit.MILLISECONDS)//防抖(防重点击)
                        .subscribeOn(Schedulers.io())//线程切换
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
