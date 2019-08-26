package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class DHBannerBean {


    /**
     * code : 200
     * msg : 获取轮播图成功
     * result : [{"id":"9","name":"积分商城轮播图","banner":"http://118.190.118.226/Uploads/banner/2017-11-13/5a08f175486df.jpg","url":"111","status":"1","sort":"11","type":"1","add_time":"2017-11-10 11:00:22"}]
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
         * id : 9
         * name : 积分商城轮播图
         * banner : http://118.190.118.226/Uploads/banner/2017-11-13/5a08f175486df.jpg
         * url : 111
         * status : 1
         * sort : 11
         * type : 1
         * add_time : 2017-11-10 11:00:22
         */

        private String id;
        private String name;
        private String banner;
        private String url;
        private String status;
        private String sort;
        private String type;
        private String add_time;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        private String goods_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
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
    }
}
