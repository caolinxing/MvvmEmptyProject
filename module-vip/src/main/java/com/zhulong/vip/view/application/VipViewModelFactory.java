package com.zhulong.vip.view.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.zhulong.library_base.mvvm.model.BaseModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by goldze on 2019/3/26.
 */
public class VipViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile VipViewModelFactory INSTANCE;
    private final Application mApplication;
    private final BaseModel model;

    public static VipViewModelFactory getInstance(Application application, BaseModel model) {
        return new VipViewModelFactory(application, model);
    }


    private VipViewModelFactory(Application application, BaseModel model) {
        this.mApplication = application;
        this.model = model;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      /*  if (modelClass.isAssignableFrom(DataViewModel.class)) {
            return (T) new DataViewModel(mApplication,(DataModel)model);
        }*/
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
