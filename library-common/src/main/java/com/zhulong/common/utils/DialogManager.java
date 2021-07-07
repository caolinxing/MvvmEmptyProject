package com.zhulong.common.utils;

import android.content.Context;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.customview.DialogCustomViewExtKt;

/**
 * /**
 * 应用模块:
 * <p>
 * 类描述: 弹窗管理器
 * <p>
 * @since : clx
 * @date : 2021/7/7 12:28
 */
public class DialogManager {
    private volatile static DialogManager mInstance;
    private MaterialDialog dialog;
    private DialogCallBack callBack;

    private DialogManager() {}

    public static DialogManager getInstance(){
        if (mInstance==null){
            synchronized (DialogManager.class){
                if (mInstance==null){
                    mInstance = new DialogManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * @param contentList [标题,内容，确认按钮文案，取消按钮文案]
     *
     */
    public void showDialog(Context context, String[] contentList, DialogCallBack callBack){
        MaterialDialog dialog = new MaterialDialog(context, MaterialDialog.getDEFAULT_BEHAVIOR());
        dialog.title(null, contentList[0]);
        dialog.message(null, contentList[1], null);
        dialog.positiveButton(null, contentList[2], materialDialog -> {
            callBack.sureClick();
            return null;
        });
        dialog.negativeButton(null,  contentList[3], materialDialog -> {
            callBack.cancelClick();
            return null;
        });
        dialog.show();
    }

    public View showDialogCustomView(Context context,View view){
        dialog = new MaterialDialog(context, MaterialDialog.getDEFAULT_BEHAVIOR());
        DialogCustomViewExtKt.customView(dialog, null, view,
                false, true, false, false);
        dialog.show();
        View customView = DialogCustomViewExtKt.getCustomView(dialog);
        return customView;
    }

    public View showDialogCustomView(Context context,int layoutId){
        dialog = new MaterialDialog(context, MaterialDialog.getDEFAULT_BEHAVIOR());
        DialogCustomViewExtKt.customView(dialog, layoutId, null,
                false, true, false, false);
        dialog.show();
        View customView = DialogCustomViewExtKt.getCustomView(dialog);
        return customView;
    }

    public interface DialogCallBack{
        void sureClick();
        void cancelClick();
    }


}
