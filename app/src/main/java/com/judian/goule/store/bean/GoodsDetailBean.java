package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/12/29.
 */

public class GoodsDetailBean {


    /**
     * errcode : 200
     * errmsg : 获取成功
     * result : {"title":"测试1","pict_url":"[\"\\/Uploads\\/admin\\/1513218767_996186499.jpg\",\"\\/Uploads\\/admin\\/1513218767_1718176600.jpg\",\"\\/Uploads\\/admin\\/1513218767_1292934965.jpg\",\"\\/Uploads\\/admin\\/1513218767_368543725.jpg\"]","price":"900.00","reserve_price":"1000.00","content":"<p><img src=\"/ueditor/php/upload/image/20171214/1513218765989343.jpg\" title=\"1513218765989343.jpg\" alt=\"zi2.jpg\"/><\/p>","month_sales":"3000","income_commission":"4.30","id":"6","fanli_money":38.7,"usable":"0.00"}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getErrcode() {
        return code;
    }

    public void setErrcode(int errcode) {
        this.code = errcode;
    }

    public String getErrmsg() {
        return msg;
    }

    public void setErrmsg(String errmsg) {
        this.msg = errmsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * title : 测试1
         * pict_url : ["\/Uploads\/admin\/1513218767_996186499.jpg","\/Uploads\/admin\/1513218767_1718176600.jpg","\/Uploads\/admin\/1513218767_1292934965.jpg","\/Uploads\/admin\/1513218767_368543725.jpg"]
         * price : 900.00
         * reserve_price : 1000.00
         * content : <p><img src="/ueditor/php/upload/image/20171214/1513218765989343.jpg" title="1513218765989343.jpg" alt="zi2.jpg"/></p>
         * month_sales : 3000
         * income_commission : 4.30
         * id : 6
         * fanli_money : 38.7
         * usable : 0.00
         */

        private String title;
        private String pict_url;
        private String price;
        private String reserve_price;
        private String content;
        private String month_sales;
        private String income_commission;
        private String id;
        private double fanli_money;
        private String usable;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMonth_sales() {
            return month_sales;
        }

        public void setMonth_sales(String month_sales) {
            this.month_sales = month_sales;
        }

        public String getIncome_commission() {
            return income_commission;
        }

        public void setIncome_commission(String income_commission) {
            this.income_commission = income_commission;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getFanli_money() {
            return fanli_money;
        }

        public void setFanli_money(double fanli_money) {
            this.fanli_money = fanli_money;
        }

        public String getUsable() {
            return usable;
        }

        public void setUsable(String usable) {
            this.usable = usable;
        }
    }
}
