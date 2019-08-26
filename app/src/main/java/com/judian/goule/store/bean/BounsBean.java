package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public class BounsBean {


    /**
     * code : 200
     * msg : 获取成功！
     * result : [{"envelope_id":"2","user_id":"71","less_money":"57.00","instructions":"一次性体现满200送57","start_time":"2017-12-04 00:00:00","end_time":"2017-12-02 00:00:00","full_money":"200.00","add_time":"2017-12-04 13:02:14","update_time":null,"status":"1","type_status":"1","type_st":"2","user_status":"1"}]
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
         * envelope_id : 2
         * user_id : 71
         * less_money : 57.00
         * instructions : 一次性体现满200送57
         * start_time : 2017-12-04 00:00:00
         * end_time : 2017-12-02 00:00:00
         * full_money : 200.00
         * add_time : 2017-12-04 13:02:14
         * update_time : null
         * status : 1
         * type_status : 1
         * type_st : 2
         * user_status : 1
         */

        private String envelope_id;
        private String user_id;
        private String less_money;
        private String instructions;
        private String start_time;
        private String end_time;
        private String full_money;
        private String add_time;
        private Object update_time;
        private String status;
        private String type_status;
        private String type_st;
        private String user_status;
        private String end_time_one;

        public String getEnd_time_one() {
            return end_time_one;
        }

        public void setEnd_time_one(String end_time_one) {
            this.end_time_one = end_time_one;
        }

        public String getStart_time_one() {
            return start_time_one;
        }

        public void setStart_time_one(String start_time_one) {
            this.start_time_one = start_time_one;
        }

        private String start_time_one;

        public String getEnvelope_id() {
            return envelope_id;
        }

        public void setEnvelope_id(String envelope_id) {
            this.envelope_id = envelope_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLess_money() {
            return less_money;
        }

        public void setLess_money(String less_money) {
            this.less_money = less_money;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getFull_money() {
            return full_money;
        }

        public void setFull_money(String full_money) {
            this.full_money = full_money;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType_status() {
            return type_status;
        }

        public void setType_status(String type_status) {
            this.type_status = type_status;
        }

        public String getType_st() {
            return type_st;
        }

        public void setType_st(String type_st) {
            this.type_st = type_st;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }
    }
}
