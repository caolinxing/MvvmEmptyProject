package com.zhulong.data.view.data_group_data;

import android.app.Application;

import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

/**
 * 应用模块:
 * <p>
 * 类描述: 资料小组[业务层]
 * <p>
 *
 * @since: clx
 * @date: 2021/8/11
 */
public class DataGroupDataViewModel extends BaseViewModel {
    public final String[] mTabData = {"资料小组", "最新精华"};

    public DataGroupDataViewModel(@NonNull @NotNull Application application, BaseModel model) {
        super(application, model);
    }
}
