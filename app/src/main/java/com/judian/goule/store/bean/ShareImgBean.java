package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ShareImgBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : {"me_url":"http://www.ccyg888.com/App/Login/phone?user_id=1003","friend_url":[{"url":"http://www.ccyg888.com/public/uploads/20180518/b4330509dd5fb5dc8afb0ccd62bc835f.png"},{"url":"http://www.ccyg888.com/public/uploads/20180418/0e264104f9ebaf63519d47db7fab2582.png"}]}
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
         * me_url : http://www.ccyg888.com/App/Login/phone?user_id=1003
         * friend_url : [{"url":"http://www.ccyg888.com/public/uploads/20180518/b4330509dd5fb5dc8afb0ccd62bc835f.png"},{"url":"http://www.ccyg888.com/public/uploads/20180418/0e264104f9ebaf63519d47db7fab2582.png"}]
         */

        private String me_url;


        private List<FriendUrlBean> friend_url;

        public String getMe_url() {
            return me_url;
        }

        public void setMe_url(String me_url) {
            this.me_url = me_url;
        }

        public List<FriendUrlBean> getFriend_url() {
            return friend_url;
        }

        public void setFriend_url(List<FriendUrlBean> friend_url) {
            this.friend_url = friend_url;
        }

        public static class FriendUrlBean {
            public boolean isSel() {
                return sel;
            }

            public void setSel(boolean sel) {
                this.sel = sel;
            }

            /**
             * url : http://www.ccyg888.com/public/uploads/20180518/b4330509dd5fb5dc8afb0ccd62bc835f.png
             */

             boolean sel;



            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
