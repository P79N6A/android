package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/11/2.
 */

public class ShearTaoBean {


    /**
     * code : 200
     * msg : 商品转链成功
     * result : {"title":"正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤","shopTitle":"翎茜美护肤品牌店","reservePrice":39.9,"is_coupon":0,"zkPrice":29.9,"taoToken":"￥ElJf0gqWeQ6￥"}
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
         * title : 正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤
         * shopTitle : 翎茜美护肤品牌店
         * reservePrice : 39.9
         * is_coupon : 0
         * zkPrice : 29.9
         * taoToken : ￥ElJf0gqWeQ6￥
         */


        /**
         * title : 韩国帽子秋冬貉子毛毛球帽欧美潮针织毛线帽女羊毛保暖加厚皮草帽
         * shopTitle : 大锦帽子高端配饰
         * pictUrl : http://img.alicdn.com/bao/uploaded/i3/2889581040/TB2EVxNaX55V1Bjy0FoXXbVjFXa_!!2889581040.jpg
         * reservePrice : 120
         * is_coupon : 1
         * zkPrice : 58
         * taoToken : ￥jz1G0TWGv7Y￥
         * fanli_money : 8.7
         */

        private String title;
        private String shopTitle;
        private String reservePrice;
        private int is_coupon;
        private String zkPrice;

        public String getFanli_money() {
            return fanli_money;
        }

        public void setFanli_money(String fanli_money) {
            this.fanli_money = fanli_money;
        }

        private String fanli_money;

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        private String coupon_money;

        public String getPictUrl() {
            return pictUrl;
        }

        public void setPictUrl(String pictUrl) {
            this.pictUrl = pictUrl;
        }

        private String pictUrl;
        private String taoToken;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
        private String link;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShopTitle() {
            return shopTitle;
        }

        public void setShopTitle(String shopTitle) {
            this.shopTitle = shopTitle;
        }

        public String getReservePrice() {
            return reservePrice;
        }

        public void setReservePrice(String reservePrice) {
            this.reservePrice = reservePrice;
        }

        public int getIs_coupon() {
            return is_coupon;
        }

        public void setIs_coupon(int is_coupon) {
            this.is_coupon = is_coupon;
        }

        public String getZkPrice() {
            return zkPrice;
        }

        public void setZkPrice(String zkPrice) {
            this.zkPrice = zkPrice;
        }

        public String getTaoToken() {
            return taoToken;
        }

        public void setTaoToken(String taoToken) {
            this.taoToken = taoToken;
        }
    }
}
