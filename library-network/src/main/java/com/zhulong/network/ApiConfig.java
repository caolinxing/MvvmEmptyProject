package com.zhulong.network;

public class ApiConfig {
    public static class ApiName {

        public static final String BASE_URL_EDU_NAME = "base_url_edu_name";
        public static final String BASE_URL_PASSPORT_NAME = "base_url_passport_name";
        public static final String BASE_URL_BBS_NAME = "bbs_url_passport_name";
        public static final String BASE_URL_F_NAME = "base_f_name";
        public static final String BASE_URL_THIRD_LOGIN_WX= "base_url_third_login_wx";
    }

    /**
     * 线上
     */
    public static class ApiUrl {
        public static final String BASE_EDU_URL = "http://edu.zhulong.com/";
        public static final String BASE_PASS_PORT = "http://passport.zhulong.com/";
        public static final String BASE_BBS_URL = "http://bbs.zhulong.com/";
        public static final String BASE_F_URL = "http://f.zhulong.com/";

        //获取openid，token接口
        public static final String BASE_LOGIN_WX = "http://api.weixin.qq.com/";
        //弹幕
        public static final String DAN_MU = "ws://ws.edu.zhulong.com:7272";
        //协议（付费）
        public static final String PAY_XIE_YI = "http://m.edu.zhulong.com/xieyi/eduxy.html?is_app=1";

    }
    /**
     * 线下
     */
    public static class ApiUrl_Xian_Xia {
        public static final String BASE_EDU_URL = "http://testedu.zhulong.com/";
        public static final String BASE_PASS_PORT = "http://testpassport.zhulong.com/";
        public static final String BASE_BBS_URL = "http://newgroup.zhulong.com/";
        public static final String BASE_F_URL = "https://f.zhulong.com/";


        //获取openid，token接口
        public static final String BASE_LOGIN_WX = "http://api.weixin.qq.com/";
    }

    /**
     * testNew环境
     */
    public static class ApiUrl_Test_New {
        public static final String BASE_EDU_URL = "http://testnewedu.zhulong.com/";
        public static final String BASE_PASS_PORT = "http://testnewpassport.zhulong.com/";
        public static final String BASE_BBS_URL = "http://newgroup.zhulong.com/";
        public static final String BASE_F_URL = "https://f.zhulong.com/";
        //获取openid，token接口
        public static final String BASE_LOGIN_WX = "http://api.weixin.qq.com/";
    }

}
