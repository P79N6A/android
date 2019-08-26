package com.judian.goule.store.bean;

import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/28.
 */
@Table("BannerBean")
public class BannerBean implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * data : [{"slide_id":"31","slide_name":"幻灯2","banner":"/Uploads/banner/2017-10-18/59e702af94796.jpg","slide_sort":"22","url":"https://s.click.taobao.com/r155naw","num_iid":"541115956763","type":"2","status":"1"},{"slide_id":"34","slide_name":"幻灯4","banner":"/Uploads/banner/2017-10-18/59e6b284094a0.jpg","slide_sort":"77","url":"www.baidu.com","num_iid":"4444","type":"2","status":"1"}]
     */
    @PrimaryKey(AssignType.BY_MYSELF)
    private String id;
    private int code;
    private String msg = "";
    @Mapping(Relation.ManyToMany)
    private ArrayList<BannerData> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BannerData> getData() {
        return data;
    }

    public void setData(ArrayList<BannerData> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * slide_id : 31
         * slide_name : 幻灯2
         * banner : /Uploads/banner/2017-10-18/59e702af94796.jpg
         * slide_sort : 22
         * url : https://s.click.taobao.com/r155naw
         * num_iid : 541115956763
         * type : 2
         * status : 1
         */

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

        public String getTitle() {

            if (title == null) {
                return "购了商城";
            } else {
                return title;
            }

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



        public String getSlide_id() {
            return slide_id;
        }

        public void setSlide_id(String slide_id) {
            this.slide_id = slide_id;
        }

        public String getSlide_name() {
            if (slide_name == null) return "购了商城";
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "slide_id='" + slide_id + '\'' +
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

    @Override
    public String toString() {
        return "BannerBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
