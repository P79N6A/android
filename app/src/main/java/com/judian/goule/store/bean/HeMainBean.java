package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class HeMainBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : {"user_info":{"nickname":"lala","level":"1","user_id":"1","avatar":""},"total":"4","comment_list":[{"id":"24","user_id":"1","order_id":"16","title":"qqqqq","content":"Aaaaaa","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb5d07.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb642f.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb6bf2.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb7298.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb7ade.png\"}]","is_cream":"0","gold":"0.00","verify":"2","zan":"0","status":"1","add_time":"2017-11-09 13:44:59","update_time":"2017-11-09 13:44:59","nickname":"lala","avatar":"","pict_url":"http://img.alicdn.com/bao/uploaded/i1/2091024697/TB1h4QZoMoQMeJjy0FpXXcTxpXa_!!0-item_pic.jpg"},{"id":"23","user_id":"1","order_id":"16","title":"rrre","content":"Eeee","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03ea9f0b15c.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03ea9f0b8f7.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03","is_cream":"1","gold":"0.00","verify":"2","zan":"0","status":"1","add_time":"2017-11-09 13:41:51","update_time":"2017-11-09 13:41:51","nickname":"lala","avatar":"","pict_url":"http://img.alicdn.com/bao/uploaded/i1/2091024697/TB1h4QZoMoQMeJjy0FpXXcTxpXa_!!0-item_pic.jpg"},{"id":"22","user_id":"1","order_id":"16","title":"rrrrrr","content":"The only thing that ","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03e368af5eb.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03e368b0b43.png\"}]","is_cream":"0","gold":"0.00","verify":"2","zan":"0","status":"1","add_time":"2017-11-09 13:11:04","update_time":"2017-11-09 13:11:04","nickname":"lala","avatar":"","pict_url":"http://img.alicdn.com/bao/uploaded/i1/2091024697/TB1h4QZoMoQMeJjy0FpXXcTxpXa_!!0-item_pic.jpg"},{"id":"20","user_id":"1","order_id":"15","title":"一起","content":"不不不宝贝宝贝宝贝巴巴爸爸好爸爸不会后悔哈哈哈干活刚刚","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-08\\/5a02bebf46ed0.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-08\\/5a02bebf470b4.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-08\\/5a02","is_cream":"0","gold":"0.00","verify":"2","zan":"0","status":"1","add_time":"2017-11-08 16:22:23","update_time":"2017-11-08 16:22:23","nickname":"lala","avatar":"","pict_url":"http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg"}]}
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
         * user_info : {"nickname":"lala","level":"1","user_id":"1","avatar":""}
         * total : 4
         * comment_list : [{"id":"24","user_id":"1","order_id":"16","title":"qqqqq","content":"Aaaaaa","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb5d07.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb642f.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb6bf2.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb7298.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03eb5bb7ade.png\"}]","is_cream":"0","gold":"0.00","verify":"2","zan":"0","status":"1","add_time":"2017-11-09 13:44:59","update_time":"2017-11-09 13:44:59","nickname":"lala","avatar":"","pict_url":"http://img.alicdn.com/bao/uploaded/i1/2091024697/TB1h4QZoMoQMeJjy0FpXXcTxpXa_!!0-item_pic.jpg"},{"id":"23","user_id":"1","order_id":"16","title":"rrre","content":"Eeee","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03ea9f0b15c.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03ea9f0b8f7.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03","is_cream":"1","gold":"0.00","verify":"2","zan":"0","status":"1","add_time":"2017-11-09 13:41:51","update_time":"2017-11-09 13:41:51","nickname":"lala","avatar":"","pict_url":"http://img.alicdn.com/bao/uploaded/i1/2091024697/TB1h4QZoMoQMeJjy0FpXXcTxpXa_!!0-item_pic.jpg"},{"id":"22","user_id":"1","order_id":"16","title":"rrrrrr","content":"The only thing that ","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03e368af5eb.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-09\\/5a03e368b0b43.png\"}]","is_cream":"0","gold":"0.00","verify":"2","zan":"0","status":"1","add_time":"2017-11-09 13:11:04","update_time":"2017-11-09 13:11:04","nickname":"lala","avatar":"","pict_url":"http://img.alicdn.com/bao/uploaded/i1/2091024697/TB1h4QZoMoQMeJjy0FpXXcTxpXa_!!0-item_pic.jpg"},{"id":"20","user_id":"1","order_id":"15","title":"一起","content":"不不不宝贝宝贝宝贝巴巴爸爸好爸爸不会后悔哈哈哈干活刚刚","pict":"[{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-08\\/5a02bebf46ed0.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-08\\/5a02bebf470b4.png\"},{\"path_name\":\"http:\\/\\/192.168.1.10:81\\/Uploads\\/comment\\/2017-11-08\\/5a02","is_cream":"0","gold":"0.00","verify":"2","zan":"0","status":"1","add_time":"2017-11-08 16:22:23","update_time":"2017-11-08 16:22:23","nickname":"lala","avatar":"","pict_url":"http://img.alicdn.com/bao/uploaded/i2/2765239567/TB2aeuwXMvD8KJjy0FlXXagBFXa_!!2765239567.jpg"}]
         */

        private UserInfoBean user_info;
        private String total;
        private List<CommentListBean> comment_list;

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<CommentListBean> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public static class UserInfoBean {
            /**
             * nickname : lala
             * level : 1
             * user_id : 1
             * avatar :
             */

            private String nickname;
            private String level;
            private String user_id;
            private String avatar;

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

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class CommentListBean {
            /**
             * id : 24
             * user_id : 1
             * order_id : 16
             * title : qqqqq
             * content : Aaaaaa
             * pict : [{"path_name":"http:\/\/192.168.1.10:81\/Uploads\/comment\/2017-11-09\/5a03eb5bb5d07.png"},{"path_name":"http:\/\/192.168.1.10:81\/Uploads\/comment\/2017-11-09\/5a03eb5bb642f.png"},{"path_name":"http:\/\/192.168.1.10:81\/Uploads\/comment\/2017-11-09\/5a03eb5bb6bf2.png"},{"path_name":"http:\/\/192.168.1.10:81\/Uploads\/comment\/2017-11-09\/5a03eb5bb7298.png"},{"path_name":"http:\/\/192.168.1.10:81\/Uploads\/comment\/2017-11-09\/5a03eb5bb7ade.png"}]
             * is_cream : 0
             * gold : 0.00
             * verify : 2
             * zan : 0
             * status : 1
             * add_time : 2017-11-09 13:44:59
             * update_time : 2017-11-09 13:44:59
             * nickname : lala
             * avatar :
             * pict_url : http://img.alicdn.com/bao/uploaded/i1/2091024697/TB1h4QZoMoQMeJjy0FpXXcTxpXa_!!0-item_pic.jpg
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
            private String nickname;
            private String avatar;
            private String pict_url;

            public String getNum_iid() {
                return num_iid;
            }

            public void setNum_iid(String num_iid) {
                this.num_iid = num_iid;
            }

            private String num_iid;

            public String getId() {
                return id;
            }

            public String getIs_zan() {
                return is_zan;
            }

            public void setIs_zan(String is_zan) {
                this.is_zan = is_zan;
            }

            private String is_zan;

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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getPict_url() {
                return pict_url;
            }

            public void setPict_url(String pict_url) {
                this.pict_url = pict_url;
            }
        }
    }
}
