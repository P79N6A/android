package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class WXUserBean {


    /**
     * openid : oSUZv1HH1O_dVkTeKBbhcaFnvU4U
     * nickname : è¡£è
     * sex : 1
     * language : zh_CN
     * city : Jinan
     * province : Shandong
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/DoCDdfC1V1ZNO9ibWoyASNc9KPX5tLBEv6jybItpe9FWuGEnoE8pgOTwJ74XTdzAyHEWUDWBdG33Y50u7EiaDu4424FhcMWFCA/0
     * privilege : []
     * unionid : oeunywkkaqT6yKsgpBdzFV6kKBOU
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}