package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public class WithdrawalsBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : [{"id":"154","user_id":"258","order_id":"","money":"12.00","intro":"用户提现12元","balance":"178.00","type":"3","state":"0","status":"1","add_time":"2017-12-06 16:01:53","update_time":"0000-00-00 00:00:00","ali_account":"18263802035","name":"徐静"},{"id":"153","user_id":"258","order_id":"","money":"10.00","intro":"用户提现10元","balance":"190.00","type":"4","state":"0","status":"1","add_time":"2017-12-05 13:27:37","update_time":"0000-00-00 00:00:00","ali_account":"18263802035","name":"徐静"}]
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
         * id : 154
         * user_id : 258
         * order_id :
         * money : 12.00
         * intro : 用户提现12元
         * balance : 178.00
         * type : 3
         * state : 0
         * status : 1
         * add_time : 2017-12-06 16:01:53
         * update_time : 0000-00-00 00:00:00
         * ali_account : 18263802035
         * v_state : 0 /   0待审核  1 已结算
         * name : 徐静
         */

        private String id;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        private String info;
        private String user_id;
        private String order_id;
        private String money;
        private String intro;
        private String balance;
        private String type;
        private String state;
        private String status;
        private String add_time;
        private String update_time;
        private String ali_account;
        private String score;
        private String name;
        private String v_state;

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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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

        public String getAli_account() {
            return ali_account;
        }

        public void setAli_account(String ali_account) {
            this.ali_account = ali_account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getV_state() {
            return v_state;
        }

        public void setV_state(String v_state) {
            this.v_state = v_state;
        }
    }
}
