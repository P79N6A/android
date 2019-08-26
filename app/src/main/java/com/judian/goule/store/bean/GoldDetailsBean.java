package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public class GoldDetailsBean {


    /**
     * code : 200
     * msg : 查询成功
     * result : {"qq":"458845481","gold_detail":[{"id":"36","user_id":"1","score":"1","type":"2","status":"1","add_time":"2017-11-07 14:55:00","update_time":"2017-11-07 14:55:00"},{"id":"35","user_id":"1","score":"1","type":"2","status":"1","add_time":"2017-11-07 14:54:41","update_time":"2017-11-07 14:54:41"}]}
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
         * qq : 458845481
         * gold_detail : [{"id":"36","user_id":"1","score":"1","type":"2","status":"1","add_time":"2017-11-07 14:55:00","update_time":"2017-11-07 14:55:00"},{"id":"35","user_id":"1","score":"1","type":"2","status":"1","add_time":"2017-11-07 14:54:41","update_time":"2017-11-07 14:54:41"}]
         */

        private String qq;
        private List<GoldDetailBean> gold_detail;

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public List<GoldDetailBean> getGold_detail() {
            return gold_detail;
        }

        public void setGold_detail(List<GoldDetailBean> gold_detail) {
            this.gold_detail = gold_detail;
        }

        public static class GoldDetailBean {
            /**
             * id : 36
             * user_id : 1
             * score : 1
             * type : 2
             * status : 1
             * add_time : 2017-11-07 14:55:00
             * update_time : 2017-11-07 14:55:00
             */

            private String id;
            private String user_id;
            private String score;
            private String type;
            private String status;
            private String add_time;
            private String update_time;

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

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
        }
    }
}
