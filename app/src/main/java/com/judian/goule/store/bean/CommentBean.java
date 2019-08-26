package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/10.
 */

public class CommentBean {

    /**
     * code : 200
     * msg : 获取成功
     * result : [{"id":"2","user_id":"1","cid":"20","zan":"0","apply_content":"ddd","is_read":"0","status":"1","type":"2","add_time":"2017-11-09 10:39:56","update_time":"2017-11-09 10:39:56","nickname":"lala","avatar":""}]
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
         * id : 2
         * user_id : 1
         * cid : 20
         * zan : 0
         * apply_content : ddd
         * is_read : 0
         * status : 1
         * type : 2
         * add_time : 2017-11-09 10:39:56
         * update_time : 2017-11-09 10:39:56
         * nickname : lala
         * avatar :
         */

        private String id;
        private String user_id;
        private String cid;
        private String zan;
        private String apply_content;
        private String is_read;
        private String status;
        private String type;
        private String add_time;
        private String update_time;
        private String nickname;
        private String avatar;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private String title;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        private String pic;

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

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getZan() {
            return zan;
        }

        public void setZan(String zan) {
            this.zan = zan;
        }

        public String getApply_content() {
            return apply_content;
        }

        public void setApply_content(String apply_content) {
            this.apply_content = apply_content;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
    }
}
