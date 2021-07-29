package com.zhulong.network.bean.mine.login;

import java.io.Serializable;

/**
 * 应用模块:
 * <p>
 * 类描述: 用户信息
 * <p>
 *
 * @since: clx
 * @date: 2021/7/28
 */
public class UserInfoBean implements Serializable {
    private String uid;
    private String uname;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
