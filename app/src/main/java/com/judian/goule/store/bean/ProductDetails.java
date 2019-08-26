package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/10/30.
 */

public class ProductDetails {


    /**
     * code : 200
     * msg : 查询成功
     * result : {"id":"563","num_iid":"559686933517","title":"中老年人雷锋帽  男士冬季加厚保暖户外护耳棉帽老人鸭舌皮质帽子","pict_url":"http://img.alicdn.com/bao/uploaded/i4/2152357234/TB2DcDdf6uhSKJjSspdXXc11XXa_!!2152357234.jpg","item_url":"http://item.taobao.com/item.htm?id=559686933517","price":"80.00","month_sales":"0","coupon_money":"无","reserve_price":"80.00","small_images":"{\"string\":[\"http://img1.tbcdn.cn/tfscom/i1/2152357234/TB2OzgEffBNTKJjy1zdXXaScpXa_!!2152357234.jpg\",\"http://img2.tbcdn.cn/tfscom/i4/2152357234/TB2AZ72j3oQMeJjy0FnXXb8gFXa_!!2152357234.jpg\",\"http://img4.tbcdn.cn/tfscom/i1/2152357234/TB2GCQIffBNTKJjSszeXXcu2VXa_!!2152357234.jpg\",\"\"]}","active_inc_scale":"60.00","user_type":"0","have_coupon":0,"fali_money":14.4}
     */

    private String code;
    private String msg;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * id : 563
         * num_iid : 559686933517
         * title : 中老年人雷锋帽  男士冬季加厚保暖户外护耳棉帽老人鸭舌皮质帽子
         * pict_url : http://img.alicdn.com/bao/uploaded/i4/2152357234/TB2DcDdf6uhSKJjSspdXXc11XXa_!!2152357234.jpg
         * item_url : http://item.taobao.com/item.htm?id=559686933517
         * price : 80.00
         * month_sales : 0
         * coupon_money : 无
         * reserve_price : 80.00
         * small_images : {"string":["http://img1.tbcdn.cn/tfscom/i1/2152357234/TB2OzgEffBNTKJjy1zdXXaScpXa_!!2152357234.jpg","http://img2.tbcdn.cn/tfscom/i4/2152357234/TB2AZ72j3oQMeJjy0FnXXb8gFXa_!!2152357234.jpg","http://img4.tbcdn.cn/tfscom/i1/2152357234/TB2GCQIffBNTKJjSszeXXcu2VXa_!!2152357234.jpg",""]}
         * active_inc_scale : 60.00
         * user_type : 0
         * have_coupon : 0
         * fali_money : 14.4
         */

        private String id;

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        private String love;
        private String num_iid;
        private String title;
        private String pict_url;
        private String item_url;
        private String price;
        private String month_sales;
        private String coupon_money;
        private String reserve_price;
        private String small_images;
        private String active_inc_scale;
        private String user_type;
        private int have_coupon;

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        private String introduce;

        public String getFanli_money() {
            return fanli_money;
        }

        public void setFanli_money(String fanli_money) {
            this.fanli_money = fanli_money;
        }

        private String fanli_money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public String getSmall_images() {
            return small_images;
        }

        public void setSmall_images(String small_images) {
            this.small_images = small_images;
        }

        public String getActive_inc_scale() {
            return active_inc_scale;
        }

        public void setActive_inc_scale(String active_inc_scale) {
            this.active_inc_scale = active_inc_scale;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public int getHave_coupon() {
            return have_coupon;
        }

        public void setHave_coupon(int have_coupon) {
            this.have_coupon = have_coupon;
        }


    }
}

