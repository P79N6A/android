package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class HotBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : [{"id":"1","name":"袜子","num":"1"},{"id":"2","name":"毛毛鞋","num":"1"},{"id":"3","name":"棉服","num":"1"},{"id":"4","name":"毛呢大衣","num":"1"},{"id":"5","name":"保温杯","num":"1"},{"id":"6","name":"围巾","num":"1"},{"id":"7","name":"毛衣","num":"1"},{"id":"8","name":"耳机","num":"1"},{"id":"9","name":"羽绒服","num":"1"}]
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
         * id : 1
         * name : 袜子
         * num : 1
         */

        private String id;
        private String name;
        private String num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
