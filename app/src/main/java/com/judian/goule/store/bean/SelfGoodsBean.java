package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class SelfGoodsBean {


    /**
     * code : 200
     * msg : “ggggg”
     * result : [{"id":"189030","cate_id":"104","two_cate_id":"105","num_iid":"","title":"测试商品","pict_url":"/Uploads/admin/1520581375_33088154.jpg","item_url":"","shop_title":"","price":"20.00","month_sales":"300","active_inc_scale":"0.00","active_commission":"0.00","start_time":"0000-00-00 00:00:00","end_time":"0000-00-00 00:00:00","coupon_number":"0","coupon_surplus":"0","couponamount":"0","coupon_money":"0","coupon_start_time":"0","coupon_end_time":"0","status":"1","shangxin":"0","zhutui":"0","zhutui_time":"0000-00-00 00:00:00","add_time":"0000-00-00 00:00:00","update_time":"0000-00-00 00:00:00","reserve_price":"300.00","coupon_price":"0.00","user_type":null,"small_images":null,"tk_rate":"0.00","coupon_id":"","picture":"","copy":"","introduce":"","type":"1","sort":null,"my_type":"0","click_num":"0","my_type_time":null,"belong":"1","on_sale":"1"}]
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
         * id : 189030
         * cate_id : 104
         * two_cate_id : 105
         * num_iid :
         * title : 测试商品
         * pict_url : /Uploads/admin/1520581375_33088154.jpg
         * item_url :
         * shop_title :
         * price : 20.00
         * month_sales : 300
         * active_inc_scale : 0.00
         * active_commission : 0.00
         * start_time : 0000-00-00 00:00:00
         * end_time : 0000-00-00 00:00:00
         * coupon_number : 0
         * coupon_surplus : 0
         * couponamount : 0
         * coupon_money : 0
         * coupon_start_time : 0
         * coupon_end_time : 0
         * status : 1
         * shangxin : 0
         * zhutui : 0
         * zhutui_time : 0000-00-00 00:00:00
         * add_time : 0000-00-00 00:00:00
         * update_time : 0000-00-00 00:00:00
         * reserve_price : 300.00
         * coupon_price : 0.00
         * user_type : null
         * small_images : null
         * tk_rate : 0.00
         * coupon_id :
         * picture :
         * copy :
         * introduce :
         * type : 1
         * sort : null
         * my_type : 0
         * click_num : 0
         * my_type_time : null
         * belong : 1
         * on_sale : 1
         */

        private String id;
        private String cate_id;

        public String getIntro() {
            if (intro==null)return "";
            else
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        private String intro;
        private String two_cate_id;
        private String num_iid;
        private String title;
        private String pict_url;
        private String item_url;
        private String shop_title;
        private String price;
        private String month_sales;
        private String active_inc_scale;
        private String active_commission;
        private String start_time;
        private String end_time;
        private String coupon_number;
        private String coupon_surplus;
        private String couponamount;
        private String coupon_money;
        private String coupon_start_time;
        private String coupon_end_time;
        private String status;
        private String shangxin;
        private String zhutui;
        private String zhutui_time;
        private String add_time;
        private String update_time;
        private String reserve_price;
        private String coupon_price;
        private Object user_type;
        private Object small_images;
        private String tk_rate;
        private String coupon_id;
        private String picture;
        private String copy;
        private String introduce;
        private String type;
        private Object sort;
        private String my_type;
        private String click_num;
        private Object my_type_time;
        private String belong;
        private String on_sale;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getTwo_cate_id() {
            return two_cate_id;
        }

        public void setTwo_cate_id(String two_cate_id) {
            this.two_cate_id = two_cate_id;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
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

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMonth_sales() {
            return month_sales;
        }

        public void setMonth_sales(String month_sales) {
            this.month_sales = month_sales;
        }

        public String getActive_inc_scale() {
            return active_inc_scale;
        }

        public void setActive_inc_scale(String active_inc_scale) {
            this.active_inc_scale = active_inc_scale;
        }

        public String getActive_commission() {
            return active_commission;
        }

        public void setActive_commission(String active_commission) {
            this.active_commission = active_commission;
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

        public String getCoupon_number() {
            return coupon_number;
        }

        public void setCoupon_number(String coupon_number) {
            this.coupon_number = coupon_number;
        }

        public String getCoupon_surplus() {
            return coupon_surplus;
        }

        public void setCoupon_surplus(String coupon_surplus) {
            this.coupon_surplus = coupon_surplus;
        }

        public String getCouponamount() {
            return couponamount;
        }

        public void setCouponamount(String couponamount) {
            this.couponamount = couponamount;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        public String getCoupon_start_time() {
            return coupon_start_time;
        }

        public void setCoupon_start_time(String coupon_start_time) {
            this.coupon_start_time = coupon_start_time;
        }

        public String getCoupon_end_time() {
            return coupon_end_time;
        }

        public void setCoupon_end_time(String coupon_end_time) {
            this.coupon_end_time = coupon_end_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShangxin() {
            return shangxin;
        }

        public void setShangxin(String shangxin) {
            this.shangxin = shangxin;
        }

        public String getZhutui() {
            return zhutui;
        }

        public void setZhutui(String zhutui) {
            this.zhutui = zhutui;
        }

        public String getZhutui_time() {
            return zhutui_time;
        }

        public void setZhutui_time(String zhutui_time) {
            this.zhutui_time = zhutui_time;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public String getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        public Object getUser_type() {
            return user_type;
        }

        public void setUser_type(Object user_type) {
            this.user_type = user_type;
        }

        public Object getSmall_images() {
            return small_images;
        }

        public void setSmall_images(Object small_images) {
            this.small_images = small_images;
        }

        public String getTk_rate() {
            return tk_rate;
        }

        public void setTk_rate(String tk_rate) {
            this.tk_rate = tk_rate;
        }

        public String getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(String coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getCopy() {
            return copy;
        }

        public void setCopy(String copy) {
            this.copy = copy;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public String getMy_type() {
            return my_type;
        }

        public void setMy_type(String my_type) {
            this.my_type = my_type;
        }

        public String getClick_num() {
            return click_num;
        }

        public void setClick_num(String click_num) {
            this.click_num = click_num;
        }

        public Object getMy_type_time() {
            return my_type_time;
        }

        public void setMy_type_time(Object my_type_time) {
            this.my_type_time = my_type_time;
        }

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }

        public String getOn_sale() {
            return on_sale;
        }

        public void setOn_sale(String on_sale) {
            this.on_sale = on_sale;
        }
    }
}
