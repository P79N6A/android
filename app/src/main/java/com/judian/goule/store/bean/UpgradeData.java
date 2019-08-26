package com.judian.goule.store.bean;

public class UpgradeData {
    /**
     * code : 200
     * down_link : http://192.168.1.241 /download/app-release_goule_store.apk
     * versionCode : 2
     * type   0 普通更新  1 是 强制更新
     */

    private int code;
    private String down_link;
    private int versionCode;
    private String type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDown_link() {
        return down_link;
    }

    public void setDown_link(String down_link) {
        this.down_link = down_link;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UpgradeData{" +
                "code=" + code +
                ", down_link='" + down_link + '\'' +
                ", versionCode=" + versionCode +
                ", type='" + type + '\'' +
                '}';
    }
}
