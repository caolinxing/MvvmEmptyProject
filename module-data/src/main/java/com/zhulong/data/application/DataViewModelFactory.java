package com.zhulong.data.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.zhulong.data.view.data.DataModel;
import com.zhulong.data.view.data.DataViewModel;
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
        if (modelClass.isAssignableFrom(DataViewModel.class)) {
            return (T) new DataViewModel(mApplication,(DataModel)model);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
