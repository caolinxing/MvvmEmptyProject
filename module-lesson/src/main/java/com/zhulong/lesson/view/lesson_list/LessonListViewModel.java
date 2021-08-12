package com.zhulong.lesson.view.lesson_list;

import android.app.Application;

import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

/**
 * 应用模块:
 * <p>
 * 类描述: 课程列表[业务层]
 * <p>
 *
 * @since: clx
 * @date: 2021/8/11
 */
public class LessonListViewModel extends BaseViewModel {

    public LessonListViewModel(@NonNull @NotNull Application application, BaseModel model) {
        super(application, model);
    }
}
