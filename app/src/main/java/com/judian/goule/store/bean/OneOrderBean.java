package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/12/28.
 */

public class OneOrderBean {

    /**
     * errcode : 200
     * errmsg : 获取成功!
     * result : {"address":{"id":"6","name":"耿XX","phone":15522222222,"province_name":"内蒙古自治区","city_name":"呼和浩特市","area_name":"赛罕区","address":"金桥国际"},"goods":{"id":"6","title":"测试1","pict_url":"http://47.104.98.190/Uploads/admin/1513218767_996186499.jpg","price":"900.00"},"user":{"usable":0,"alipay":"fffff@ee.com"}}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getErrcode() {
        return code;
    }

    public void setErrcode(int errcode) {
        this.code = errcode;
    }

    public String getErrmsg() {
        return msg;
    }

    public void setErrmsg(String errmsg) {
        this.msg = errmsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : {"id":"6","name":"耿XX","phone":15522222222,"province_name":"内蒙古自治区","city_name":"呼和浩特市","area_name":"赛罕区","address":"金桥国际"}
         * goods : {"id":"6","title":"测试1","pict_url":"http://47.104.98.190/Uploads/admin/1513218767_996186499.jpg","price":"900.00"}
         * user : {"usable":0,"alipay":"fffff@ee.com"}
         */

        private AddressBean address;
        private GoodsBean goods;
        private UserBean user;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class AddressBean {
            /**
             * id : 6
             * name : 耿XX
             * phone : 15522222222
             * province_name : 内蒙古自治区
             * city_name : 呼和浩特市
             * area_name : 赛罕区
             * address : 金桥国际
             */

            private String id;
            private String name;
            private String phone;
            private String province_name;
            private String city_name;
            private String area_name;
            private String address;

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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class GoodsBean {
            /**
             * id : 6
             * title : 测试1
             * pict_url : http://47.104.98.190/Uploads/admin/1513218767_996186499.jpg
             * price : 900.00
             */

            private String id;
            private String title;
            private String pict_url;
            private String price;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPict_url() {
                return pict_url;
            }

            public void setPict_url(String pict_url) {
                this.pict_url = pict_url;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }

        public static class UserBean {
            /**
             * usable : 0
             * alipay : fffff@ee.com
             */

            private String usable;
            private String alipay;

            public String getUsable() {
                return usable;
            }

            public void setUsable(String usable) {
                this.usable = usable;
            }

            public String getAlipay() {
                return alipay;
            }

            public void setAlipay(String alipay) {
                this.alipay = alipay;
            }
        }
    }
}
