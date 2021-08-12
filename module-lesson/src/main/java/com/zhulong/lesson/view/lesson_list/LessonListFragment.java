package com.zhulong.lesson.view.lesson_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.lesson.R;
import com.zhulong.lesson.BR;
import com.zhulong.lesson.application.LessonViewModelFactory;
import com.zhulong.lesson.databinding.LessonFragmentLessonLayoutBinding;
import com.zhulong.lesson.view.ILessonContractView;
import com.zhulong.library_base.mvvm.base_view.BaseFragment;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

/**
 * 应用模块: Data
 * <p>
 * 类描述: 课程列表
 * <p>
 *
 * @author clx
 * @since 2021-07-03
 */
@Route(path = RouterFragmentPath.Lesson.PAGER_LESSON_LIST)
public class LessonListFragment extends BaseFragment<LessonFragmentLessonLayoutBinding, LessonListViewModel> implements ILessonContractView.ILessonList.IView {
    public static final int OPEN_LESSON_TYPE  = 1;  //1训练营 2精品课 3公开课
    public static final int GOOD_LESSON_TYPE  = 2;  //1训练营 2精品课 3公开课
    public static final int TRAINING_LESSON_TYPE  = 3;  //1训练营 2精品课 3公开课
    public static final String LESSON_TYPE_KEY  = "lesson_type";//1训练营 2精品课 3公开课

    public static LessonListFragment newInstance(int type) {
        final LessonListFragment fragment = new LessonListFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt(LESSON_TYPE_KEY, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return R.layout.lesson_fragment_lesson_layout;
    }
    @Override
    public LessonListViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        LessonViewModelFactory factory = LessonViewModelFactory.getInstance(requireActivity().getApplication(), new LessonListModel());
        return new ViewModelProvider(this, factory).get(LessonListViewModel.class);

    }
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }
}