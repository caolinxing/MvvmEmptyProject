package com.zhulong.network.config;

import java.io.Serializable;

/**
 * 应用模块:
 * <p>
 * 类描述: 公参和Cookie
 * <p>
 *
 * @since: clx
 * @date: 2021/7/7
 */
public class CookieBean implements Serializable {
    private String uid;
    private String zlid;
    private String is_corp;
    private String pcid;
    private String username;
    private String specialty_id;


    public String getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(String specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getZlid() {
        return zlid;
    }

    public void setZlid(String zlid) {
        this.zlid = zlid;
    }

    public String getIs_corp() {
        return is_corp;
    }

    public void setIs_corp(String is_corp) {
        this.is_corp = is_corp;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
