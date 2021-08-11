package com.zhulong.data.view;

import com.zhulong.library_base.mvvm.base_view.IBaseView;

/**
 * /**
 * 应用模块:
 * <p>
 * 类描述:约束层
 * <p>
 *
 * @Author: clx
 * @CreateDate: 2021/8/11 10:49
 */
public interface IDataContractView {
    //最新精华
    interface IGroupNew {
        interface IView extends IBaseView {
        }

        interface IModel {

        }
    }

    //资料小组
    interface IGroupData {
        interface IView extends IBaseView {
        }

        interface IModel {

        }
    }


}
