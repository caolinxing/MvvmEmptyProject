package com.zhulong.library_base.binding.viewadapter.image;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhulong.library_base.view.CircleImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

/**
 * Created by goldze on 2017/6/18.
 */
public final class ViewAdapter {

    @BindingAdapter(value = "civ_border_color")
    public static void setBorderColor(CircleImageView circleImageView, int placeholderRes) {
        circleImageView.setBorderColor(ContextCompat.getColor(circleImageView.getContext(),placeholderRes));
    }

    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholderRes))
                    .into(imageView);
        }else{
            Glide.with(imageView.getContext())
                    .load(placeholderRes)
                    .apply(new RequestOptions().placeholder(placeholderRes))
                    .into(imageView);
        }
    }
    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, int url, int placeholderRes) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholderRes))
                    .error(placeholderRes)
                    .into(imageView);
    }


    @BindingAdapter(value = {"animatorTime"})
    public static void objectAnimator(View view,int animatorTime) {
        ObjectAnimator cardAnimator = ObjectAnimator.ofFloat(view, "translationY", 0, 20);
        cardAnimator.setDuration(animatorTime);
        cardAnimator.setInterpolator(new LinearInterpolator());
        cardAnimator.setRepeatMode(ValueAnimator.REVERSE);
        cardAnimator.setRepeatCount(5);
        cardAnimator.start();
    }


}

