package com.zhulong.common.router;

/**
 * 应用模块: 组件化路由
 * <p>
 * 类描述: 用于在组件化开发中,利用ARouter 进行Activity跳转的统一路径注册, 注册时请写好注释、标注界面功能业务
 * <p>
 *
 */
public class RouterActivityPath {

    /** 首页组件 */
    public static class Home {

        private static final String HOME = "/home";


    }

    /** 课程 */
    public static class Lesson {

        private static final String LESSON = "/class";

    }

    /** Vip */
    public static class Vip {

        private static final String VIP = "/vip";

    }

    /** 资料 */
    public static class Data {

        private static final String DATA = "/data";
    }

    /** 我的*/
    public static class Mine {

        private static final String MINE = "/mineCenter";

        /** 登录页 */
        public static final String PAGER_MINE_LOGIN = MINE + "/Login";
    }
}
