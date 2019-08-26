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
@Table("MsBean")
public class MsBean implements Serializable {


    /**
     * code : 200
     * msg : 获取成功
     * result : [{"slide_id":"39","slide_name":"今日主推1","banner":"/Uploads/banner/2017-10-24/59eef626be1d2.jpg","slide_sort":"53","url":"","num_iid":"","cate_id":"48","type":"3","status":"1"},{"slide_id":"40","slide_name":"今日主推2","banner":"/Uploads/banner/2017-10-24/59eef655b6639.jpg","slide_sort":"0","url":"","num_iid":"78903762","cate_id":"0","type":"3","status":"1"},{"slide_id":"41","slide_name":"今日主推3","banner":"/Uploads/banner/2017-10-24/59eef68f61f4f.jpg","slide_sort":"0","url":"www.baidu.com","num_iid":"","cate_id":"0","type":"3","status":"1"},{"slide_id":"42","slide_name":"今日主推4","banner":"/Uploads/banner/2017-10-24/59eef6a7725c4.jpg","slide_sort":"11","url":"","num_iid":"","cate_id":"9","type":"3","status":"1"},{"slide_id":"43","slide_name":"今日主推5","banner":"/Uploads/banner/2017-10-24/59eef6bcba808.jpg","slide_sort":"0","url":"","num_iid":"444444","cate_id":"0","type":"3","status":"1"}]
     */
    @PrimaryKey(AssignType.BY_MYSELF)
    private String id;
    private int code;
    private String msg = "";
    private String type = ""; //  1 分类     2  分成类，
    @Mapping(Relation.ManyToMany)
    private ArrayList<ClassifyData> result;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ClassifyData> getResult() {
        return result;
    }

    public void setResult(ArrayList<ClassifyData> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * slide_id : 39
         * slide_name : 今日主推1
         * banner : /Uploads/banner/2017-10-24/59eef626be1d2.jpg
         * slide_sort : 53
         * url :
         * num_iid :
         * cate_id : 48
         * type : 3
         * status : 1
         */

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

        public String getCopy() {
            return copy;
        }

        public void setCopy(String copy) {
            this.copy = copy;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        private String explain;

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

        @Override
        public String toString() {
            return "ResultBean{" +
                    "slide_id='" + slide_id + '\'' +
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
                    ", explain='" + explain + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MsBean{" +
                "id='" + id + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", type='" + type + '\'' +
                ", result=" + result +
                '}';
    }
}
