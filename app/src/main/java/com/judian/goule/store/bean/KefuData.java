package com.judian.goule.store.bean;

import java.io.Serializable;

public class KefuData implements Serializable {
    /**
     * code : 200
     * msg : 获取成功
     * result : {"phone":"13250772191","qq":"921308494","weixin":"1234","weixin_url":"/uploads/20180707/ac9dc97ccef674b12016960cdb8d7514.gif"}
     */

    private String code;
    private String msg;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * phone : 13250772191
         * qq : 921308494
         * weixin : 1234
         * weixin_url : /uploads/20180707/ac9dc97ccef674b12016960cdb8d7514.gif
         */

        private String phone;
        private String qq;
        private String weixin;
        private String weixin_url;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getWeixin_url() {
            return weixin_url;
        }

        public void setWeixin_url(String weixin_url) {
            this.weixin_url = weixin_url;
        }
    }
}
