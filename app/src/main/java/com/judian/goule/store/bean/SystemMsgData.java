package com.judian.goule.store.bean;

import java.io.Serializable;
import java.util.List;

public class SystemMsgData implements Serializable{
    /**
     * code : 200
     * msg : 请求消息成功
     * result : [{"msg_title":"系统消息测试3","msg_content":"3333333","send_time":"2018-08-09 11:24:06"},{"msg_title":"系统消息测试2","msg_content":"222222222","send_time":"2018-08-09 11:09:26"},{"msg_title":"测试系统消息1","msg_content":"11111111111","send_time":"2018-08-09 11:02:20"},{"msg_title":"7777","msg_content":"77777","send_time":"2018-08-09 10:59:58"},{"msg_title":"66","msg_content":"7777","send_time":"2018-08-09 10:57:06"},{"msg_title":"4444","msg_content":"5555","send_time":"2018-08-09 10:53:45"},{"msg_title":"3333","msg_content":"33333334444","send_time":"2018-08-09 10:47:17"},{"msg_title":"121212121212","msg_content":"1212121","send_time":"2018-08-09 10:38:39"},{"msg_title":"维护公告","msg_content":"平台将于2018年5月5日 进行  进行  \n省内车小家伙就是的风景地方还是\n四六级速度快放假","send_time":"2018-08-09 10:38:00"},{"msg_title":"ces","msg_content":"cessssss","send_time":"2018-08-09 10:26:27"}]
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

    public static class ResultBean implements Serializable{
        /**
         * msg_title : 系统消息测试3
         * msg_content : 3333333
         * send_time : 2018-08-09 11:24:06
         */

        private String msg_title;
        private String msg_content;
        private String send_time;

        public String getMsg_title() {
            return msg_title;
        }

        public void setMsg_title(String msg_title) {
            this.msg_title = msg_title;
        }

        public String getMsg_content() {
            return msg_content;
        }

        public void setMsg_content(String msg_content) {
            this.msg_content = msg_content;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "msg_title='" + msg_title + '\'' +
                    ", msg_content='" + msg_content + '\'' +
                    ", send_time='" + send_time + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SystemMsgData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
