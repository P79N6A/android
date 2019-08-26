package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class OrderDetailBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : {"comment":{"id":"22","user_id":"1","order_id":"16","title":"rrrrrr","content":"The only thing that ","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03e368af5eb.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03e368b0b43.png\"}]","is_cream":"0","gold":"0.00","verify":"1","zan":"0","status":"1","add_time":"2017-11-09 13:11:04","update_time":"2017-11-09 13:11:04","lun":[{"path_name":"http://192.168.1.10:81/Uploads/comment/2017-11-09/5a03e368af5eb.png"},{"path_name":"http://192.168.1.10:81/Uploads/comment/2017-11-09/5a03e368b0b43.png"}],"avatar":"","nickname":"lala","level":"1","sex":"1","total":"11","is_zan":0},"goods":{"num_iid":542798233906,"title":"正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤","pict_url":"http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg","price":29.9,"month_sales":49,"reserve_price":39.9,"user_type":0,"active_inc_scale":20,"have_coupon":0,"fanli_money":1.79}}
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

    public static class ResultBean {
        /**
         * comment : {"id":"22","user_id":"1","order_id":"16","title":"rrrrrr","content":"The only thing that ","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03e368af5eb.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03e368b0b43.png\"}]","is_cream":"0","gold":"0.00","verify":"1","zan":"0","status":"1","add_time":"2017-11-09 13:11:04","update_time":"2017-11-09 13:11:04","lun":[{"path_name":"http://192.168.1.10:81/Uploads/comment/2017-11-09/5a03e368af5eb.png"},{"path_name":"http://192.168.1.10:81/Uploads/comment/2017-11-09/5a03e368b0b43.png"}],"avatar":"","nickname":"lala","level":"1","sex":"1","total":"11","is_zan":0}
         * goods : {"num_iid":542798233906,"title":"正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤","pict_url":"http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg","price":29.9,"month_sales":49,"reserve_price":39.9,"user_type":0,"active_inc_scale":20,"have_coupon":0,"fanli_money":1.79}
         */

        private CommentBean comment;
        private GoodsBean goods;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String  url;

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class CommentBean {
            /**
             * id : 22
             * user_id : 1
             * order_id : 16
             * title : rrrrrr
             * content : The only thing that
             * pict : [{"path_name":"http:\/\/192.168.1.10:81\/Uploads\/comment\/2017-11-09\/5a03e368af5eb.png"},{"path_name":"http:\/\/192.168.1.10:81\/Uploads\/comment\/2017-11-09\/5a03e368b0b43.png"}]
             * is_cream : 0
             * gold : 0.00
             * verify : 1
             * zan : 0
             * status : 1
             * add_time : 2017-11-09 13:11:04
             * update_time : 2017-11-09 13:11:04
             * lun : [{"path_name":"http://192.168.1.10:81/Uploads/comment/2017-11-09/5a03e368af5eb.png"},{"path_name":"http://192.168.1.10:81/Uploads/comment/2017-11-09/5a03e368b0b43.png"}]
             * avatar :
             * nickname : lala
             * level : 1
             * sex : 1
             * total : 11
             * is_zan : 0
             */

            private String id;
            private String user_id;
            private String order_id;
            private String title;
            private String content;
            private String pict;
            private String is_cream;
            private String gold;
            private String verify;
            private String zan;
            private String status;
            private String add_time;
            private String update_time;
            private String avatar;
            private String nickname;
            private String level;
            private String zan_num;
            private List<ImgBean> img;
            public String getZan_num() {
                return zan_num+"";
            }

            public void setZan_num(String zan_num) {
                this.zan_num = zan_num;
            }

            public String getComment_num() {
                return comment_num+"";
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            private String comment_num;
            private String sex;


            private String total;
            private int is_zan;
            private List<LunBean> lun;
            public List<ImgBean> getImg() {
                return img;
            }

            public void setImg(List<ImgBean> img) {
                this.img = img;
            }
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPict() {
                return pict;
            }

            public void setPict(String pict) {
                this.pict = pict;
            }

            public String getIs_cream() {
                return is_cream;
            }

            public void setIs_cream(String is_cream) {
                this.is_cream = is_cream;
            }

            public String getGold() {
                return gold;
            }

            public void setGold(String gold) {
                this.gold = gold;
            }

            public String getVerify() {
                return verify;
            }

            public void setVerify(String verify) {
                this.verify = verify;
            }

            public String getZan() {
                return zan;
            }

            public void setZan(String zan) {
                this.zan = zan;
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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getSex() {
                if (sex==null)return "1";
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public int getIs_zan() {
                return is_zan;
            }

            public void setIs_zan(int is_zan) {
                this.is_zan = is_zan;
            }

            public List<LunBean> getLun() {
                return lun;
            }

            public void setLun(List<LunBean> lun) {
                this.lun = lun;
            }
            public static class ImgBean {
                /**
                 * height : 576
                 * width : 432
                 */

                private int height;
                private int width;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }
            }
            public static class LunBean {
                /**
                 * path_name : http://192.168.1.10:81/Uploads/comment/2017-11-09/5a03e368af5eb.png
                 */

                private String path_name;

                public String getPath_name() {
                    return path_name;
                }

                public void setPath_name(String path_name) {
                    this.path_name = path_name;
                }
            }
        }

        public static class GoodsBean {
            /**
             * num_iid : 542798233906
             * title : 正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤
             * pict_url : http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg
             * price : 29.9
             * month_sales : 49
             * reserve_price : 39.9
             * user_type : 0
             * active_inc_scale : 20
             * have_coupon : 0
             * fanli_money : 1.79
             */
            public String getCoupon_money() {
                return coupon_money;
            }

            public void setCoupon_money(String coupon_money) {
                this.coupon_money = coupon_money;
            }

            private String coupon_money;
            private String num_iid;
            private String title;
            private String pict_url;
            private String price;
            private int month_sales;
            private String reserve_price;
            private int user_type;
            private String active_inc_scale;
            private int have_coupon;
            private String fanli_money;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getMonth_sales() {
                return month_sales;
            }

            public void setMonth_sales(int month_sales) {
                this.month_sales = month_sales;
            }

            public String getReserve_price() {
                return reserve_price;
            }

            public void setReserve_price(String reserve_price) {
                this.reserve_price = reserve_price;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }

            public String getActive_inc_scale() {
                return active_inc_scale;
            }

            public void setActive_inc_scale(String active_inc_scale) {
                this.active_inc_scale = active_inc_scale;
            }

            public int getHave_coupon() {
                return have_coupon;
            }

            public void setHave_coupon(int have_coupon) {
                this.have_coupon = have_coupon;
            }

            public String getFanli_money() {
                return fanli_money;
            }

            public void setFanli_money(String fanli_money) {
                this.fanli_money = fanli_money;
            }
        }
    }
}
