package com.zhulong.mine.application;

import android.annotation.SuppressLint;
import android.app.Application;


import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.mine.view.login.LoginModel;
import com.zhulong.mine.view.login.LoginViewModel;
import com.zhulong.mine.view.mine.MineModel;
import com.zhulong.mine.view.mine.MineViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by goldze on 2019/3/26.
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final BaseModel model;

    public static AppViewModelFactory getInstance(Application application, BaseModel model) {
        return new AppViewModelFactory(application,model);
    }


    private AppViewModelFactory(Application application, BaseModel model) {
        this.mApplication = application;
        this.model = model;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication,(LoginModel)model);
        }else if (modelClass.isAssignableFrom(MineViewModel.class)) {
            return (T) new MineViewModel(mApplication,(MineModel) model);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
