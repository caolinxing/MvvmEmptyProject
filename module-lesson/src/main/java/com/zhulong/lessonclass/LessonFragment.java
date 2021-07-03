package com.zhulong.lessonclass;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhulong.common.router.RouterFragmentPath;
import com.zhulong.lessonclass.databinding.LessonFragmentHomeBinding;
import com.zhulong.library_base.fragment.MvvmLazyFragment;
import com.zhulong.library_base.viewmodel.IMvvmBaseViewModel;

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
public class LessonFragment extends MvvmLazyFragment<LessonFragmentHomeBinding, IMvvmBaseViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.lesson_fragment_home;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
    }

    private void initView() {

    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected IMvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected void onRetryBtnClick() {

    }
}