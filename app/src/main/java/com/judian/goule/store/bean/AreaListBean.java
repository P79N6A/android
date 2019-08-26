package com.judian.goule.store.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class AreaListBean {


    /**
     * code : 200
     * msg : 获取成功!
     * result : [{"id":"15","name":"ccy","phone":"17096831273","address":"vgg","state":"0","province_name":"重庆","city_name":"重庆市","area_name":"江北区"}]
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

    public static class ResultBean  implements Serializable {
        /**
         * id : 15
         * name : ccy
         * phone : 17096831273
         * address : vgg
         * state : 0
         * province_name : 重庆
         * city_name : 重庆市
         * area_name : 江北区
         */

        private String id;
        private String name;
        private String phone;
        private String address;
        private String state;
        private String province_name;
        private String city_name;
        private String area_name;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }
    }
}
