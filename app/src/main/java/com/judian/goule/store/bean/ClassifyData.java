package com.judian.goule.store.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;


@Table("ClassifyData")
public class ClassifyData implements Serializable {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String slide_id;
    private String slide_name;
    private String banner;
    private String slide_sort;
    private String url;
    private String num_iid;
    private String cate_id;
    private String type;
    private String status;
    private String copy;
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

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
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

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "ClassifyData{" +
                "id=" + id +
                ", slide_id='" + slide_id + '\'' +
                ", slide_name='" + slide_name + '\'' +
                ", banner='" + banner + '\'' +
                ", slide_sort='" + slide_sort + '\'' +
                ", url='" + url + '\'' +
                ", num_iid='" + num_iid + '\'' +
                ", cate_id='" + cate_id + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", copy='" + copy + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
