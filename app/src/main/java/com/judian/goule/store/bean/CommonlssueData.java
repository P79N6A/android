package com.judian.goule.store.bean;

import java.io.Serializable;
import java.util.List;

public class CommonlssueData implements Serializable {
    /**
     * code : 200
     * msg : 获取常见问题列表成功
     * result : [{"id":"1","title":"购了商城是做什么的？","content":"购了商城，一款\u201c网购省钱，分享赚钱\u201d的APP！京东/淘宝购物必备，正品保障，领券购物立省！随时随地，分享好友，赚取佣金！分享越多，奖励越丰厚！/n省到就是赚到！大学生、宝妈、上班族购物性价比之选，海量精品，优质优价！/n要赚钱就「购了」！分享就能赚钱，成功推荐的新用户，他在购了商城的每一笔交易你都可以永久获得佣金奖励！"},{"id":"2","title":"牛八客","content":"牛八客"},{"id":"3","title":"牛八客","content":"牛八客"}]
     */

    private String code;
    private String msg;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * id : 1
         * title : 购了商城是做什么的？
         * content : 购了商城，一款“网购省钱，分享赚钱”的APP！京东/淘宝购物必备，正品保障，领券购物立省！随时随地，分享好友，赚取佣金！分享越多，奖励越丰厚！/n省到就是赚到！大学生、宝妈、上班族购物性价比之选，海量精品，优质优价！/n要赚钱就「购了」！分享就能赚钱，成功推荐的新用户，他在购了商城的每一笔交易你都可以永久获得佣金奖励！
         */

        private String id;
        private String title;
        private String content;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CommonlssueData{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
