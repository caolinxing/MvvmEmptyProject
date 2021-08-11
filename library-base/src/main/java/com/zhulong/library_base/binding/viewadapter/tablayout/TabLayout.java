package com.zhulong.library_base.binding.viewadapter.tablayout;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zhulong.library_base.binding.command.BindingCommand;

import androidx.databinding.BindingAdapter;

/**
 * 应用模块:
 * <p>
 * 类描述: TabLayout 适配器
 * <p>
 *
 * @since: clx
 * @date: 2021/8/11
 */
public class TabLayout {
    @BindingAdapter(value = {"addTabData"}, requireAll = false)
    public static void addTabData(SegmentTabLayout tabLayout, String[] tabData) {
        tabLayout.setTabData(tabData);
    }

    @BindingAdapter(value = {"addTabSelectListener"}, requireAll = false)
    public static void addTabData(SegmentTabLayout tabLayout, final BindingCommand onTabSelectCommand) {
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (onTabSelectCommand != null) {
                    onTabSelectCommand.execute(position);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
