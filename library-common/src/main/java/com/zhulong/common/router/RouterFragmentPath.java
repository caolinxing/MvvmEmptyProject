package com.zhulong.common.router;

/**
 * 应用模块: 组件化路由
 * <p>
 * 类描述: 用于组件化开发中,ARouter Fragment路径统一注册, 注册的路径请写好注释、标注业务功能
 * <p>
 *
 */
public class RouterFragmentPath
{
    
    /** 首页组件 */
    public static class Home
    {
        private static final String HOME = "/home";
        
        /** 首页 */
        public static final String PAGER_HOME = HOME + "/Home";
        
    }
    
    /** 课程 */
    public static class Lesson
    {
        private static final String LESSON = "/class";
        
        /** 课程页 */
        public static final String PAGER_LESSON = LESSON + "/Class";
    }
    
    /** Vip */
    public static class Vip
    {
        private static final String VIP = "/vip";
        
        /** Vip页面 */
        public static final String PAGER_VIP = VIP + "/Vip";
    }

    /** 资料 */
    public static class Data
    {
        private static final String DATA = "/data";
        
        /** 资料页面 */
        public static final String PAGER_DATA = DATA + "/Data";
    }
    
    /** 我的*/
    public static class Mine
    {
        private static final String MINE = "/mineCenter";

        /** 个人中心 */
        public static final String PAGER_MINE = MINE + "/MineCenter";
    }

}
