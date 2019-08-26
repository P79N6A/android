package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/28.
 */

public class CateBean {


    /**
     * code : 200
     * msg : 查询成功
     * result : [{"id":"2","category_name":"男装","pid":"1","open_type":"1","show_type":"1","show_sort":"0","promotion":"0","promotion_img":null,"cate_level":"2","pic_url":null},{"id":"3","category_name":"女装/女士精品","pid":"1","open_type":"1","show_type":"1","show_sort":"0","promotion":"0","promotion_img":null,"cate_level":"2","pic_url":null},{"id":"4","category_name":"女士内衣/男士内衣/家居服","pid":"1","open_type":"1","show_type":"1","show_sort":"0","promotion":"0","promotion_img":null,"cate_level":"2","pic_url":null},{"id":"91","category_name":"电子词典/电纸书/文化用品","pid":"89","open_type":"1","show_type":"1","show_sort":"0","promotion":"0","promotion_img":null,"cate_level":"2","pic_url":null},{"id":"92","category_name":"商业/办公家具","pid":"89","open_type":"1","show_type":"1","show_sort":"0","promotion":"0","promotion_img":null,"cate_level":"2","pic_url":null},{"id":"93","category_name":"书籍/杂志/报纸","pid":"89","open_type":"1","show_type":"1","show_sort":"0","promotion":"0","promotion_img":null,"cate_level":"2","pic_url":null}]
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
         * category_name : 男装
         * pid : 1
         * open_type : 1
         * show_type : 1
         * show_sort : 0
         * promotion : 0
         * promotion_img : null
         * cate_level : 2
         * pic_url : null
         */

        private String id;
        private String category_name;
        private String pid;
        private String open_type;
        private String show_type;
        private String show_sort;
        private String promotion;
        private Object promotion_img;
        private String cate_level;

        public ResultBean() {

        }

        public ResultBean(String id, String category_name) {
            this.id = id;
            this.category_name = category_name;
        }

        public boolean isSel() {
            return sel;
        }

        public void setSel(boolean sel) {
            this.sel = sel;
        }

        private boolean sel;
        private Object pic_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getOpen_type() {
            return open_type;
        }

        public void setOpen_type(String open_type) {
            this.open_type = open_type;
        }

        public String getShow_type() {
            return show_type;
        }

        public void setShow_type(String show_type) {
            this.show_type = show_type;
        }

        public String getShow_sort() {
            return show_sort;
        }

        public void setShow_sort(String show_sort) {
            this.show_sort = show_sort;
        }

        public String getPromotion() {
            return promotion;
        }

        public void setPromotion(String promotion) {
            this.promotion = promotion;
        }

        public Object getPromotion_img() {
            return promotion_img;
        }

        public void setPromotion_img(Object promotion_img) {
            this.promotion_img = promotion_img;
        }

        public String getCate_level() {
            return cate_level;
        }

        public void setCate_level(String cate_level) {
            this.cate_level = cate_level;
        }

        public Object getPic_url() {
            return pic_url;
        }

        public void setPic_url(Object pic_url) {
            this.pic_url = pic_url;
        }
    }
}
