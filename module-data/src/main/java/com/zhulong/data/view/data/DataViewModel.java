package com.zhulong.data.view.data;

import android.app.Application;

import com.zhulong.library_base.mvvm.view_model.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

/**
 * 应用模块:
 * <p>
 * 类描述: 资料[ViewModel]
 * <p>
 *
 * @since: clx
 * @date: 2021/8/11
 */
public class DataViewModel extends BaseViewModel<DataModel> {
    public final String[] mTabData = {"资料小组", "最新精华"};

    public DataViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public DataViewModel(@NonNull @NotNull Application application, DataModel model) {
        super(application, model);
    }
}
