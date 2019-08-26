package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class ExDetailBean {


    /**
     * code : 200
     * msg : 获取商品成功
     * result : {"id":"27","title":"唇彩","pict_url":"http://dongdong.com:81/Uploads/score/2017-11-10/5a04faa7aaa22.jpg","pict_url_two":"http://dongdong.com:81/Uploads/score/","item_url":"http://dongdong.com:81/Uploads/score/","price":"456.00","count":"225","require_points":"200","attr_name":"颜色","attr_value":"红色","start_time":"2017-11-11 02:27:00","end_time":"2017-11-11 02:09:00","status":"1","add_time":"2017-11-10 09:02:31","goods_type":["红色"]}
     */

    private int code;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 27
         * title : 唇彩
         * pict_url : http://dongdong.com:81/Uploads/score/2017-11-10/5a04faa7aaa22.jpg
         * pict_url_two : http://dongdong.com:81/Uploads/score/
         * item_url : http://dongdong.com:81/Uploads/score/
         * price : 456.00
         * count : 225
         * require_points : 200
         * attr_name : 颜色
         * attr_value : 红色
         * start_time : 2017-11-11 02:27:00
         * end_time : 2017-11-11 02:09:00
         * status : 1
         * add_time : 2017-11-10 09:02:31
         * goods_type : ["红色"]
         */

        private String id;
        private String title;
        private String pict_url;
        private String pict_url_two;
        private String item_url;
        private String price;
        private String count;
        private String require_points;
        private String attr_name;
        private String attr_value;
        private String start_time;
        private String end_time;
        private String status;

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        private String qq;
        private String add_time;
        private List<String> goods_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getPict_url_two() {
            return pict_url_two;
        }

        public void setPict_url_two(String pict_url_two) {
            this.pict_url_two = pict_url_two;
        }

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getRequire_points() {
            return require_points;
        }

        public void setRequire_points(String require_points) {
            this.require_points = require_points;
        }

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }

        public String getAttr_value() {
            return attr_value;
        }

        public void setAttr_value(String attr_value) {
            this.attr_value = attr_value;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public List<String> getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(List<String> goods_type) {
            this.goods_type = goods_type;
        }
    }
}
