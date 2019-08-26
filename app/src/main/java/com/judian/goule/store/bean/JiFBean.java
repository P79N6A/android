package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */

public class JiFBean {


    /**
     * code : 200
     * msg : 获取成功
     * result : {"able_score":"1","data":[{"title":"1.晒单拿积分","pic":"http://47.104.108.30/Uploads/banner/2017-11-20/5a1281d65c1f1.png"},{"title":"2.晒单被点赞","pic":"http://47.104.108.30/Uploads/banner/2017-11-20/5a1281d65c1f1.png"},{"title":"3.晒单被加精","pic":"http://47.104.108.30/Uploads/banner/2017-11-20/5a1281d65c1f1.png"}]}
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
         * able_score : 1
         * data : [{"title":"1.晒单拿积分","pic":"http://47.104.108.30/Uploads/banner/2017-11-20/5a1281d65c1f1.png"},{"title":"2.晒单被点赞","pic":"http://47.104.108.30/Uploads/banner/2017-11-20/5a1281d65c1f1.png"},{"title":"3.晒单被加精","pic":"http://47.104.108.30/Uploads/banner/2017-11-20/5a1281d65c1f1.png"}]
         */

        private String able_score;
        private List<DataBean> data;

        public String getAble_score() {
            return able_score;
        }

        public void setAble_score(String able_score) {
            this.able_score = able_score;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * title : 1.晒单拿积分
             * pic : http://47.104.108.30/Uploads/banner/2017-11-20/5a1281d65c1f1.png
             */

            private String title;
            private String pic;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
