package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/11/2.
 */

public class SignBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : {"is_sign":0,"gold_info":"","sign_gold":"随机金币","comment_gold":"1","zan_integration":"1"}
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
         * is_sign : 0
         * gold_info :
         * sign_gold : 随机金币
         * comment_gold : 1
         * zan_integration : 1
         */

        private int is_sign;
        private String gold_info;
        private String sign_gold;
        private String comment_gold;
        private String zan_integration;
        private String sign_gold_des;
        private String comment_gold_des;

        public String getComment_integral_des() {
            return comment_integral_des;
        }

        public void setComment_integral_des(String comment_integral_des) {
            this.comment_integral_des = comment_integral_des;
        }

        private String comment_integral_des;

        public String getComment_integral() {
            return comment_integral;
        }

        public void setComment_integral(String comment_integral) {
            this.comment_integral = comment_integral;
        }

        private String comment_integral;

        public String getShareappintegral_des() {
            return shareappintegral_des;
        }

        public void setShareappintegral_des(String shareappintegral_des) {
            this.shareappintegral_des = shareappintegral_des;
        }

        private String shareappintegral_des;

        public String getSign_gold_des() {
            return sign_gold_des;
        }

        public void setSign_gold_des(String sign_gold_des) {
            this.sign_gold_des = sign_gold_des;
        }

        public String getComment_gold_des() {
            return comment_gold_des;
        }

        public void setComment_gold_des(String comment_gold_des) {
            this.comment_gold_des = comment_gold_des;
        }

        public String getZan_integration_des() {
            return zan_integration_des;
        }

        public void setZan_integration_des(String zan_integration_des) {
            this.zan_integration_des = zan_integration_des;
        }

        public String getCream_gold_des() {
            return cream_gold_des;
        }

        public void setCream_gold_des(String cream_gold_des) {
            this.cream_gold_des = cream_gold_des;
        }

        private String zan_integration_des;
        private String cream_gold_des;

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public String getGold_info() {
            return gold_info;
        }

        public void setGold_info(String gold_info) {
            this.gold_info = gold_info;
        }

        public String getSign_gold() {
            return sign_gold;
        }

        public void setSign_gold(String sign_gold) {
            this.sign_gold = sign_gold;
        }

        public String getComment_gold() {
            return comment_gold;
        }

        public void setComment_gold(String comment_gold) {
            this.comment_gold = comment_gold;
        }

        public String getZan_integration() {
            return zan_integration;
        }

        public void setZan_integration(String zan_integration) {
            this.zan_integration = zan_integration;
        }
    }
}
