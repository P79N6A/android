package com.judian.goule.store.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

public class XuBean implements Serializable  {


    /**
     * code : 200
     * msg : 查询成功
     * result : [{"id":"10","title":"新手帮助","pic_url":"Public/app/img/2017-10-28/59f3e92d33213.png","href":"www.baidu.com","sort":"4","status":"1"},{"id":"2","title":"推广赚钱","pic_url":"Public/app/img/2017-10-28/59f3e8da96192.png","href":"https://s.click.taobao.com/6LqBFjw","sort":"03","status":"1"},{"id":"8","title":"秒杀榜单","pic_url":"Public/app/img/2017-10-28/59f3e903191dc.png","href":"3424324","sort":"02","status":"1"},{"id":"1","title":"9.9疯抢","pic_url":"Public/app/img/2017-10-28/59f3e8b597fb3.png","href":"https://s.click.taobao.com/HUeAFjw","sort":"0","status":"1"},{"id":"11","title":"小编推荐","pic_url":"Public/app/img/tuijian.png","href":"ww","sort":"0","status":"1"}]
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

    public static class ResultBean implements Serializable {
        /**
         * id : 10
         * title : 新手帮助
         * pic_url : Public/app/img/2017-10-28/59f3e92d33213.png
         * href : www.baidu.com
         * sort : 4
         * status : 1
         */

        private String id;
        private String title;
        private String pic_url;
        private String href;
        private String sort;
        private String status;

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        private  int  img;

        public ResultBean() {

        }

        public ResultBean(String id, String title, int img) {
            this.id = id;
            this.img = img;
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
