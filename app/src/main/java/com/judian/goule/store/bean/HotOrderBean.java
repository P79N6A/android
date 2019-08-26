package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class HotOrderBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : [{"id":"16","user_id":"1","order_id":"15","title":"","content":"ddd","pict":"123123","is_cream":"0","gold":"0.00","verify":"2","zan":0,"status":"1","add_time":"2017-11-07 10:44:34","update_time":"2017-11-07 10:44:34","pict_url":"http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg","order_title":"正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤","nickname":"lala","avatar":""},{"id":"15","user_id":"1","order_id":"28","title":"","content":"ddd","pict":"123123","is_cream":"0","gold":"1.00","verify":"2","zan":"0","status":"1","add_time":"2017-10-27 16:31:08","update_time":"2017-10-27 16:31:08","pict_url":"","order_title":"春夏女装新款韩版大码中长款开衫空调衫防晒衣学生连帽风衣薄外套","nickname":"lala","avatar":""}]
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
         * id : 16
         * user_id : 1
         * order_id : 15
         * title :
         * content : ddd
         * pict : 123123
         * is_cream : 0
         * gold : 0.00
         * verify : 2
         * zan : 0
         * status : 1
         * add_time : 2017-11-07 10:44:34
         * update_time : 2017-11-07 10:44:34
         * pict_url : http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg
         * order_title : 正品翎茜美护肤化妆品专柜套装隔离深层补水保湿清洁收缩毛孔紧肤
         * nickname : lala
         * avatar :
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
        private int zan;
        private String status;
        private String add_time;
        private String update_time;
        private String pict_url;
        private String order_title;
        private String nickname;
        private String avatar;

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        private String num_iid;

        public String getIs_zan() {
            return is_zan;
        }

        public void setIs_zan(String is_zan) {
            this.is_zan = is_zan;
        }

        private String is_zan;

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

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
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

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getOrder_title() {
            return order_title;
        }

        public void setOrder_title(String order_title) {
            this.order_title = order_title;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            if (avatar==null)return "";
            else
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
