package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class LoveBean {


    /**
     * code : 200
     * msg : 搜索成功
     * result : [{"id":"175","user_id":"13","goods_id":"559686933517","sc_time":"2017-11-01 09:41:25","status":"1","reserve_price":"80.00","num_iid":"559686933517","price":"80.00","title":"中老年人雷锋帽  男士冬季加厚保暖户外护耳棉帽老人鸭舌皮质帽子","pict_url":"http://img.alicdn.com/bao/uploaded/i4/2152357234/TB2DcDdf6uhSKJjSspdXXc11XXa_!!2152357234.jpg","month_sales":"0","have_coupon":0,"rebate_money":14.4},{"id":"170","user_id":"13","goods_id":"548190417455","sc_time":"2017-11-01 09:35:59","status":"1","reserve_price":"129.50","num_iid":"548190417455","price":"9.99","title":"春秋男士百搭牛仔裤男中腰宽松直筒个性青年中腰韩版男装长裤子潮","pict_url":"http://img.alicdn.com/bao/uploaded/i3/2853982213/TB2jK.JkMFkpuFjSspnXXb4qFXa_!!2853982213.jpg","month_sales":"2","have_coupon":1,"coupon_money":"3","rebate_money":2.7},{"id":"167","user_id":"13","goods_id":"557070516767","sc_time":"2017-11-01 09:26:41","status":"1","reserve_price":"229.00","num_iid":"557070516767","price":"96.00","title":"美帽秋冬新款羊毛礼帽女 麂皮麻花辫时装盆帽 欧美时尚水滴顶帽子","pict_url":"http://img.alicdn.com/bao/uploaded/i1/1016564428/TB2CdHnXBOBJuJjy1XdXXXIXVXa_!!1016564428.jpg","month_sales":"6","have_coupon":0,"rebate_money":25.92},{"id":"166","user_id":"13","goods_id":"554315257387","sc_time":"2017-11-01 09:26:33","status":"1","reserve_price":"72.50","num_iid":"554315257387","price":"35.00","title":"正品潮牌棒球帽子男夏韩版嘻哈帽潮人鸭舌帽街头百搭平沿帽遮阳帽","pict_url":"http://img.alicdn.com/bao/uploaded/i1/429467561/TB24iYqzbBmpuFjSZFAXXaQ0pXa_!!429467561.jpg","month_sales":"70","have_coupon":0,"rebate_money":5.25}]
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
         * id : 175
         * user_id : 13
         * goods_id : 559686933517
         * sc_time : 2017-11-01 09:41:25
         * status : 1
         * reserve_price : 80.00
         * num_iid : 559686933517
         * price : 80.00
         * title : 中老年人雷锋帽  男士冬季加厚保暖户外护耳棉帽老人鸭舌皮质帽子
         * pict_url : http://img.alicdn.com/bao/uploaded/i4/2152357234/TB2DcDdf6uhSKJjSspdXXc11XXa_!!2152357234.jpg
         * month_sales : 0
         * have_coupon : 0
         * rebate_money : 14.4
         * coupon_money : 3
         */

        private String id;
        private String user_id;
        private String goods_id;
        private String sc_time;
        private String status;
        private String reserve_price;
        private String num_iid;
        private String price;
        private String title;
        private String pict_url;
        private String month_sales;
        private int have_coupon;
        private String rebate_money;
        private String coupon_money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getSc_time() {
            return sc_time;
        }

        public void setSc_time(String sc_time) {
            this.sc_time = sc_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getMonth_sales() {
            return month_sales;
        }

        public void setMonth_sales(String month_sales) {
            this.month_sales = month_sales;
        }

        public int getHave_coupon() {
            return have_coupon;
        }

        public void setHave_coupon(int have_coupon) {
            this.have_coupon = have_coupon;
        }

        public String  getRebate_money() {
            return rebate_money;
        }

        public void setRebate_money(String rebate_money) {
            this.rebate_money = rebate_money;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }
    }
}
