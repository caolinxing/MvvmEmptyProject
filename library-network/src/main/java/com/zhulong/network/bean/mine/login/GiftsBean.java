package com.zhulong.network.bean.mine.login;

import java.io.Serializable;

/**
 * @ProjectName: ZLKaoBang
 * @Package: com.zhulong.newtiku.bean
 * @ClassName: GiftsBean
 * @Description: li
 * @Author: CaoLinXing
 * @CreateDate: 2020/9/9 14:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/9 14:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GiftsBean implements Serializable {
    /**
     * id : 468
     * gift_name : 神火 （supfire）X20-S强光手电筒
     * img : http://newoss.zhulong.com/forum/202009/04/47/200547oaw4sywr8o9zyo6z.png
     * url : https://bbs.zhulong.com/9010_group_848/detail42701633/
     * introduce :
     * normal_price : 0
     * vip_price : 0
     * gold_vip_price : 0
     * source_price : 0
     */

    private String id;
    private String gift_name;
    private String img;
    private String url;
    private String introduce;
    private String normal_price;
    private String vip_price;
    private String gold_vip_price;
    private String source_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGift_name() {
        return gift_name;
    }

    public void setGift_name(String gift_name) {
        this.gift_name = gift_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getNormal_price() {
        return normal_price;
    }

    public void setNormal_price(String normal_price) {
        this.normal_price = normal_price;
    }

    public String getVip_price() {
        return vip_price;
    }

    public void setVip_price(String vip_price) {
        this.vip_price = vip_price;
    }

    public String getGold_vip_price() {
        return gold_vip_price;
    }

    public void setGold_vip_price(String gold_vip_price) {
        this.gold_vip_price = gold_vip_price;
    }

    public String getSource_price() {
        return source_price;
    }

    public void setSource_price(String source_price) {
        this.source_price = source_price;
    }
}
