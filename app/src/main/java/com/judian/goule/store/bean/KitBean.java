package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public class KitBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"log_id":"68612","user_id":"83","money":"100.00","intro":"会员提现:100元","create_time":"1505265603","create_ip":null,"types":"0","shopp":null,"shu":null,"jin":null,"money_now":null,"state":"5","orderid":null,"balance":"802.81"},{"log_id":"68613","user_id":"83","money":"100.00","intro":"会员提现:100元","create_time":"1505266727","create_ip":null,"types":"0","shopp":null,"shu":null,"jin":null,"money_now":null,"state":"5","orderid":null,"balance":"702.81"}]
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
         * log_id : 68612
         * user_id : 83
         * money : 100.00
         * intro : 会员提现:100元
         * create_time : 1505265603
         * create_ip : null
         * types : 0
         * shopp : null
         * shu : null
         * jin : null
         * money_now : null
         * state : 5
         * orderid : null
         * balance : 802.81
         */

        private String log_id;
        private String user_id;
        private String money;
        private String intro;
        private String create_time;
        private Object create_ip;
        private String types;
        private Object shopp;
        private Object shu;
        private Object jin;
        private Object money_now;
        private String state;
        private Object orderid;
        private String balance;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

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

        public Object getCreate_ip() {
            return create_ip;
        }

        public void setCreate_ip(Object create_ip) {
            this.create_ip = create_ip;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public Object getShopp() {
            return shopp;
        }

        public void setShopp(Object shopp) {
            this.shopp = shopp;
        }

        public Object getShu() {
            return shu;
        }

        public void setShu(Object shu) {
            this.shu = shu;
        }

        public Object getJin() {
            return jin;
        }

        public void setJin(Object jin) {
            this.jin = jin;
        }

        public Object getMoney_now() {
            return money_now;
        }

        public void setMoney_now(Object money_now) {
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
    }
}
