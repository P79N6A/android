package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/12/28.
 */

public class AreaDetailBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : {"id":"16","uid":"279","name":"xxxx","phone":"17096831274","address":"nkk担心","addtime":"1520664463","state":"0","province_id":"21","city_id":"22","area_id":"24"}
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
         * id : 16
         * uid : 279
         * name : xxxx
         * phone : 17096831274
         * address : nkk担心
         * addtime : 1520664463
         * state : 0
         * province_id : 21
         * city_id : 22
         * area_id : 24
         */

        private String id;
        private String uid;
        private String name;
        private String phone;
        private String address;
        private String addtime;
        private String state;
        private String province_id;
        private String city_id;
        private String area_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }
    }
}
