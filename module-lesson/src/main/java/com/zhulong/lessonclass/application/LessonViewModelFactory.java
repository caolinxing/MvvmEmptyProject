package com.zhulong.lessonclass.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.zhulong.library_base.mvvm.model.BaseModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by goldze on 2019/3/26.
 */
public class LessonViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile LessonViewModelFactory INSTANCE;
    private final Application mApplication;
    private final BaseModel model;

    public static LessonViewModelFactory getInstance(Application application, BaseModel model) {
        return new LessonViewModelFactory(application, model);
    }


    private LessonViewModelFactory(Application application, BaseModel model) {
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
