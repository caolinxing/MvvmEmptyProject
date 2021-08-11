package com.zhulong.data.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.zhulong.data.view.data_group_data.DataGroupDataModel;
import com.zhulong.data.view.data_group_data.DataGroupDataViewModel;
import com.zhulong.data.view.data_group_new.DataGroupNewModel;
import com.zhulong.data.view.data_group_new.DataGroupNewViewModel;
import com.zhulong.data.view.data_home.DataViewModel;
import com.zhulong.library_base.mvvm.model.BaseModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by goldze on 2019/3/26.
 */
public class DataViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile DataViewModelFactory INSTANCE;
    private final Application mApplication;
    private final BaseModel model;

    public static DataViewModelFactory getInstance(Application application, BaseModel model) {
        return new DataViewModelFactory(application,model);
    }


    private DataViewModelFactory(Application application, BaseModel model) {
        this.mApplication = application;
        this.model = model;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DataGroupDataViewModel.class)) {
            return (T) new DataGroupDataViewModel(mApplication,(DataGroupDataModel)model);
        }else if (modelClass.isAssignableFrom(DataGroupNewViewModel.class)) {
            return (T) new DataGroupNewViewModel(mApplication,(DataGroupNewModel)model);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
