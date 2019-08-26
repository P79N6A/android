package com.judian.goule.store.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class GoodListBean implements Serializable {


    /**
     * code : 200
     * msg : 查询成功
     * result : [{"id":"533","cate_id":"5","two_cate_id":"6","favorites_id":"114823103",aobao.com/item.htm?id=538051967543","shop_title":"大锦帽子高端7","cate_id":"5",元减15元","coupon_start_time":"2017-10-21","coupon_end_time":"2017-10-25",0","active_commission":"14.75","startiind_time":"","status":"1","shangxin":"0","zhutui":"0","zhutui_time":"0000-00-0000: 00: 00","add_time":"2017-10-2415: 04: 58","update_time":"2017-10-2713: 50: 14","reserve_price":"119.00","user_type":"0","tk_rate":"90.00","fanli":27,"fanli_money":14.85,"have_coupon":0}]
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

    public static class ResultBean implements Serializable {
        /**
         * id : 533
         * cate_id : 5
         * two_cate_id : 6
         * favorites_id : 114823103
         * num_iid : 538051967543
         * title : 韩国帽子秋冬貉子毛毛球帽欧美潮针织毛线帽女羊毛保暖加厚皮草帽
         * pict_url : http://img.alicdn.com/bao/uploaded/i3/2889581040/TB2EVxNaX55V1Bjy0FoXXbVjFXa_!!2889581040.jpg
         * item_url : http://item.taobao.com/item.htm?id=538051967543
         * shop_title : 大锦帽子高端配饰
         * price : 58.00
         * month_sales : 8
         * active_inc_scale : 50.00
         * active_commission : 29.00
         * start_time : 2017-10-27 00:00:00
         * end_time : 2017-11-04 00:00:00
         * seller_ww : 谢大发moment
         * coupon_number : 100000
         * coupon_surplus : 99835
         * couponamount : 5
         * coupon_money : 5
         * coupon_start_time : 2017-06-22
         * coupon_end_time : 2017-12-22
         * status : 1
         * shangxin : 0
         * zhutui : 0
         * zhutui_time : 0000-00-00 00:00:00
         * add_time : 2017-10-24 15:04:57
         * update_time : 2017-10-27 13:50:12
         * reserve_price : 120.00
         * user_type : 0
         * small_images : {"string":["http://img4.tbcdn.cn/tfscom/i1/TB1.O1ENXXXXXbRXVXXYXGcGpXX_M2.SS2","http://img1.tbcdn.cn/tfscom/i2/2889581040/TB26StJaai5V1BjSspaXXbrApXa_!!2889581040.jpg","http://img3.tbcdn.cn/tfscom/i3/2889581040/TB2zmhKaam5V1BjSspjXXcGFpXa_!!2889581040.jpg","http://img2.tbcdn.cn/tfscom/i7/TB1qhWMNXXXXXa7XFXXYXGcGpXX_M2.SS2"]}
         * tk_rate : 50.00
         * fanli : 15
         * fanli_money : 8.7
         * fanli_money_fenxiang : 8.7 分享赚
         * fanli_money_shengji : 8.7  升级赚
         * hava_coupon : 1
         * have_coupon : 0
         */

        private String id;
        private String goods_id;
        private String coupon_start_time;
        private String coupon_end_time;
        private String fanli_money_fenxiang;
        private String fanli_money_shengji;
        private String goods_num;
        private String goods_num_surplus;


        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getClick_num() {
            return click_num;
        }

        public void setClick_num(String click_num) {
            this.click_num = click_num;
        }

        private String click_num;

        public String getCoupon_click_url() {
            return coupon_click_url;
        }

        public void setCoupon_click_url(String coupon_click_url) {
            this.coupon_click_url = coupon_click_url;
        }

        private String coupon_click_url;

        public String getCoupon_link() {
            return coupon_link;
        }

        public void setCoupon_link(String coupon_link) {
            this.coupon_link = coupon_link;
        }

        private String coupon_link;

        public boolean isSel() {
            return isSel;
        }

        public void setSel(boolean sel) {
            isSel = sel;
        }

        private boolean isSel;

        public String getIntroduce() {
            if (introduce == null)
                return "";
            else
                return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        private String introduce;

        public String getType() {
            if (type == null)
                return "1";
            else
                return type;
        }

        public String getCoupon_start_time() {
            return coupon_start_time;
        }

        public void setCoupon_start_time(String coupon_start_time) {
            this.coupon_start_time = coupon_start_time;
        }

        public String getCoupon_end_time() {
            return coupon_end_time;
        }

        public void setCoupon_end_time(String coupon_end_time) {
            this.coupon_end_time = coupon_end_time;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;
        private String cate_id;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;

        public String getLink() {
            if (link == null) return "cccccccccccccc";
            else
                return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        private String link;

        private String num_iid;
        private String title;
        private String pict_url;
        private String item_url;

        private String price;

        public String getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        private String coupon_price;
        private String month_sales;


        private String coupon_money;


        private String status;


        private String add_time;
        private String update_time;
        private String reserve_price;
        private String user_type;
        private String small_images;


        private float fanli;
        private String fanli_money;

        private int have_coupon;

        public String getRebate_money() {
            return rebate_money;
        }

        public void setRebate_money(String rebate_money) {
            this.rebate_money = rebate_money;
        }

        private String rebate_money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }


        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
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

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }


        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMonth_sales() {
            return month_sales;
        }

        public void setMonth_sales(String month_sales) {
            this.month_sales = month_sales;
        }


        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public String getUser_type() {
            if (user_type == null) return "3";
            else
                return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public float getFanli() {
            return fanli;
        }

        public void setFanli(float fanli) {
            this.fanli = fanli;
        }

        public String getFanli_money() {
            return fanli_money;
        }

        public void setFanli_money(String fanli_money) {
            this.fanli_money = fanli_money;
        }


        public int getHave_coupon() {
            return have_coupon;
        }

        public void setHave_coupon(int have_coupon) {
            this.have_coupon = have_coupon;
        }

        public String getSmall_images() {
            return small_images;
        }

        public void setSmall_images(String small_images) {
            this.small_images = small_images;
        }

        public String getFanli_money_fenxiang() {
            return fanli_money_fenxiang;
        }

        public void setFanli_money_fenxiang(String fanli_money_fenxiang) {
            this.fanli_money_fenxiang = fanli_money_fenxiang;
        }

        public String getFanli_money_shengji() {
            return fanli_money_shengji;
        }

        public void setFanli_money_shengji(String fanli_money_shengji) {
            this.fanli_money_shengji = fanli_money_shengji;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_num_surplus() {
            return goods_num_surplus;
        }

        public void setGoods_num_surplus(String goods_num_surplus) {
            this.goods_num_surplus = goods_num_surplus;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", goods_id='" + goods_id + '\'' +
                    ", coupon_start_time='" + coupon_start_time + '\'' +
                    ", coupon_end_time='" + coupon_end_time + '\'' +
                    ", fanli_money_fenxiang='" + fanli_money_fenxiang + '\'' +
                    ", fanli_money_shengji='" + fanli_money_shengji + '\'' +
                    ", click_num='" + click_num + '\'' +
                    ", coupon_click_url='" + coupon_click_url + '\'' +
                    ", coupon_link='" + coupon_link + '\'' +
                    ", isSel=" + isSel +
                    ", introduce='" + introduce + '\'' +
                    ", type='" + type + '\'' +
                    ", cate_id='" + cate_id + '\'' +
                    ", url='" + url + '\'' +
                    ", link='" + link + '\'' +
                    ", num_iid='" + num_iid + '\'' +
                    ", title='" + title + '\'' +
                    ", pict_url='" + pict_url + '\'' +
                    ", item_url='" + item_url + '\'' +
                    ", price='" + price + '\'' +
                    ", coupon_price='" + coupon_price + '\'' +
                    ", month_sales='" + month_sales + '\'' +
                    ", coupon_money='" + coupon_money + '\'' +
                    ", status='" + status + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", update_time='" + update_time + '\'' +
                    ", reserve_price='" + reserve_price + '\'' +
                    ", user_type='" + user_type + '\'' +
                    ", small_images='" + small_images + '\'' +
                    ", fanli=" + fanli +
                    ", fanli_money='" + fanli_money + '\'' +
                    ", have_coupon=" + have_coupon +
                    ", rebate_money='" + rebate_money + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GoodListBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
