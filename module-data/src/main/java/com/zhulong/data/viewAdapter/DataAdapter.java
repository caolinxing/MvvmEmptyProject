package com.zhulong.data.viewAdapter;

import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.zhulong.library_base.binding.command.BindingCommand;

import androidx.databinding.BindingAdapter;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @since: clx
 * @date: 2021/8/11
 */
public class DataAdapter {
    @BindingAdapter(value = {"addTabData"},requireAll = false)
    public static void addTabData(SegmentTabLayout view, String[] tabData){
        view.setTabData(tabData);
    }
}
