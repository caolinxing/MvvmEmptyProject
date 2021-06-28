/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhulong.network.utils;


import com.zhulong.network.func.HandleFuc;
import com.zhulong.network.func.HttpResponseFunc;
import com.zhulong.network.model.ApiResult;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * <p>描述：线程调度工具</p>
 * 作者： zhouyou<br>
 * 日期： 2017/5/15 17:12 <br>
 * 版本： v1.0<br>
 */
public class RxUtil {

    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                HttpLog.i("+++doOnSubscribe+++" + disposable.isDisposed());
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                HttpLog.i("+++doFinally+++");
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<ApiResult<T>, T> _io_main() {
        return new ObservableTransformer<ApiResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<ApiResult<T>> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new HandleFuc<T>())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                HttpLog.i("+++doOnSubscribe+++" + disposable.isDisposed());
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                HttpLog.i("+++doFinally+++");
                            }
                        })
                        .onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }


    public static <T> ObservableTransformer<ApiResult<T>, T> _main() {
        return new ObservableTransformer<ApiResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<ApiResult<T>> upstream) {
                return upstream
                        //.observeOn(AndroidSchedulers.mainThread())
                        .map(new HandleFuc<T>())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                HttpLog.i("+++doOnSubscribe+++" + disposable.isDisposed());
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                HttpLog.i("+++doFinally+++");
                            }
                        })
                        .onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }
}
