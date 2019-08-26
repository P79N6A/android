package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public class OrderLevelBean {

    /**
     * code : 200
     * msg : 成功
     * data : [{"log_id":"69081","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547145","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69084","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547147","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69087","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547149","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69090","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547150","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69093","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547152","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69096","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547153","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69099","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547155","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69102","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547156","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69105","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547158","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"},{"log_id":"69108","user_id":"98","money":"0.12","intro":"您的下级会员11的下级会员购买商品结算返利金额：+0.12元","create_time":"1505547160","create_ip":"","types":"0","shopp":"G5CONXEGN旗舰店","shu":"1","jin":"49.00","money_now":"0.00","state":"1","orderid":null,"balance":"0.00","level":"1"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * log_id : 69081
         * user_id : 98
         * money : 0.12
         * intro : 您的下级会员11的下级会员购买商品结算返利金额：+0.12元
         * create_time : 1505547145
         * create_ip :
         * types : 0
         * shopp : G5CONXEGN旗舰店
         * shu : 1
         * jin : 49.00
         * money_now : 0.00
         * state : 1
         * orderid : null
         * balance : 0.00
         * level : 1
         */

        private String log_id;
        private String user_id;
        private String money;
        private String intro;
        private String create_time;
        private String create_ip;
        private String types;
        private String shopp;
        private String shu;
        private String jin;
        private String money_now;
        private String state;
        private Object orderid;
        private String balance;
        private String level;

        public String getLog_id() {
            return log_id;
        }

        public void setLog_id(String log_id) {
            this.log_id = log_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCreate_ip() {
            return create_ip;
        }

        public void setCreate_ip(String create_ip) {
            this.create_ip = create_ip;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getShopp() {
            return shopp;
        }

        public void setShopp(String shopp) {
            this.shopp = shopp;
        }

        public String getShu() {
            return shu;
        }

        public void setShu(String shu) {
            this.shu = shu;
        }

        public String getJin() {
            return jin;
        }

        public void setJin(String jin) {
            this.jin = jin;
        }

        public String getMoney_now() {
            return money_now;
        }

        public void setMoney_now(String money_now) {
            this.money_now = money_now;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Object getOrderid() {
            return orderid;
        }

        public void setOrderid(Object orderid) {
            this.orderid = orderid;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
