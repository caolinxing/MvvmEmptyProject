package com.zhulong.common.config;

/**
 * 应用模块: common
 * <p>
 * 类描述: 组件生命周期初始化类的类目管理者,在这里注册需要初始化的组件,通过反射动态调用各个组件的初始化方法
 * <p>
 */
public class ModuleLifecycleReflexs {
    /**
     * 基础库
     */
    private static final String BASE_INIT = "com.zhulong.common.CommonModuleInit";

    /**
     * main组件库初始化
     */
    private static final String MAIN_INIT = "com.zhulong.main.application.MainModuleInit";

    /**
     * 用户组件库初始化
     */
    private static final String MINE_INIT = "com.zhulong.mine.application.MainModuleInit";


    public static String[] initModuleNames = {BASE_INIT, MAIN_INIT,MINE_INIT};
}
