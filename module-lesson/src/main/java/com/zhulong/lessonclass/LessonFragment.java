package com.zhulong.lessonclass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.lessonclass.databinding.LessonFragmentHomeBinding;
import com.zhulong.library_base.mvvm.base_view.BaseActivity;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;

import androidx.annotation.Nullable;

/**
 * 应用模块: Lesson
 * <p>
 * 类描述: 课程-fragment
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Lesson.PAGER_LESSON)
public class LessonFragment extends BaseFragment<LessonFragmentHomeBinding, BaseViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.lesson_fragment_home;
    }

    @Override
    public int initVariableId() {
        return 0;
    }
}