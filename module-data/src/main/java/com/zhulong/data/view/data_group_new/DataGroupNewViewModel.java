package com.zhulong.data.view.data_group_new;

import android.app.Application;

import com.zhulong.data.view.data_group_data.DataGroupDataModel;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

/**
 * 应用模块:
 * <p>
 * 类描述: 最新精华[业务层]
 * <p>
 *
 * @since: clx
 * @date: 2021/8/11
 */
public class DataGroupNewViewModel extends BaseViewModel<DataGroupNewModel> {
    public DataGroupNewViewModel(@NonNull @NotNull Application application, DataGroupNewModel model) {
        super(application, model);
    }
}
