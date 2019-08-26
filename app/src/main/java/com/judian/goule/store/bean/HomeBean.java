package com.judian.goule.store.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/6.
 */

public class HomeBean implements Serializable{

    /**
     * code : 200
     * msg : 获取成功
     * result : {"now_month_commission":"0","pre_month_commission":"0","today_commission":"0","yestoday_commission":"0","now_month_payment_num":"0","pre_month_payment_num":"0","today_payment_num":"0","yestoday_payment_num":"0","referer":"18263802035","yqr_code":"1058146","total_income":"0.17","wait_withdra":"0.17"}
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
        /**
         * now_month_commission : 0
         * pre_month_commission : 0
         * today_commission : 0
         * yestoday_commission : 0
         * now_month_payment_num : 0
         * pre_month_payment_num : 0
         * today_payment_num : 0
         * yestoday_payment_num : 0
         * referer : 18263802035
         * yqr_code : 1058146
         * total_income : 0.17
         * wait_withdra : 0.17
         */

        private String now_month_commission;
        private String pre_month_commission;
        private String today_commission;
        private String yestoday_commission;
        private String now_month_payment_num;
        private String pre_month_payment_num;
        private String today_payment_num;
        private String yestoday_payment_num;
        private String referer;
        private String yqr_code;
        private String total_income;
        private String wait_withdra;

        public String getNow_month_commission() {
            return now_month_commission;
        }

        public void setNow_month_commission(String now_month_commission) {
            this.now_month_commission = now_month_commission;
        }

        public String getPre_month_commission() {
            return pre_month_commission;
        }

        public void setPre_month_commission(String pre_month_commission) {
            this.pre_month_commission = pre_month_commission;
        }

        public String getToday_commission() {
            return today_commission;
        }

        public void setToday_commission(String today_commission) {
            this.today_commission = today_commission;
        }

        public String getYestoday_commission() {
            return yestoday_commission;
        }

        public void setYestoday_commission(String yestoday_commission) {
            this.yestoday_commission = yestoday_commission;
        }

        public String getNow_month_payment_num() {
            return now_month_payment_num;
        }

        public void setNow_month_payment_num(String now_month_payment_num) {
            this.now_month_payment_num = now_month_payment_num;
        }

        public String getPre_month_payment_num() {
            return pre_month_payment_num;
        }

        public void setPre_month_payment_num(String pre_month_payment_num) {
            this.pre_month_payment_num = pre_month_payment_num;
        }

        public String getToday_payment_num() {
            return today_payment_num;
        }

        public void setToday_payment_num(String today_payment_num) {
            this.today_payment_num = today_payment_num;
        }

        public String getYestoday_payment_num() {
            return yestoday_payment_num;
        }

        public void setYestoday_payment_num(String yestoday_payment_num) {
            this.yestoday_payment_num = yestoday_payment_num;
        }

        public String getReferer() {
            return referer;
        }

        public void setReferer(String referer) {
            this.referer = referer;
        }

        public String getYqr_code() {
            return yqr_code;
        }

        public void setYqr_code(String yqr_code) {
            this.yqr_code = yqr_code;
        }

        public String getTotal_income() {
            return total_income;
        }

        public void setTotal_income(String total_income) {
            this.total_income = total_income;
        }

        public String getWait_withdra() {
            return wait_withdra;
        }

        public void setWait_withdra(String wait_withdra) {
            this.wait_withdra = wait_withdra;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "now_month_commission='" + now_month_commission + '\'' +
                    ", pre_month_commission='" + pre_month_commission + '\'' +
                    ", today_commission='" + today_commission + '\'' +
                    ", yestoday_commission='" + yestoday_commission + '\'' +
                    ", now_month_payment_num='" + now_month_payment_num + '\'' +
                    ", pre_month_payment_num='" + pre_month_payment_num + '\'' +
                    ", today_payment_num='" + today_payment_num + '\'' +
                    ", yestoday_payment_num='" + yestoday_payment_num + '\'' +
                    ", referer='" + referer + '\'' +
                    ", yqr_code='" + yqr_code + '\'' +
                    ", total_income='" + total_income + '\'' +
                    ", wait_withdra='" + wait_withdra + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
