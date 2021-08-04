package com.zhulong.mine.view.mine;

import android.app.Application;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhulong.common.router.RouterActivityPath;
import com.zhulong.library_base.binding.command.BindingCommand;
import com.zhulong.library_base.mvvm.model.BaseModel;
import com.zhulong.library_base.mvvm.view_model.BaseViewModel;
import com.zhulong.library_base.utils.ToastUtil;
import com.zhulong.network.ApiCallBack;
import com.zhulong.network.BaseResponse;
import com.zhulong.network.bean.mine.login.PersonHeaderBean;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import io.rx_cache2.Reply;

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
    public PersonHeaderBean personHeaderBean = new PersonHeaderBean();
    public ObservableField<PersonHeaderBean> data = new ObservableField<>();


    public MineViewModel(@NonNull Application application, MineModel<BaseModel> model) {
        super(application, model);
    }

    //登录
    public BindingCommand<Void> mgoLogin = new BindingCommand<>(() ->
            ARouter.getInstance().build(RouterActivityPath.Mine.PAGER_MINE_LOGIN).navigation()
    );

    /**
     * 栏目一点击
     */
    public BindingCommand<View> clickGroup1 = new BindingCommand<>((view) -> {
        switch (view.getTag().toString()) {
            case "goSheQun":
                //社群
                showToast("社群");
                break;
            case "goPingCe":
                //评测
                showToast("评测");
                break;
            case "goYouHuiJuan":
                //优惠卷
                showToast("优惠卷");
                break;
            case "goFanKui":
                //反馈
                showToast("反馈");
                break;
            case "goKuaiDi":
                //课程收藏
                showToast("快递");
                break;
            default:
                break;
        }
    });

    /**
     * 栏目二点击
     */
    public BindingCommand<View> clickGroup2 = new BindingCommand<>((view) -> {
        switch (view.getTag().toString()) {

            case "goYiGouKeCheng":
                //已购课程
                showToast("已购课程");
                break;
            case "goDaiFuKuan":
                //待付款
                showToast("待付款");
                break;
            case "goGuanKanJiLu":
                //观看记录
                showToast("观看记录");
                break;
            case "goKeChengShouCang":
                //课程收藏
                showToast("课程收藏");
                break;
            case "goHuanCun":
                //缓存
                showToast("缓存");
                break;
            default:
                break;
        }
    });

    /**
     * 栏目三点击
     */
    public BindingCommand<View> clickGroup3 = new BindingCommand<>((view) -> {
        switch (view.getTag().toString()) {
            case "goShangChuan":
                //上传
                showToast("上传");
                break;
            case "goXiaZai":
                //下载
                showToast("下载");
                break;
            case "goZiLiaoShouCang":
                //资料收藏
                showToast("资料收藏");
                break;
            case "goEVip":
                //E会员
                showToast("E会员");
                break;
            default:
                break;
        }
    });

    /**
     * 栏目四点击
     */
    public BindingCommand<View> clickGroup4 = new BindingCommand<>((view) -> {
        switch (view.getTag().toString()) {

            case "goWoDeFaTie":
                //我的发帖
                showToast("我的发帖");
                break;
            case "goWoDeHuiFu":
                //我的回复
                showToast("我的回复");
                break;
            case "goGuanZhuXiaoZu":
                //关注小组
                showToast("关注小组");
                break;
            case "goGuanZhuLongYou":
                //关注龙友
                showToast("关注龙友");
                break;
            case "goLiuLanJiLu":
                //浏览记录
                showToast("浏览记录");
                break;
            default:
                break;
        }
    });

    /**
     * 礼物点击
     */
    public BindingCommand<View> goGiftDetail = new BindingCommand<>((view) -> {
        switch (view.getTag().toString()) {
            case "0":
                showToast("点击礼物1");
                break;
            case "1":
                showToast("点击礼物2");
                break;
            case "2":
                showToast("点击礼物3");
                break;
            case "3":
                showToast("点击礼物4");
                break;
            default:
                showToast("点击礼物1");
                break;
        }
    });

    //头信息
    public void getUserHeader() {
        requestMap.clear();
        requestMap.put("zuid", getUserInfo().getUid() + "");
        model.getUserHeader(requestMap).subscribe(new ApiCallBack<Reply<BaseResponse<PersonHeaderBean>>>() {
            @Override
            public void onSuccess(Reply<BaseResponse<PersonHeaderBean>> result) {
                personHeaderBean = result.getData().getResult();
                data.set(personHeaderBean);
                showToast(ToastUtil.SUCCESS, personHeaderBean.getUsername() + "登入成功");
            }

            @Override
            public void onFail(int code, String wrongMsg, String result) {
                showToast(wrongMsg);

            }
        });
    }

}
