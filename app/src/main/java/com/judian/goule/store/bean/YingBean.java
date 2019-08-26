package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */

public class YingBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"money":700,"nickname":"18545590350","avatar":""},{"money":203,"nickname":"的方法","avatar":"http://47.104.108.30/Uploads/userhead/2017-12-04/5a24ed2c38209.png"},{"money":200,"nickname":"你好","avatar":"https://login.taobao.com/member/login.jhtml?from=taobaoindex&f=top&style=&sub=true&redirect_url=https%3A%2F%2Fi.taobao.com%2Fmy_taobao.htm%3Fspm%3Da21bo.2017.201864-1.1.db5e413yqlhO7%26ad_id%3D%26am_id%3D%26cm_id%3D%26pm_id%3D1501036000a02c5c3739"},{"money":200,"nickname":"静","avatar":"http://47.104.108.30/Uploads/userhead/2017-12-04/5a2507967cc50.png"},{"money":200,"nickname":"lala","avatar":""},{"money":200,"nickname":"发工资","avatar":"dddd"}]
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
         * money : 700
         * nickname : 18545590350
         * avatar :
         */

        private String money;
        private String nickname;
        private String avatar;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
