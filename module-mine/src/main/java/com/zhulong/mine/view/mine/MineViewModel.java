package com.zhulong.mine.view.mine;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhulong.common.router.RouterActivityPath;
import com.zhulong.library_base.binding.command.BindingAction;
import com.zhulong.library_base.binding.command.BindingCommand;
import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;
import com.zhulong.mine.view.login.LoginModel;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * 应用模块:
 * <p>
 * 类描述: 个人中心VieModel
 * <p>
 *
 * @since: clx
 * @date: 2021/7/15
 */
public class MineViewModel extends BaseViewModel<MineModel<BaseModel>> {
    //接口请求参数
    Map<String, String> requestMap = new HashMap<>();
    public MineViewModel(@NonNull Application application,MineModel model) {
        super(application,model);
    }
    public MineViewModel(Application model) {
        super(model);
    }

    public BindingCommand<Void> goLogin = new BindingCommand<Void>(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Mine.PAGER_MINE_LOGIN).navigation();
        }
    });

}
