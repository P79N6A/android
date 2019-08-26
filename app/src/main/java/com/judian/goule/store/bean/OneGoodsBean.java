package com.judian.goule.store.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/25.
 */

public class OneGoodsBean {

    /**
     * errcode : 200
     * errmsg : 获取数据成功
     * result : {"id":"7","cate_id":"0","two_cate_id":"0","favorites_id":"0","num_iid":"","title":"自营测试","pict_url":"null","item_url":"","shop_title":null,"price":"3455.00","month_sales":"222","income_scale":null,"income_commission":"0.00","active_status":null,"active_inc_scale":null,"active_commission":null,"start_time":null,"end_time":null,"seller_ww":null,"taoke_short_link":null,"taoke_link":null,"command":null,"coupon_number":null,"coupon_surplus":"0","coupon_money":null,"coupon_start_time":null,"coupon_end_time":null,"coupon_link":"","coupon_command":"","coupon_short_link":"","status":"1","add_time":"1513736026","update_time":"1513760368","is_hot":"1","on_sale":"1","reserve_price":"23534.00","user_type":null,"small_images":null,"tk_rate":null,"is_explosion":"1","stock":"3445","content":null,"belong":"1","is_mail":"1","sharing_knock":"","is_year":"1"}
     */

    private int errcode;
    private String errmsg;
    private ResultBean result;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * id : 7
         * cate_id : 0
         * two_cate_id : 0
         * favorites_id : 0
         * num_iid :
         * title : 自营测试
         * pict_url : null
         * item_url :
         * shop_title : null
         * price : 3455.00
         * month_sales : 222
         * income_scale : null
         * income_commission : 0.00
         * active_status : null
         * active_inc_scale : null
         * active_commission : null
         * start_time : null
         * end_time : null
         * seller_ww : null
         * taoke_short_link : null
         * taoke_link : null
         * command : null
         * coupon_number : null
         * coupon_surplus : 0
         * coupon_money : null
         * coupon_start_time : null
         * coupon_end_time : null
         * coupon_link :
         * coupon_command :
         * coupon_short_link :
         * status : 1
         * add_time : 1513736026
         * update_time : 1513760368
         * is_hot : 1
         * on_sale : 1
         * reserve_price : 23534.00
         * user_type : null
         * small_images : null
         * tk_rate : null
         * is_explosion : 1
         * stock : 3445
         * content : null
         * belong : 1
         * is_mail : 1
         * sharing_knock :
         * is_year : 1
         */

        private String id;
        private String cate_id;
        private String two_cate_id;
        private String favorites_id;
        private String num_iid;
        private String title;
        private String pict_url;
        private String item_url;

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        private String intro;

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        private String introduce;
        private Object shop_title;
        private String price;
        private String month_sales;
        private Object income_scale;
        private String income_commission;
        private Object active_status;
        private Object active_inc_scale;
        private Object active_commission;
        private Object start_time;
        private Object end_time;
        private Object seller_ww;
        private Object taoke_short_link;
        private Object taoke_link;
        private Object command;
        private Object coupon_number;
        private String coupon_surplus;
        private Object coupon_money;
        private Object coupon_start_time;
        private Object coupon_end_time;
        private String coupon_link;
        private String coupon_command;
        private String coupon_short_link;
        private String status;
        private String add_time;
        private String update_time;
        private String is_hot;
        private String on_sale;
        private String reserve_price;
        private Object user_type;
        private Object small_images;
        private Object tk_rate;
        private String is_explosion;
        private String stock;
        private String content;
        private String belong;
        private String is_mail;
        private String sharing_knock;
        private String is_year;

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

        public String getFavorites_id() {
            return favorites_id;
        }

        public void setFavorites_id(String favorites_id) {
            this.favorites_id = favorites_id;
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

        public Object getShop_title() {
            return shop_title;
        }

        public void setShop_title(Object shop_title) {
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

        public Object getIncome_scale() {
            return income_scale;
        }

        public void setIncome_scale(Object income_scale) {
            this.income_scale = income_scale;
        }

        public String getIncome_commission() {
            return income_commission;
        }

        public void setIncome_commission(String income_commission) {
            this.income_commission = income_commission;
        }

        public Object getActive_status() {
            return active_status;
        }

        public void setActive_status(Object active_status) {
            this.active_status = active_status;
        }

        public Object getActive_inc_scale() {
            return active_inc_scale;
        }

        public void setActive_inc_scale(Object active_inc_scale) {
            this.active_inc_scale = active_inc_scale;
        }

        public Object getActive_commission() {
            return active_commission;
        }

        public void setActive_commission(Object active_commission) {
            this.active_commission = active_commission;
        }

        public Object getStart_time() {
            return start_time;
        }

        public void setStart_time(Object start_time) {
            this.start_time = start_time;
        }

        public Object getEnd_time() {
            return end_time;
        }

        public void setEnd_time(Object end_time) {
            this.end_time = end_time;
        }

        public Object getSeller_ww() {
            return seller_ww;
        }

        public void setSeller_ww(Object seller_ww) {
            this.seller_ww = seller_ww;
        }

        public Object getTaoke_short_link() {
            return taoke_short_link;
        }

        public void setTaoke_short_link(Object taoke_short_link) {
            this.taoke_short_link = taoke_short_link;
        }

        public Object getTaoke_link() {
            return taoke_link;
        }

        public void setTaoke_link(Object taoke_link) {
            this.taoke_link = taoke_link;
        }

        public Object getCommand() {
            return command;
        }

        public void setCommand(Object command) {
            this.command = command;
        }

        public Object getCoupon_number() {
            return coupon_number;
        }

        public void setCoupon_number(Object coupon_number) {
            this.coupon_number = coupon_number;
        }

        public String getCoupon_surplus() {
            return coupon_surplus;
        }

        public void setCoupon_surplus(String coupon_surplus) {
            this.coupon_surplus = coupon_surplus;
        }

        public Object getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(Object coupon_money) {
            this.coupon_money = coupon_money;
        }

        public Object getCoupon_start_time() {
            return coupon_start_time;
        }

        public void setCoupon_start_time(Object coupon_start_time) {
            this.coupon_start_time = coupon_start_time;
        }

        public Object getCoupon_end_time() {
            return coupon_end_time;
        }

        public void setCoupon_end_time(Object coupon_end_time) {
            this.coupon_end_time = coupon_end_time;
        }

        public String getCoupon_link() {
            return coupon_link;
        }

        public void setCoupon_link(String coupon_link) {
            this.coupon_link = coupon_link;
        }

        public String getCoupon_command() {
            return coupon_command;
        }

        public void setCoupon_command(String coupon_command) {
            this.coupon_command = coupon_command;
        }

        public String getCoupon_short_link() {
            return coupon_short_link;
        }

        public void setCoupon_short_link(String coupon_short_link) {
            this.coupon_short_link = coupon_short_link;
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

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getOn_sale() {
            return on_sale;
        }

        public void setOn_sale(String on_sale) {
            this.on_sale = on_sale;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
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

        public Object getTk_rate() {
            return tk_rate;
        }

        public void setTk_rate(Object tk_rate) {
            this.tk_rate = tk_rate;
        }

        public String getIs_explosion() {
            return is_explosion;
        }

        public void setIs_explosion(String is_explosion) {
            this.is_explosion = is_explosion;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }

        public String getIs_mail() {
            return is_mail;
        }

        public void setIs_mail(String is_mail) {
            this.is_mail = is_mail;
        }

        public String getSharing_knock() {
            return sharing_knock;
        }

        public void setSharing_knock(String sharing_knock) {
            this.sharing_knock = sharing_knock;
        }

        public String getIs_year() {
            return is_year;
        }

        public void setIs_year(String is_year) {
            this.is_year = is_year;
        }
    }
}
