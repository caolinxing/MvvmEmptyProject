package com.zhulong.main.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.zhulong.library_base.mvvm.model.BaseModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by goldze on 2019/3/26.
 */
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile MainViewModelFactory INSTANCE;
    private final Application mApplication;
    private final BaseModel model;

    public static MainViewModelFactory getInstance(Application application, BaseModel model) {
        return new MainViewModelFactory(application,model);
    }


    private MainViewModelFactory(Application application, BaseModel model) {
        this.mApplication = application;
        this.model = model;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
       /* if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication,(LoginModel)model);
        }*/
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
