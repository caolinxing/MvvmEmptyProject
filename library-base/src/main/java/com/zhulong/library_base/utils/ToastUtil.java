package com.zhulong.library_base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhulong.library_base.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;


/**
 * Toast弹窗工具类
 * 防止Toast重复出现，添加控制。
 * 并统一弹窗样式：SUCCESS 成功，FAIL 失败，WARNING 警告。
 */
public class ToastUtil {

    public static final int FAIL = -1;
    public static final int WARNING = 0;
    public static final int SUCCESS = 1;
    public static final int SMILE = 2;

    private static Toast mToast;

    @IntDef({FAIL, SUCCESS, WARNING, SMILE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ToastType {
    }

    /**
     * 带图片和文字的Toast顶部图片，图片下面是文字
     */
    public static Toast showToastByType(Context context, @ToastType int typeId, String toastMessage) {
        //获取图片id
        int picDrawableRes = getDrawable(typeId);
        return showToast(context, picDrawableRes, toastMessage);
    }

    public static Toast showToast(Context context,  String toastMessage) {
        if (context == null)
            return null;
        if (mToast == null) {
            Context applicationContext = context.getApplicationContext();
            mToast = new Toast(applicationContext);
            LayoutInflater inflate = (LayoutInflater)
                    applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mToast.setView(inflate.inflate(R.layout.base_layout_nopic_toast, null));
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            mToast.cancel();
            Context applicationContext = context.getApplicationContext();
            mToast = new Toast(applicationContext);
            LayoutInflater inflate = (LayoutInflater)
                    applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mToast.setView(inflate.inflate(R.layout.base_layout_nopic_toast, null));
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }

        View view = mToast.getView();
        TextView tvMessage =  view.findViewById(R.id.tv_message);
        toastMessage = TextUtils.isEmpty(toastMessage) ? "出错了，请重试" : toastMessage;
        tvMessage.setText(toastMessage);

        mToast.show();
        return mToast;
    }


    /**
     * 带图片和文字的Toast顶部图片，图片下面是文字
     * 可以根据picDrawableRes自定义Toast类型。
     */
    public static Toast showToast(Context context, @DrawableRes int picDrawableRes, String toastMessage) {
        if (context == null)
            return null;
        if (mToast == null) {
            Context applicationContext = context.getApplicationContext();
            mToast = new Toast(applicationContext);
            LayoutInflater inflate = (LayoutInflater)
                    applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mToast.setView(inflate.inflate(R.layout.base_layout_custom_toast, null));
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            mToast.cancel();
            Context applicationContext = context.getApplicationContext();
            mToast = new Toast(applicationContext);
            LayoutInflater inflate = (LayoutInflater)
                    applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mToast.setView(inflate.inflate(R.layout.base_layout_custom_toast, null));
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }

        View view = mToast.getView();
        ImageView ivPic = (ImageView) view.findViewById(R.id.iv_pic);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        if (picDrawableRes != 0) {
            ivPic.setImageDrawable(ContextCompat.getDrawable(context, picDrawableRes));
        } else {
            ivPic.setVisibility(View.GONE);
        }
        toastMessage = TextUtils.isEmpty(toastMessage) ? "出错了，请重试" : toastMessage;
        tvMessage.setText(toastMessage);

        mToast.show();
        return mToast;
    }

    /**
     * 手动取消Toast显示
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

    /**
     * 释放Toast的静态引用
     */
    public static void release() {
        mToast = null;
    }

    /**
     * 根据Type返回对应样式图片id
     * 默认是警告样式 WARNING
     *
     * @param type 类型
     * @return 图片id
     */
    private static int getDrawable(int type) {
        int drawableId;
        switch (type) {
            case SUCCESS:
                drawableId = R.drawable.base_ic_toast_ok;
                break;
            case FAIL:
                drawableId = R.drawable.base_ic_toast_cancel;
                break;
            case SMILE:
                drawableId = R.drawable.base_ic_toast_face;
                break;
            default:
                drawableId = R.drawable.base_ic_toast_warning;
                break;
        }
        return drawableId;
    }

}
