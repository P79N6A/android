package com.judian.goule.store.other;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class CommodityBean {

    /**
     * code : 200
     * msg : 获取成功
     * result : [{"id":"17","title":"衣服","pict_url":"Uploads/goods/2017-11-07/5a0142fd5351f.png","pict_url_two":"","item_url":"","price":"555.00","count":"55","require_points":"555","attr_name":"颜色","attr_value":"黑色,xl","start_time":"2017-11-07 00:00:00","end_time":"2017-11-09 00:00:00","status":"1","add_time":"2017-11-07 13:22:05"},{"id":"16","title":"饼干","pict_url":"Uploads/goods/2017-11-07/5a01424e79a75.png","pict_url_two":"Uploads/goods/2017-11-07/5a014259f1eca.jpg","item_url":"Uploads/goods/2017-11-07/5a014240f1df8.jpg","price":"900.00","count":"8000","require_points":"100","attr_name":"口味","attr_value":"巧克力,奶油","start_time":"2017-11-07 13:19:21","end_time":"2017-11-16 00:00:00","status":"1","add_time":"2017-11-07 11:27:31"},{"id":"15","title":"aa","pict_url":"Uploads/goods/2017-11-07/5a01275b893f9.jpg","pict_url_two":"Uploads/goods/2017-11-07/5a01275b8b667.jpg","item_url":"Uploads/goods/2017-11-07/5a01275b8cf17.jpg","price":"0.00","count":"ff","require_points":"0","attr_name":"口味","attr_value":"","start_time":"2017-11-08 00:00:00","end_time":"2017-11-11 00:00:00","status":"1","add_time":"2017-11-07 11:24:11"},{"id":"14","title":"康师傅","pict_url":"www.kangshifu.com","pict_url_two":"","item_url":"jjj","price":"12.00","count":"444","require_points":"33","attr_name":"","attr_value":"","start_time":"2017-11-09 00:00:00","end_time":"2017-11-17 00:00:00","status":"1","add_time":"2017-11-03 14:26:43"},{"id":"13","title":"玉米","pict_url":"http://www.dongdong.com/Uploads/share/61508979557.png","pict_url_two":"","item_url":"http://www.dongdong.com/Uploads/share/61508979557.png","price":"33.00","count":"34","require_points":"234","attr_name":"","attr_value":"","start_time":"2017-11-03 15:26:02","end_time":"2017-11-03 00:00:00","status":"1","add_time":"2017-10-26 09:05:31"},{"id":"12","title":"dfdfgd","pict_url":"http://www.dongdong.com/Uploads/share/61508979557.png","pict_url_two":"","item_url":"http://www.dongdong.com/Uploads/share/61508979557.png","price":"200.00","count":"55","require_points":"100","attr_name":"","attr_value":"","start_time":"2017-10-16 00:00:00","end_time":"2017-10-24 00:00:00","status":"1","add_time":"2017-10-26 09:03:39"},{"id":"11","title":"343","pict_url":"http://www.dongdong.com/Uploads/share/61508979557.png","pict_url_two":"","item_url":"http://www.dongdong.com/Uploads/share/61508979557.png","price":"34.00","count":"34","require_points":"34","attr_name":"","attr_value":"","start_time":"0000-00-00 00:00:00","end_time":"0000-00-00 00:00:00","status":"1","add_time":"2017-10-23 10:20:28"},{"id":"10","title":"3434","pict_url":"http://www.dongdong.com/Uploads/share/61508979557.png","pict_url_two":"","item_url":"http://www.dongdong.com/Uploads/share/61508979557.png","price":"545.00","count":"545","require_points":"54","attr_name":"","attr_value":"","start_time":"0000-00-00 00:00:00","end_time":"0000-00-00 00:00:00","status":"1","add_time":"2017-10-23 10:20:09"},{"id":"9","title":"232","pict_url":"http://www.dongdong.com/Uploads/share/61508979557.png","pict_url_two":"","item_url":"http://www.dongdong.com/Uploads/share/61508979557.png","price":"231.00","count":"323","require_points":"232","attr_name":"","attr_value":"","start_time":"0000-00-00 00:00:00","end_time":"0000-00-00 00:00:00","status":"1","add_time":"2017-10-23 10:19:20"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 17
         * title : 衣服
         * pict_url : Uploads/goods/2017-11-07/5a0142fd5351f.png
         * pict_url_two :
         * item_url :
         * price : 555.00
         * count : 55
         * require_points : 555
         * attr_name : 颜色
         * attr_value : 黑色,xl
         * start_time : 2017-11-07 00:00:00
         * end_time : 2017-11-09 00:00:00
         * status : 1
         * add_time : 2017-11-07 13:22:05
         */

        private String id;
        private String title;
        private String pict_url;
        private String pict_url_two;
        private String item_url;
        private String price;
        private int count;
        private String require_points;
        private String attr_name;
        private String attr_value;
        private String start_time;
        private String end_time;
        private String status;
        private String add_time;

        public int getCount_num() {
            return count_num;
        }

        public void setCount_num(int count_num) {
            this.count_num = count_num;
        }

        private int count_num;

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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
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
    }
}
