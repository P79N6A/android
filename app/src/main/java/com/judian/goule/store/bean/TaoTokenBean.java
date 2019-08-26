package com.judian.goule.store.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/2.
 */

public class TaoTokenBean implements  Serializable{


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

    public static class ResultBean implements Serializable{


        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        private String share_title;
        private String pic;
        private String price;

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        private String num_iid;

        public String getCoupon_link() {
            return coupon_link;
        }

        public void setCoupon_link(String coupon_link) {
            this.coupon_link = coupon_link;
        }

        private String coupon_link;

        public String getFanli_money() {
            return fanli_money;
        }

        public void setFanli_money(String fanli_money) {
            this.fanli_money = fanli_money;
        }

        private String fanli_money;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        private String coupon_price;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        private String link;



    }
}
