package com.judian.goule.store.bean;


import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * 首页轮播不数据
 */
@Table("BannerData")
public class BannerData implements Serializable {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String slide_id;
    private String slide_name;
    private String banner;
    private String slide_sort;
    private String url;
    private String num_iid;
    private String type;
    private String status;
    private String cate_id;
    private String explain;
    private String title;
    private String style;

    public String getSlide_id() {
        return slide_id;
    }

    public void setSlide_id(String slide_id) {
        this.slide_id = slide_id;
    }

    public String getSlide_name() {
        return slide_name;
    }

    public void setSlide_name(String slide_name) {
        this.slide_name = slide_name;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getSlide_sort() {
        return slide_sort;
    }

    public void setSlide_sort(String slide_sort) {
        this.slide_sort = slide_sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "BannerData{" +
                "id=" + id +
                ", slide_id='" + slide_id + '\'' +
                ", slide_name='" + slide_name + '\'' +
                ", banner='" + banner + '\'' +
                ", slide_sort='" + slide_sort + '\'' +
                ", url='" + url + '\'' +
                ", num_iid='" + num_iid + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", cate_id='" + cate_id + '\'' +
                ", explain='" + explain + '\'' +
                ", title='" + title + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
