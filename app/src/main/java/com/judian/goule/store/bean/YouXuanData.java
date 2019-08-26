package com.judian.goule.store.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;


@Table("YouXuanData")
public class YouXuanData implements Serializable {
    @PrimaryKey(AssignType.BY_MYSELF)
    private String ad_id;
    private String name;
    private String type_id;
    private String pic;
    private String url;
    private String addtime;
    private String sort;
    private String open;
    private String content;
    private String type;
    private String style;

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "YouXuanData{" +
                "ad_id='" + ad_id + '\'' +
                ", name='" + name + '\'' +
                ", type_id='" + type_id + '\'' +
                ", pic='" + pic + '\'' +
                ", url='" + url + '\'' +
                ", addtime='" + addtime + '\'' +
                ", sort='" + sort + '\'' +
                ", open='" + open + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
