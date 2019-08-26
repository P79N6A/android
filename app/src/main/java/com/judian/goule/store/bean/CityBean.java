package com.judian.goule.store.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class CityBean implements Serializable {


    /**
     * code : 200
     * msg : 获取成功
     * result : [{"id":"1","name":"北京","pid":"0"},{"id":"21","name":"上海","pid":"0"},{"id":"42","name":"天津","pid":"0"},{"id":"62","name":"重庆","pid":"0"},{"id":"104","name":"安徽","pid":"0"},{"id":"227","name":"福建","pid":"0"},{"id":"322","name":"甘肃","pid":"0"},{"id":"423","name":"广东","pid":"0"},{"id":"566","name":"广西","pid":"0"},{"id":"690","name":"贵州","pid":"0"},{"id":"788","name":"海南","pid":"0"},{"id":"814","name":"河北","pid":"0"},{"id":"998","name":"河南","pid":"0"},{"id":"1176","name":"黑龙江","pid":"0"},{"id":"1320","name":"湖北","pid":"0"},{"id":"1436","name":"湖南","pid":"0"},{"id":"1573","name":"吉林","pid":"0"},{"id":"1643","name":"江苏","pid":"0"},{"id":"1763","name":"江西","pid":"0"},{"id":"1874","name":"辽宁","pid":"0"},{"id":"1989","name":"内蒙古","pid":"0"},{"id":"2103","name":"宁夏","pid":"0"},{"id":"2130","name":"青海","pid":"0"},{"id":"2182","name":"山东","pid":"0"},{"id":"2340","name":"山西","pid":"0"},{"id":"2471","name":"陕西","pid":"0"},{"id":"2589","name":"四川","pid":"0"},{"id":"2792","name":"西藏","pid":"0"},{"id":"2873","name":"新疆","pid":"0"},{"id":"2987","name":"云南","pid":"0"},{"id":"3133","name":"浙江","pid":"0"},{"id":"3235","name":"香港","pid":"0"},{"id":"3239","name":"澳门","pid":"0"},{"id":"3242","name":"台湾","pid":"0"}]
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

    public static class ResultBean  implements  Serializable {
        /**
         * id : 1
         * name : 北京
         * pid : 0
         */

        private String id;
        private String name;
        private String pid;

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

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
    }
}
