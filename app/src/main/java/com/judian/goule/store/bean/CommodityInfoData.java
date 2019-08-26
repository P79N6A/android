package com.judian.goule.store.bean;


import java.io.Serializable;

/**
 * 商品详情
 */
public class CommodityInfoData implements Serializable {
    /**
     * code : 200
     * msg : 查询成功
     * result : {"id":"19","status":"0","createtime":"2018-07-24 10:48:08","updatetime":"0000-00-00 00:00:00","shop_title":"天猫超市","user_type":"1","zk_final_price":"120.00","title":"良品铺子猪肉脯鸭脖牛肉粒肉类豆干组合休闲吃货零食大礼包1216g","nick":"天猫超市","seller_id":"725677994","volume":"287","pict_url":"https://img.alicdn.com/tfscom/i2/725677994/TB1AGpWXk7mBKNjSZFyXXbydFXa_!!0-item_pic.jpg","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=564680324431","coupon_total_count":"500000","coupon_remain_count":"305000","commission_rate":"2.00","coupon_money":"3","coupon_info":"满120元减3元","category":"50002766","num_iid":"564680324431","coupon_start_time":"2018-06-27","coupon_end_time":"2018-07-31","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=wsMhFMkGs1sGQASttHIRqcx8P20jVl2wzd3ftYTu6x%2BHg%2FcRiEsEh%2FEy6zb%2BEYy0fIkOqErjS59bFDsE0ICEKDEhJpUUrcnYxmNMtGupSV49neUHUGa4sa1hbRgwYUOf","item_description":"","small_images":"https://img.alicdn.com/tfscom/i3/725677994/TB20Io_Xk9WBuNjSspeXXaz5VXa_!!725677994.jpg,https://img.alicdn.com/tfscom/i4/725677994/TB2U50XXxGYBuNjy0FnXXX5lpXa_!!725677994.jpg,https://img.alicdn.com/tfscom/i4/725677994/TB23Z6EDx1YBuNjy1zcXXbNcXXa_!!725677994.jpg,https://img.alicdn.com/tfscom/i1/725677994/TB206eRXCBYBeNjy0FeXXbnmFXa_!!725677994.jpg","listorder":"","tpwd":"￥4aEeb0kDCWt￥","cate_id":"385","two_cate_id":"387","sort":"0","coupon_price":"117.00","commission":"2.34","click_num":"0","cg_id":"","favorites_id":"18187275","provcity":"上海","reserve_price":"120","share_a_profit":"1","if_brand":"1","if_video":"0"}
     */

    private int code;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * id : 19
         * status : 0
         * createtime : 2018-07-24 10:48:08
         * updatetime : 0000-00-00 00:00:00
         * shop_title : 天猫超市
         * user_type : 1
         * zk_final_price : 120.00
         * title : 良品铺子猪肉脯鸭脖牛肉粒肉类豆干组合休闲吃货零食大礼包1216g
         * nick : 天猫超市
         * seller_id : 725677994
         * volume : 287
         * pict_url : https://img.alicdn.com/tfscom/i2/725677994/TB1AGpWXk7mBKNjSZFyXXbydFXa_!!0-item_pic.jpg
         * item_url : http://h5.m.taobao.com/awp/core/detail.htm?id=564680324431
         * coupon_total_count : 500000
         * coupon_remain_count : 305000
         * commission_rate : 2.00
         * coupon_money : 3
         * coupon_info : 满120元减3元
         * category : 50002766
         * num_iid : 564680324431
         * coupon_start_time : 2018-06-27
         * coupon_end_time : 2018-07-31
         * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=wsMhFMkGs1sGQASttHIRqcx8P20jVl2wzd3ftYTu6x%2BHg%2FcRiEsEh%2FEy6zb%2BEYy0fIkOqErjS59bFDsE0ICEKDEhJpUUrcnYxmNMtGupSV49neUHUGa4sa1hbRgwYUOf
         * item_description :
         * small_images : https://img.alicdn.com/tfscom/i3/725677994/TB20Io_Xk9WBuNjSspeXXaz5VXa_!!725677994.jpg,https://img.alicdn.com/tfscom/i4/725677994/TB2U50XXxGYBuNjy0FnXXX5lpXa_!!725677994.jpg,https://img.alicdn.com/tfscom/i4/725677994/TB23Z6EDx1YBuNjy1zcXXbNcXXa_!!725677994.jpg,https://img.alicdn.com/tfscom/i1/725677994/TB206eRXCBYBeNjy0FeXXbnmFXa_!!725677994.jpg
         * listorder :
         * tpwd : ￥4aEeb0kDCWt￥
         * cate_id : 385
         * two_cate_id : 387
         * sort : 0
         * coupon_price : 117.00
         * commission : 2.34
         * click_num : 0
         * cg_id :
         * favorites_id : 18187275
         * provcity : 上海
         * reserve_price : 120
         * share_a_profit : 1
         * if_brand : 1
         * if_video : 0
         * favorite : 0  是否商城该商品  0 是没有收藏 1 有收藏
         */

        private String id;
        private String status;
        private String createtime;
        private String updatetime;
        private String shop_title;
        private String user_type;
        private String zk_final_price;
        private String title;
        private String nick;
        private String seller_id;
        private String volume;
        private String pict_url;
        private String item_url;
        private String coupon_total_count;
        private String coupon_remain_count;
        private String commission_rate;
        private String coupon_money;
        private String coupon_info;
        private String category;
        private String num_iid;
        private String coupon_start_time;
        private String coupon_end_time;
        private String coupon_click_url;
        private String item_description;
        private String small_images;
        private String listorder;
        private String tpwd;
        private String cate_id;
        private String two_cate_id;
        private String sort;
        private String coupon_price;
        private String commission;
        private String click_num;
        private String cg_id;
        private String favorites_id;
        private String provcity;
        private String reserve_price;
        private String share_a_profit;
        private String if_brand;
        private String if_video;
        private String fenxiang_money;
        private String zigou_money;
        private String favorite;
        private String coupon_link;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getZk_final_price() {
            return zk_final_price;
        }

        public void setZk_final_price(String zk_final_price) {
            this.zk_final_price = zk_final_price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
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

        public String getCoupon_total_count() {
            return coupon_total_count;
        }

        public void setCoupon_total_count(String coupon_total_count) {
            this.coupon_total_count = coupon_total_count;
        }

        public String getCoupon_remain_count() {
            return coupon_remain_count;
        }

        public void setCoupon_remain_count(String coupon_remain_count) {
            this.coupon_remain_count = coupon_remain_count;
        }

        public String getCommission_rate() {
            return commission_rate;
        }

        public void setCommission_rate(String commission_rate) {
            this.commission_rate = commission_rate;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        public String getCoupon_info() {
            return coupon_info;
        }

        public void setCoupon_info(String coupon_info) {
            this.coupon_info = coupon_info;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
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

        public String getCoupon_click_url() {
            return coupon_click_url;
        }

        public void setCoupon_click_url(String coupon_click_url) {
            this.coupon_click_url = coupon_click_url;
        }

        public String getItem_description() {
            return item_description;
        }

        public void setItem_description(String item_description) {
            this.item_description = item_description;
        }

        public String getSmall_images() {
            return small_images;
        }

        public void setSmall_images(String small_images) {
            this.small_images = small_images;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getTpwd() {
            return tpwd;
        }

        public void setTpwd(String tpwd) {
            this.tpwd = tpwd;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getTwo_cate_id() {
            return two_cate_id;
        }

        public void setTwo_cate_id(String two_cate_id) {
            this.two_cate_id = two_cate_id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getClick_num() {
            return click_num;
        }

        public void setClick_num(String click_num) {
            this.click_num = click_num;
        }

        public String getCg_id() {
            return cg_id;
        }

        public void setCg_id(String cg_id) {
            this.cg_id = cg_id;
        }

        public String getFavorites_id() {
            return favorites_id;
        }

        public void setFavorites_id(String favorites_id) {
            this.favorites_id = favorites_id;
        }

        public String getProvcity() {
            return provcity;
        }

        public void setProvcity(String provcity) {
            this.provcity = provcity;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public String getShare_a_profit() {
            return share_a_profit;
        }

        public void setShare_a_profit(String share_a_profit) {
            this.share_a_profit = share_a_profit;
        }

        public String getIf_brand() {
            return if_brand;
        }

        public void setIf_brand(String if_brand) {
            this.if_brand = if_brand;
        }

        public String getIf_video() {
            return if_video;
        }

        public void setIf_video(String if_video) {
            this.if_video = if_video;
        }

        public String getFenxiang_money() {
            return fenxiang_money;
        }

        public void setFenxiang_money(String fenxiang_money) {
            this.fenxiang_money = fenxiang_money;
        }

        public String getZigou_money() {
            return zigou_money;
        }

        public void setZigou_money(String zigou_money) {
            this.zigou_money = zigou_money;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

        public String getCoupon_link() {
            return coupon_link;
        }

        public void setCoupon_link(String coupon_link) {
            this.coupon_link = coupon_link;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", status='" + status + '\'' +
                    ", createtime='" + createtime + '\'' +
                    ", updatetime='" + updatetime + '\'' +
                    ", shop_title='" + shop_title + '\'' +
                    ", user_type='" + user_type + '\'' +
                    ", zk_final_price='" + zk_final_price + '\'' +
                    ", title='" + title + '\'' +
                    ", nick='" + nick + '\'' +
                    ", seller_id='" + seller_id + '\'' +
                    ", volume='" + volume + '\'' +
                    ", pict_url='" + pict_url + '\'' +
                    ", item_url='" + item_url + '\'' +
                    ", coupon_total_count='" + coupon_total_count + '\'' +
                    ", coupon_remain_count='" + coupon_remain_count + '\'' +
                    ", commission_rate='" + commission_rate + '\'' +
                    ", coupon_money='" + coupon_money + '\'' +
                    ", coupon_info='" + coupon_info + '\'' +
                    ", category='" + category + '\'' +
                    ", num_iid='" + num_iid + '\'' +
                    ", coupon_start_time='" + coupon_start_time + '\'' +
                    ", coupon_end_time='" + coupon_end_time + '\'' +
                    ", coupon_click_url='" + coupon_click_url + '\'' +
                    ", item_description='" + item_description + '\'' +
                    ", small_images='" + small_images + '\'' +
                    ", listorder='" + listorder + '\'' +
                    ", tpwd='" + tpwd + '\'' +
                    ", cate_id='" + cate_id + '\'' +
                    ", two_cate_id='" + two_cate_id + '\'' +
                    ", sort='" + sort + '\'' +
                    ", coupon_price='" + coupon_price + '\'' +
                    ", commission='" + commission + '\'' +
                    ", click_num='" + click_num + '\'' +
                    ", cg_id='" + cg_id + '\'' +
                    ", favorites_id='" + favorites_id + '\'' +
                    ", provcity='" + provcity + '\'' +
                    ", reserve_price='" + reserve_price + '\'' +
                    ", share_a_profit='" + share_a_profit + '\'' +
                    ", if_brand='" + if_brand + '\'' +
                    ", if_video='" + if_video + '\'' +
                    ", fenxiang_money='" + fenxiang_money + '\'' +
                    ", zigou_money='" + zigou_money + '\'' +
                    ", favorite='" + favorite + '\'' +
                    ", coupon_link='" + coupon_link + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CommodityInfoData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
