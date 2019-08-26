package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/10/28.
 */

public class ModifyName {

    /**
     * code : 200
     * msg : 修改昵称成功
     * result : {"nick_name":"lala"}
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
         * nick_name : lala
         */

        private String nick_name;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }
    }
}
