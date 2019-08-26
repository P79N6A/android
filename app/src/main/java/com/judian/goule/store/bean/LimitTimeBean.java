package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/7.
 */

public class LimitTimeBean {


    /**
     * code : 200
     * msg : 获取成功
     * data : [{"time":"2018-01-03 00:00","intro":"已开始","is_start":1,"new_time":"00:00","id":1},{"time":"2018-01-03 10:00","intro":"已开始","is_start":1,"new_time":"10:00","id":2},{"time":"2018-01-03 15:00","intro":"已开始","is_start":1,"new_time":"15:00","id":3},{"time":"2018-01-03 20:00","intro":"已开始","is_start":1,"new_time":"20:00","id":4},{"time":"2018-01-04 00:00","intro":"已开始","is_start":1,"new_time":"00:00","id":5},{"time":"2018-01-04 10:00","intro":"疯抢中","is_start":1,"new_time":"10:00","id":6},{"time":"2018-01-04 15:00","intro":"即将开始","is_start":0,"new_time":"15:00","id":7},{"time":"2018-01-04 20:00","intro":"即将开始","is_start":0,"new_time":"20:00","id":8},{"time":"2018-01-05 00:00","intro":"明日开抢","is_start":0,"new_time":"00:00","id":9},{"time":"2018-01-05 10:00","intro":"明日开抢","is_start":0,"new_time":"10:00","id":10},{"time":"2018-01-05 15:00","intro":"明日开抢","is_start":0,"new_time":"15:00","id":11},{"time":"2018-01-05 20:00","intro":"明日开抢","is_start":0,"new_time":"20:00","id":12}]
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

    public List<ResultBean> getData() {
        return result;
    }

    public void setData(List<ResultBean> data) {
        this.result = data;
    }

    public static class ResultBean {
        /**
         * time : 2018-01-03 00:00
         * intro : 已开始
         * is_start : 1
         * new_time : 00:00
         * id : 1
         */

        private String time;
        private String intro;
        private int is_start;
        private String new_time;
        private int id;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getIs_start() {
            return is_start;
        }

        public void setIs_start(int is_start) {
            this.is_start = is_start;
        }

        public String getNew_time() {
            return new_time;
        }

        public void setNew_time(String new_time) {
            this.new_time = new_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
