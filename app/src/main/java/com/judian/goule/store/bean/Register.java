package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/10/28.
 */

public class Register {

    /**
     * code : 200
     * msg : ok
     * result : {"token":"ef9446f7e5efc819c240dc4444bdeb5c4285d6826ba52245a8a29aca995e99d1","id":"12","phone":"15066663312","pid":144776605}
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
         * token : ef9446f7e5efc819c240dc4444bdeb5c4285d6826ba52245a8a29aca995e99d1
         * id : 12
         * phone : 15066663312
         * pid : 144776605
         */

        private String token;
        private String id;
        private String phone;
        private int pid;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }
    }
}
