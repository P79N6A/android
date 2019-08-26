package com.judian.goule.store.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class HomeCate implements Serializable {


    /**
     * shop_id : 0
     * cate_cid : 0
     * cate_id : 1
     * pic : cateadv/2017-04-17/58f4153bb9f28.jpg
     * app_pic : cateadv/2017-04-18/58f566be2d6ad.png
     * top_logo : cateadv/2017-04-17/58f4153bbaadf.jpg
     * intro : 新款女装，靓丽出行
     * cate_name : 潮流服装
     */

    private String shop_id;
    private String cate_cid;
    private String cate_id;
    private String pic;
    private String app_pic;
    private String top_logo;
    private String intro;
    private String cate_name;

    public HomeCate(int icon, String cate_name) {
        this.icon = icon;
        this.cate_name = cate_name;
    }

    public int getIcon() {

        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private int icon;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCate_cid() {
        return cate_cid;
    }

    public void setCate_cid(String cate_cid) {
        this.cate_cid = cate_cid;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getApp_pic() {
        return app_pic;
    }

    public void setApp_pic(String app_pic) {
        this.app_pic = app_pic;
    }

    public String getTop_logo() {
        return top_logo;
    }

    public void setTop_logo(String top_logo) {
        this.top_logo = top_logo;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }
}
