package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public class HelpBean  {


    /**
     * code : 200
     * msg : 获取成功
     * result : [{"column_id":"7","column_name":"1、怎么买东西？","column_parent":"0","img_url":"/Uploads/column/2017-11-16/5a0d0893be2e2.png","column_level":"0","column_state":"0","column_sort":"7","describe":"查看订单问题"},{"column_id":"8","column_name":"2、买的东西在哪看返利","column_parent":"0","img_url":"/Uploads/column/2017-11-16/5a0d0889b9bc3.png","column_level":"0","column_state":"0","column_sort":"6","describe":"收益问题全知道"},{"column_id":"9","column_name":"3、怎么邀请好友以及有什么好处","column_parent":"0","img_url":"/Uploads/column/2017-11-16/5a0d087970774.png","column_level":"0","column_state":"0","column_sort":"5","describe":"解决账号安全"},{"column_id":"10","column_name":"4、怎么分享赚钱","column_parent":"0","img_url":"/Uploads/column/2017-11-16/5a0d08326acdd.png","column_level":"0","column_state":"0","column_sort":"4","describe":"更多福利全了解"},{"column_id":"11","column_name":"5、有收益了怎么体现","column_parent":"0","img_url":"/Uploads/column/2017-11-16/5a0d0829728c0.png","column_level":"0","column_state":"0","column_sort":"3","describe":"马上发起售后"},{"column_id":"16","column_name":"6、常见问题汇总","column_parent":"0","img_url":"/Uploads/column/2017-11-16/5a0d081550086.png","column_level":"0","column_state":"0","column_sort":"2","describe":"分享奖励更多"}]
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
         * column_id : 7
         * column_name : 1、怎么买东西？
         * column_parent : 0
         * img_url : /Uploads/column/2017-11-16/5a0d0893be2e2.png
         * column_level : 0
         * column_state : 0
         * column_sort : 7
         * describe : 查看订单问题
         */

        private String column_id;
        private String column_name;
        private String column_parent;
        private String img_url;
        private String column_level;
        private String column_state;
        private String column_sort;
        private String describe;

        public String getColumn_id() {
            return column_id;
        }

        public void setColumn_id(String column_id) {
            this.column_id = column_id;
        }

        public String getColumn_name() {
            return column_name;
        }

        public void setColumn_name(String column_name) {
            this.column_name = column_name;
        }

        public String getColumn_parent() {
            return column_parent;
        }

        public void setColumn_parent(String column_parent) {
            this.column_parent = column_parent;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getColumn_level() {
            return column_level;
        }

        public void setColumn_level(String column_level) {
            this.column_level = column_level;
        }

        public String getColumn_state() {
            return column_state;
        }

        public void setColumn_state(String column_state) {
            this.column_state = column_state;
        }

        public String getColumn_sort() {
            return column_sort;
        }

        public void setColumn_sort(String column_sort) {
            this.column_sort = column_sort;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "column_id='" + column_id + '\'' +
                    ", column_name='" + column_name + '\'' +
                    ", column_parent='" + column_parent + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", column_level='" + column_level + '\'' +
                    ", column_state='" + column_state + '\'' +
                    ", column_sort='" + column_sort + '\'' +
                    ", describe='" + describe + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HelpBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
