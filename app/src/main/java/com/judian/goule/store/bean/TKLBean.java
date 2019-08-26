package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */

public class TKLBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : {"result":{"category":"16","commission_rate":"20.00","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=0YFBY3WB%2FwIGQASttHIRqSCzr9Zsr3kejredGGV3JsVYomNqLX%2FiF5hwpiuCnDOFCs9E3FwFTLSAYljf9CCZmjiqekFoUM5PDfqEFBOhTcxdJ%2Fs7gA95F3DEk5G6NnVrJIZckPYkZu0WTPpPUoc594b%2FfW10abqX3afyDwEM%2FBD7J8FmRkgg%2BMMHqQ%2Bw9yuToFXV6Ih8LkeOw5fmoEel3g%3D%3D&traceId=0be5c56e15263615742113300e","coupon_end_time":"2018-05-20","coupon_info":["68","10"],"coupon_remain_count":"4830","coupon_start_time":"2018-05-14","coupon_total_count":"5000","item_description":"赠送运费险 显瘦修身90-200斤选","item_url":"http://detail.tmall.com/item.htm?id=548492071612","nick":"舒力娅艾帆皓轩专卖店","num_iid":"548492071612","pict_url":"http://img.alicdn.com/tfscom/i2/TB1VmFXQFXXXXcOaXXXXXXXXXXX_!!0-item_pic.jpg","seller_id":"1738659338","shop_title":"舒力娅艾帆皓轩专卖店","small_images":{"string":["http://img.alicdn.com/tfscom/i4/1738659338/TB2gfm_nEhnpuFjSZFpXXcpuXXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i4/1738659338/TB2nxvnnxhmpuFjSZFyXXcLdFXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i3/1738659338/TB2DT74h1tTMeFjSZFOXXaTiVXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i1/1738659338/TB2P31GnAqvpuFjSZFhXXaOgXXa_!!1738659338.jpg"]},"title":"加肥加大码女装浅蓝夏季新款破洞牛仔裙200斤胖mm半身裙A字中长裙","user_type":"1","volume":"405","zk_final_price":"69.00","coupon_money":"10","coupon_price":59,"fanli_money":23.6},"type":1}
     */

    private int code;
    private String msg;
    private ResultBeanX result;

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

    public ResultBeanX getResult() {
        return result;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    public static class ResultBeanX {
        /**
         * result : {"category":"16","commission_rate":"20.00","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=0YFBY3WB%2FwIGQASttHIRqSCzr9Zsr3kejredGGV3JsVYomNqLX%2FiF5hwpiuCnDOFCs9E3FwFTLSAYljf9CCZmjiqekFoUM5PDfqEFBOhTcxdJ%2Fs7gA95F3DEk5G6NnVrJIZckPYkZu0WTPpPUoc594b%2FfW10abqX3afyDwEM%2FBD7J8FmRkgg%2BMMHqQ%2Bw9yuToFXV6Ih8LkeOw5fmoEel3g%3D%3D&traceId=0be5c56e15263615742113300e","coupon_end_time":"2018-05-20","coupon_info":["68","10"],"coupon_remain_count":"4830","coupon_start_time":"2018-05-14","coupon_total_count":"5000","item_description":"赠送运费险 显瘦修身90-200斤选","item_url":"http://detail.tmall.com/item.htm?id=548492071612","nick":"舒力娅艾帆皓轩专卖店","num_iid":"548492071612","pict_url":"http://img.alicdn.com/tfscom/i2/TB1VmFXQFXXXXcOaXXXXXXXXXXX_!!0-item_pic.jpg","seller_id":"1738659338","shop_title":"舒力娅艾帆皓轩专卖店","small_images":{"string":["http://img.alicdn.com/tfscom/i4/1738659338/TB2gfm_nEhnpuFjSZFpXXcpuXXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i4/1738659338/TB2nxvnnxhmpuFjSZFyXXcLdFXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i3/1738659338/TB2DT74h1tTMeFjSZFOXXaTiVXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i1/1738659338/TB2P31GnAqvpuFjSZFhXXaOgXXa_!!1738659338.jpg"]},"title":"加肥加大码女装浅蓝夏季新款破洞牛仔裙200斤胖mm半身裙A字中长裙","user_type":"1","volume":"405","zk_final_price":"69.00","coupon_money":"10","coupon_price":59,"fanli_money":23.6}
         * type : 1
         */

        private ResultBean result;
        private String type;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public String  getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class ResultBean {
            /**
             * category : 16
             * commission_rate : 20.00
             * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=0YFBY3WB%2FwIGQASttHIRqSCzr9Zsr3kejredGGV3JsVYomNqLX%2FiF5hwpiuCnDOFCs9E3FwFTLSAYljf9CCZmjiqekFoUM5PDfqEFBOhTcxdJ%2Fs7gA95F3DEk5G6NnVrJIZckPYkZu0WTPpPUoc594b%2FfW10abqX3afyDwEM%2FBD7J8FmRkgg%2BMMHqQ%2Bw9yuToFXV6Ih8LkeOw5fmoEel3g%3D%3D&traceId=0be5c56e15263615742113300e
             * coupon_end_time : 2018-05-20
             * coupon_info : ["68","10"]
             * coupon_remain_count : 4830
             * coupon_start_time : 2018-05-14
             * coupon_total_count : 5000
             * item_description : 赠送运费险 显瘦修身90-200斤选
             * item_url : http://detail.tmall.com/item.htm?id=548492071612
             * nick : 舒力娅艾帆皓轩专卖店
             * num_iid : 548492071612
             * pict_url : http://img.alicdn.com/tfscom/i2/TB1VmFXQFXXXXcOaXXXXXXXXXXX_!!0-item_pic.jpg
             * seller_id : 1738659338
             * shop_title : 舒力娅艾帆皓轩专卖店
             * small_images : {"string":["http://img.alicdn.com/tfscom/i4/1738659338/TB2gfm_nEhnpuFjSZFpXXcpuXXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i4/1738659338/TB2nxvnnxhmpuFjSZFyXXcLdFXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i3/1738659338/TB2DT74h1tTMeFjSZFOXXaTiVXa_!!1738659338.jpg","http://img.alicdn.com/tfscom/i1/1738659338/TB2P31GnAqvpuFjSZFhXXaOgXXa_!!1738659338.jpg"]}
             * title : 加肥加大码女装浅蓝夏季新款破洞牛仔裙200斤胖mm半身裙A字中长裙
             * user_type : 1
             * volume : 405
             * zk_final_price : 69.00
             * coupon_money : 10
             * coupon_price : 59
             * fanli_money : 23.6
             */

            private String category;
            private String commission_rate;
            private String coupon_click_url;
            private String coupon_end_time;
            private String coupon_remain_count;
            private String coupon_start_time;
            private String coupon_total_count;
            private String item_url;
            private String nick;
            private String num_iid;
            private String pict_url;
            private String seller_id;
            private String shop_title;
            private String title;
            private String user_type;
            private String volume;
            private String zk_final_price;
            private String coupon_money;
            private String coupon_price;
            private String fanli_money;
            private List<String> coupon_info;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCommission_rate() {
                return commission_rate;
            }

            public void setCommission_rate(String commission_rate) {
                this.commission_rate = commission_rate;
            }

            public String getCoupon_click_url() {
                return coupon_click_url;
            }

            public void setCoupon_click_url(String coupon_click_url) {
                this.coupon_click_url = coupon_click_url;
            }

            public String getCoupon_end_time() {
                return coupon_end_time;
            }

            public void setCoupon_end_time(String coupon_end_time) {
                this.coupon_end_time = coupon_end_time;
            }

            public String getCoupon_remain_count() {
                return coupon_remain_count;
            }

            public void setCoupon_remain_count(String coupon_remain_count) {
                this.coupon_remain_count = coupon_remain_count;
            }

            public String getCoupon_start_time() {
                return coupon_start_time;
            }

            public void setCoupon_start_time(String coupon_start_time) {
                this.coupon_start_time = coupon_start_time;
            }

            public String getCoupon_total_count() {
                return coupon_total_count;
            }

            public void setCoupon_total_count(String coupon_total_count) {
                this.coupon_total_count = coupon_total_count;
            }


            public String getItem_url() {
                return item_url;
            }

            public void setItem_url(String item_url) {
                this.item_url = item_url;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getNum_iid() {
                return num_iid;
            }

            public void setNum_iid(String num_iid) {
                this.num_iid = num_iid;
            }

            public String getPict_url() {
                return pict_url;
            }

            public void setPict_url(String pict_url) {
                this.pict_url = pict_url;
            }

            public String getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(String seller_id) {
                this.seller_id = seller_id;
            }

            public String getShop_title() {
                return shop_title;
            }

            public void setShop_title(String shop_title) {
                this.shop_title = shop_title;
            }



            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getZk_final_price() {
                return zk_final_price;
            }

            public void setZk_final_price(String zk_final_price) {
                this.zk_final_price = zk_final_price;
            }

            public String getCoupon_money() {
                return coupon_money;
            }

            public void setCoupon_money(String coupon_money) {
                this.coupon_money = coupon_money;
            }

            public String  getCoupon_price() {
                return coupon_price;
            }

            public void setCoupon_price(String coupon_price) {
                this.coupon_price = coupon_price;
            }

            public String  getFanli_money() {
                return fanli_money;
            }

            public void setFanli_money(String fanli_money) {
                this.fanli_money = fanli_money;
            }

            public List<String> getCoupon_info() {
                return coupon_info;
            }

            public void setCoupon_info(List<String> coupon_info) {
                this.coupon_info = coupon_info;
            }

            public static class SmallImagesBean {
                private List<String> string;

                public List<String> getString() {
                    return string;
                }

                public void setString(List<String> string) {
                    this.string = string;
                }
            }
        }
    }
}
