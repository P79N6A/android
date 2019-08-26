package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class MySBean {


    /**
     * code : 200
     * msg : 获取订单成功
     * result : {"total":"2","order_info":[{"id":"15","user_id":"0","add_time":"2017-09-20 09:16:14","add_time_day":"2017-09-20","click_time":"2017-09-20 09:15:49","good_title":"正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤","good_id":"542798233906","mess":"爱么你么爱你么么哒","shop":"翎茜美护肤品牌店","good_num":"1","good_price":"85.00","order_state":"订单结算","order_type":"淘宝","income":"38.00","divide":"95.00","amount_pay":"0.00","evaluation":"12.00","settlement":"0.00","estimate":"0.00","settlement_time":"2017-10-20 09:15:49","commission_percent":"38.00","commission":"0.00","pension_percent":"0.00","pension":"0.00","pension_type":"-","roof":"PC","source":"淘宝客活动","order_sn":"60142759240095303","cate":"美妆-美容护肤","media_id":"27494356","media":"牛八软件","adv_id":"118016449","adv":"牛付通","user_name":null,"returnstatus":"1","return_time":null,"admin_id":null,"admin_name":null,"returns":"0.000","goods_id_four":"5303","pict_url":"http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg","price":"29.90","is_comment":"0"},{"id":"26","user_id":"0","add_time":"2017-08-11 10:55:45","add_time_day":"2017-08-11","click_time":"2017-08-11 10:55:11","good_title":"QQ币1个直充","good_id":"25497244217","mess":"晴思雨2007","shop":"Q币 Q币充值 QB","good_num":"1","good_price":"0.98","order_state":"订单结算","order_type":"淘宝","income":"1.00","divide":"100.00","amount_pay":"0.98","evaluation":"0.01","settlement":"0.98","estimate":"0.01","settlement_time":"2017-08-11 10:56:21","commission_percent":"1.00","commission":"0.01","pension_percent":"0.00","pension":"0.00","pension_type":"-","roof":"PC","source":"--","order_sn":"44453167321484307","cate":"腾讯QQ专区","media_id":"27494356","media":"牛八软件","adv_id":"118016449","adv":"牛付通","user_name":null,"returnstatus":"0","return_time":null,"admin_id":null,"admin_name":null,"returns":"0.006","goods_id_four":"4307","pict_url":"http://img.alicdn.com/bao/uploaded/i1/T1aiVpXoBHXXb1upjX.jpg","price":"0.98","is_comment":"0"}]}
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
         * total : 2
         * order_info : [{"id":"15","user_id":"0","add_time":"2017-09-20 09:16:14","add_time_day":"2017-09-20","click_time":"2017-09-20 09:15:49","good_title":"正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤","good_id":"542798233906","mess":"爱么你么爱你么么哒","shop":"翎茜美护肤品牌店","good_num":"1","good_price":"85.00","order_state":"订单结算","order_type":"淘宝","income":"38.00","divide":"95.00","amount_pay":"0.00","evaluation":"12.00","settlement":"0.00","estimate":"0.00","settlement_time":"2017-10-20 09:15:49","commission_percent":"38.00","commission":"0.00","pension_percent":"0.00","pension":"0.00","pension_type":"-","roof":"PC","source":"淘宝客活动","order_sn":"60142759240095303","cate":"美妆-美容护肤","media_id":"27494356","media":"牛八软件","adv_id":"118016449","adv":"牛付通","user_name":null,"returnstatus":"1","return_time":null,"admin_id":null,"admin_name":null,"returns":"0.000","goods_id_four":"5303","pict_url":"http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg","price":"29.90","is_comment":"0"},{"id":"26","user_id":"0","add_time":"2017-08-11 10:55:45","add_time_day":"2017-08-11","click_time":"2017-08-11 10:55:11","good_title":"QQ币1个直充","good_id":"25497244217","mess":"晴思雨2007","shop":"Q币 Q币充值 QB","good_num":"1","good_price":"0.98","order_state":"订单结算","order_type":"淘宝","income":"1.00","divide":"100.00","amount_pay":"0.98","evaluation":"0.01","settlement":"0.98","estimate":"0.01","settlement_time":"2017-08-11 10:56:21","commission_percent":"1.00","commission":"0.01","pension_percent":"0.00","pension":"0.00","pension_type":"-","roof":"PC","source":"--","order_sn":"44453167321484307","cate":"腾讯QQ专区","media_id":"27494356","media":"牛八软件","adv_id":"118016449","adv":"牛付通","user_name":null,"returnstatus":"0","return_time":null,"admin_id":null,"admin_name":null,"returns":"0.006","goods_id_four":"4307","pict_url":"http://img.alicdn.com/bao/uploaded/i1/T1aiVpXoBHXXb1upjX.jpg","price":"0.98","is_comment":"0"}]
         */

        private String total;
        private List<OrderInfoBean> order_info;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<OrderInfoBean> getOrder_info() {
            return order_info;
        }

        public void setOrder_info(List<OrderInfoBean> order_info) {
            this.order_info = order_info;
        }

        public static class OrderInfoBean {
            /**
             * id : 15
             * user_id : 0
             * add_time : 2017-09-20 09:16:14
             * add_time_day : 2017-09-20
             * click_time : 2017-09-20 09:15:49
             * good_title : 正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤
             * good_id : 542798233906
             * mess : 爱么你么爱你么么哒
             * shop : 翎茜美护肤品牌店
             * good_num : 1
             * good_price : 85.00
             * order_state : 订单结算
             * order_type : 淘宝
             * income : 38.00
             * divide : 95.00
             * amount_pay : 0.00
             * evaluation : 12.00
             * settlement : 0.00
             * estimate : 0.00
             * settlement_time : 2017-10-20 09:15:49
             * commission_percent : 38.00
             * commission : 0.00
             * pension_percent : 0.00
             * pension : 0.00
             * pension_type : -
             * roof : PC
             * source : 淘宝客活动
             * order_sn : 60142759240095303
             * cate : 美妆-美容护肤
             * media_id : 27494356
             * media : 牛八软件
             * adv_id : 118016449
             * adv : 牛付通
             * user_name : null
             * returnstatus : 1
             * return_time : null
             * admin_id : null
             * admin_name : null
             * returns : 0.000
             * goods_id_four : 5303
             * pict_url : http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg
             * price : 29.90
             * is_comment : 0
             */

            private String id;
            private String user_id;
            private String add_time;
            private String add_time_day;
            private String click_time;
            private String good_title;
            private String good_id;
            private String mess;
            private String shop;
            private String good_num;
            private String good_price;
            private String order_state;
            private String order_type;
            private String income;
            private String divide;
            private String amount_pay;
            private String evaluation;
            private String settlement;
            private String estimate;
            private String settlement_time;
            private String commission_percent;
            private String commission;
            private String pension_percent;
            private String pension;
            private String pension_type;
            private String roof;
            private String source;
            private String order_sn;
            private String cate;
            private String media_id;
            private String media;
            private String adv_id;
            private String adv;
            private Object user_name;
            private String returnstatus;
            private Object return_time;
            private Object admin_id;
            private Object admin_name;
            private String returns;
            private String goods_id_four;
            private String pict_url;
            private String price;
            private String is_comment;

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

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getAdd_time_day() {
                return add_time_day;
            }

            public void setAdd_time_day(String add_time_day) {
                this.add_time_day = add_time_day;
            }

            public String getClick_time() {
                return click_time;
            }

            public void setClick_time(String click_time) {
                this.click_time = click_time;
            }

            public String getGood_title() {
                return good_title;
            }

            public void setGood_title(String good_title) {
                this.good_title = good_title;
            }

            public String getGood_id() {
                return good_id;
            }

            public void setGood_id(String good_id) {
                this.good_id = good_id;
            }

            public String getMess() {
                return mess;
            }

            public void setMess(String mess) {
                this.mess = mess;
            }

            public String getShop() {
                return shop;
            }

            public void setShop(String shop) {
                this.shop = shop;
            }

            public String getGood_num() {
                return good_num;
            }

            public void setGood_num(String good_num) {
                this.good_num = good_num;
            }

            public String getGood_price() {
                return good_price;
            }

            public void setGood_price(String good_price) {
                this.good_price = good_price;
            }

            public String getOrder_state() {
                return order_state;
            }

            public void setOrder_state(String order_state) {
                this.order_state = order_state;
            }

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public String getDivide() {
                return divide;
            }

            public void setDivide(String divide) {
                this.divide = divide;
            }

            public String getAmount_pay() {
                return amount_pay;
            }

            public void setAmount_pay(String amount_pay) {
                this.amount_pay = amount_pay;
            }

            public String getEvaluation() {
                return evaluation;
            }

            public void setEvaluation(String evaluation) {
                this.evaluation = evaluation;
            }

            public String getSettlement() {
                return settlement;
            }

            public void setSettlement(String settlement) {
                this.settlement = settlement;
            }

            public String getEstimate() {
                return estimate;
            }

            public void setEstimate(String estimate) {
                this.estimate = estimate;
            }

            public String getSettlement_time() {
                return settlement_time;
            }

            public void setSettlement_time(String settlement_time) {
                this.settlement_time = settlement_time;
            }

            public String getCommission_percent() {
                return commission_percent;
            }

            public void setCommission_percent(String commission_percent) {
                this.commission_percent = commission_percent;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getPension_percent() {
                return pension_percent;
            }

            public void setPension_percent(String pension_percent) {
                this.pension_percent = pension_percent;
            }

            public String getPension() {
                return pension;
            }

            public void setPension(String pension) {
                this.pension = pension;
            }

            public String getPension_type() {
                return pension_type;
            }

            public void setPension_type(String pension_type) {
                this.pension_type = pension_type;
            }

            public String getRoof() {
                return roof;
            }

            public void setRoof(String roof) {
                this.roof = roof;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getCate() {
                return cate;
            }

            public void setCate(String cate) {
                this.cate = cate;
            }

            public String getMedia_id() {
                return media_id;
            }

            public void setMedia_id(String media_id) {
                this.media_id = media_id;
            }

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }

            public String getAdv_id() {
                return adv_id;
            }

            public void setAdv_id(String adv_id) {
                this.adv_id = adv_id;
            }

            public String getAdv() {
                return adv;
            }

            public void setAdv(String adv) {
                this.adv = adv;
            }

            public Object getUser_name() {
                return user_name;
            }

            public void setUser_name(Object user_name) {
                this.user_name = user_name;
            }

            public String getReturnstatus() {
                return returnstatus;
            }

            public void setReturnstatus(String returnstatus) {
                this.returnstatus = returnstatus;
            }

            public Object getReturn_time() {
                return return_time;
            }

            public void setReturn_time(Object return_time) {
                this.return_time = return_time;
            }

            public Object getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(Object admin_id) {
                this.admin_id = admin_id;
            }

            public Object getAdmin_name() {
                return admin_name;
            }

            public void setAdmin_name(Object admin_name) {
                this.admin_name = admin_name;
            }

            public String getReturns() {
                return returns;
            }

            public void setReturns(String returns) {
                this.returns = returns;
            }

            public String getGoods_id_four() {
                return goods_id_four;
            }

            public void setGoods_id_four(String goods_id_four) {
                this.goods_id_four = goods_id_four;
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

            public String getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
            }
        }
    }
}
