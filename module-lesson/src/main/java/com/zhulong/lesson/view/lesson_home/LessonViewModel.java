package com.zhulong.lesson.view.lesson_home;

import android.app.Application;

import com.zhulong.lesson.view.lesson_list.LessonListFragment;
import com.zhulong.library_base.binding.command.BindingCommand;
import com.zhulong.library_base.bus.event.SingleLiveEvent;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * 应用模块:
 * <p>
 * 类描述: 课程[ViewModel]
 * <p>
 *
 * @since: clx
 * @date: 2021/8/11
 */
public class LessonViewModel extends BaseViewModel {
    public final String[] mTabData = {"训练营", "精品课", "公开课"};
    public List<Fragment> fragmentList = new ArrayList<>();
    public ObservableInt pagerIndex = new ObservableInt(0);
    public ObservableField<FragmentManager> childFragmentManager = new ObservableField<>();
    public int defaultTabIndex = 0;


    public LessonViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void initFragment() {
        LessonListFragment TrainingLessonListFragment = LessonListFragment.newInstance(LessonListFragment.TRAINING_LESSON_TYPE);
        LessonListFragment GoodLessonListFragment = LessonListFragment.newInstance(LessonListFragment.GOOD_LESSON_TYPE);
        LessonListFragment OpenLessonListFragment = LessonListFragment.newInstance(LessonListFragment.OPEN_LESSON_TYPE);
        fragmentList.add(TrainingLessonListFragment);
        fragmentList.add(GoodLessonListFragment);
        fragmentList.add(OpenLessonListFragment);

    }
}
