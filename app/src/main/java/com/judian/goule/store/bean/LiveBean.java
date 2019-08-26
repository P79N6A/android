package com.judian.goule.store.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class LiveBean implements Serializable  {


    /**
     * code : 200
     * msg : 获取成功
     * result : {"last_id":"111","count":10,"live_msg":[{"id":"111","pic":"http://140.143.191.119/img/19.jpg","title":"","price":"","content":"券后比","link":"https://detail.tmall.com/item.htm?id=538123913844","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=473ffe57bcbd413c94637ba25cf398d5&sellerId=2096587469","add_time":"2017-11-15 16:01:10"},{"id":"110","pic":"http://140.143.191.119/img/18.jpg","title":"","price":"","content":"热卖好枸杞","link":"https://item.taobao.com/item.htm?id=524894486373","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=1cf868f6635a41e2b14458110984d26a&sellerId=845567592","add_time":"2017-11-15 16:00:43"},{"id":"109","pic":"/4.jpg","title":"","price":"","content":"福利功效，每盒50包","link":"https://detail.tmall.com/item.htm?id=38564696067","coupon_link":"https://shop.m.taobao.com/shop/coupon.htm?activityId=4b533ebce1c145a1a82675f4ac20c617&sellerId=759721784","add_time":"2017-11-15 15:37:24"},{"id":"108","pic":"/3.jpg","title":"","price":"","content":"超厚实重达","link":"https://detail.tmall.com/item.htm?id=557121421780","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=342671a345224d6ca82c9bb07c1b8819&sellerId=2914320128","add_time":"2017-11-15 15:36:56"},{"id":"107","pic":"http://140.143.191.119/img/2.jpg","title":"","price":"","content":"漏洞！漏洞！漏洞！拍3件","link":"https://detail.tmall.com/item.htm?id=547075477676","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=77739bdaa8c24644b0c9258994350954&sellerId=2189748653","add_time":"2017-11-15 15:30:39"},{"id":"106","pic":"http://140.143.191.119/img/1.jpg","title":"","price":"","content":"质量超级","link":"https://detail.tmall.com/item.htm?id=540177443165","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=2dbb20b84923442d86a4e984612c6d76&sellerId=2058279615","add_time":"2017-11-15 15:30:14"},{"id":"105","pic":"http://140.143.191.119/img/17.jpg","title":"","price":"","content":"【志高】家用多","link":"https://detail.tmall.com/item.htm?id=537318558265","coupon_link":"https://shop.m.taobao.com/shop/coupon.htm?seller_id=2712935181&activity_id=3ba75f152eb946cf8f80f97db4b5fea2","add_time":"2017-11-15 15:25:33"},{"id":"104","pic":"http://140.143.191.119/img/16.jpg","title":"","price":"","content":"质量超","link":"https://detail.tmall.com/item.htm?id=540177443165","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=2dbb20b84923442d86a4e984612c6d76&sellerId=2058279615","add_time":"2017-11-15 15:07:01"},{"id":"103","pic":"http://140.143.191.119/img/15.jpg","title":"","price":"","content":"【中粮集","link":"https://item.taobao.com/item.htm?id=539512909127","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=2b9a5c1c6d6f4e74a52318d69db58b0b&sellerId=2999717256","add_time":"2017-11-15 15:06:55"},{"id":"102","pic":"http://140.143.191.119/img/14.jpg","title":"","price":"","content":"【依徕乐冬季高腰保暖","link":"https://detail.tmall.com/item.htm?id=560228998217","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=ac8ea7817e3c47d1b8f5c4d833fc32e9&sellerId=2973162389","add_time":"2017-11-15 15:06:51"}]}
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

    public static class ResultBean implements Serializable  {
        /**
         * last_id : 111
         * count : 10
         * live_msg : [{"id":"111","pic":"http://140.143.191.119/img/19.jpg","title":"","price":"","content":"券后比","link":"https://detail.tmall.com/item.htm?id=538123913844","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=473ffe57bcbd413c94637ba25cf398d5&sellerId=2096587469","add_time":"2017-11-15 16:01:10"},{"id":"110","pic":"http://140.143.191.119/img/18.jpg","title":"","price":"","content":"热卖好枸杞","link":"https://item.taobao.com/item.htm?id=524894486373","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=1cf868f6635a41e2b14458110984d26a&sellerId=845567592","add_time":"2017-11-15 16:00:43"},{"id":"109","pic":"/4.jpg","title":"","price":"","content":"福利功效，每盒50包","link":"https://detail.tmall.com/item.htm?id=38564696067","coupon_link":"https://shop.m.taobao.com/shop/coupon.htm?activityId=4b533ebce1c145a1a82675f4ac20c617&sellerId=759721784","add_time":"2017-11-15 15:37:24"},{"id":"108","pic":"/3.jpg","title":"","price":"","content":"超厚实重达","link":"https://detail.tmall.com/item.htm?id=557121421780","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=342671a345224d6ca82c9bb07c1b8819&sellerId=2914320128","add_time":"2017-11-15 15:36:56"},{"id":"107","pic":"http://140.143.191.119/img/2.jpg","title":"","price":"","content":"漏洞！漏洞！漏洞！拍3件","link":"https://detail.tmall.com/item.htm?id=547075477676","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=77739bdaa8c24644b0c9258994350954&sellerId=2189748653","add_time":"2017-11-15 15:30:39"},{"id":"106","pic":"http://140.143.191.119/img/1.jpg","title":"","price":"","content":"质量超级","link":"https://detail.tmall.com/item.htm?id=540177443165","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=2dbb20b84923442d86a4e984612c6d76&sellerId=2058279615","add_time":"2017-11-15 15:30:14"},{"id":"105","pic":"http://140.143.191.119/img/17.jpg","title":"","price":"","content":"【志高】家用多","link":"https://detail.tmall.com/item.htm?id=537318558265","coupon_link":"https://shop.m.taobao.com/shop/coupon.htm?seller_id=2712935181&activity_id=3ba75f152eb946cf8f80f97db4b5fea2","add_time":"2017-11-15 15:25:33"},{"id":"104","pic":"http://140.143.191.119/img/16.jpg","title":"","price":"","content":"质量超","link":"https://detail.tmall.com/item.htm?id=540177443165","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=2dbb20b84923442d86a4e984612c6d76&sellerId=2058279615","add_time":"2017-11-15 15:07:01"},{"id":"103","pic":"http://140.143.191.119/img/15.jpg","title":"","price":"","content":"【中粮集","link":"https://item.taobao.com/item.htm?id=539512909127","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=2b9a5c1c6d6f4e74a52318d69db58b0b&sellerId=2999717256","add_time":"2017-11-15 15:06:55"},{"id":"102","pic":"http://140.143.191.119/img/14.jpg","title":"","price":"","content":"【依徕乐冬季高腰保暖","link":"https://detail.tmall.com/item.htm?id=560228998217","coupon_link":"http://shop.m.taobao.com/shop/coupon.htm?activityId=ac8ea7817e3c47d1b8f5c4d833fc32e9&sellerId=2973162389","add_time":"2017-11-15 15:06:51"}]
         */

        private String last_id;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        private String num;

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        private String start_time;
        private int count;
        private List<LiveMsgBean> live_msg;

        public String getLast_id() {
            return last_id;
        }

        public void setLast_id(String last_id) {
            this.last_id = last_id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<LiveMsgBean> getLive_msg() {
            return live_msg;
        }

        public void setLive_msg(List<LiveMsgBean> live_msg) {
            this.live_msg = live_msg;
        }

        public static class LiveMsgBean implements Serializable {
            /**
             * id : 111
             * pic : http://140.143.191.119/img/19.jpg
             * title :
             * price :
             * content : 券后比
             * link : https://detail.tmall.com/item.htm?id=538123913844
             * coupon_link : http://shop.m.taobao.com/shop/coupon.htm?activityId=473ffe57bcbd413c94637ba25cf398d5&sellerId=2096587469
             * add_time : 2017-11-15 16:01:10
             */

            private String id;
            private String pic;
            private String title;
            private String price;
            private String content;
            private String link;
            private String coupon_link;

            public String getNum_iid() {
                return num_iid;
            }

            public void setNum_iid(String num_iid) {
                this.num_iid = num_iid;
            }

            private String num_iid;
            private String add_time;

            public String getNow_time() {
                return now_time;
            }

            public void setNow_time(String now_time) {
                this.now_time = now_time;
            }

            private String now_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getCoupon_link() {
                return coupon_link;
            }

            public void setCoupon_link(String coupon_link) {
                this.coupon_link = coupon_link;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }
        }
    }
}
