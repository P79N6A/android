package com.judian.goule.store.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/11/20.
 */

public class MyImgBean {


    /**
     * code : 200
     * msg : 成功
     * result : {"slide_id":"69","slide_name":"我的banner","banner":"/Uploads/banner/2017-11-20/5a126590d17df.png","slide_sort":"1","url":"","num_iid":"","cate_id":"0","type":"5","status":"1","style":"3","copy":"","explain":"","interface":"http://118.190.118.226/app.php/Home/Help/kefu "}
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
         * slide_id : 69
         * slide_name : 我的banner
         * banner : /Uploads/banner/2017-11-20/5a126590d17df.png
         * slide_sort : 1
         * url :
         * num_iid :
         * cate_id : 0
         * type : 5
         * status : 1
         * style : 3
         * copy :
         * explain :
         * interface : http://118.190.118.226/app.php/Home/Help/kefu
         */

        private String slide_id;
        private String slide_name;
        private String banner;
        private String slide_sort;
        private String url;
        private String num_iid;
        private String cate_id;
        private String type;
        private String status;
        private String style;
        private String copy;
        private String explain;
        @SerializedName("interface")
        private String interfaceX;

        public String getSlide_id() {
            return slide_id;
        }

        public void setSlide_id(String slide_id) {
            this.slide_id = slide_id;
        }

        public String getSlide_name() {
            return slide_name;
        }

        public void setSlide_name(String slide_name) {
            this.slide_name = slide_name;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getSlide_sort() {
            return slide_sort;
        }

        public void setSlide_sort(String slide_sort) {
            this.slide_sort = slide_sort;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getCopy() {
            return copy;
        }

        public void setCopy(String copy) {
            this.copy = copy;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getInterfaceX() {
            return interfaceX;
        }

        public void setInterfaceX(String interfaceX) {
            this.interfaceX = interfaceX;
        }
    }
}
