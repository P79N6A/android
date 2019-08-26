package com.judian.goule.store.bean;


import java.io.Serializable;

public class MiandanInfoData implements Serializable {
    /**
     * code : 200
     * msg : 获取成功
     * result : {"activity_id":"6","activity_name":"七夕活动1","start_time":"2018-08-17 00:00:00","end_time":"2018-08-18 00:00:00","coupon_count":"1314","status":"1","piece_user_xinxi2":"","piece_user_xinxi3":"","type":"22","coupon_used":"0","coupon_surplus":"1314"}
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

    public static class ResultBean implements Serializable {
        /**
         * activity_id : 6
         * activity_name : 七夕活动1
         * start_time : 2018-08-17 00:00:00
         * end_time : 2018-08-18 00:00:00
         * coupon_count : 1314
         * status : 1
         * piece_user_xinxi2 :
         * piece_user_xinxi3 :
         * type : 22
         * coupon_used : 0
         * coupon_surplus : 1314
         */

        private String activity_id;
        private String activity_name;
        private String start_time;
        private String end_time;
        private String coupon_count;
        private String status;
        private String piece_user_xinxi2;
        private String piece_user_xinxi3;
        private String type;
        private String coupon_used;
        private String coupon_surplus;

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(String coupon_count) {
            this.coupon_count = coupon_count;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPiece_user_xinxi2() {
            return piece_user_xinxi2;
        }

        public void setPiece_user_xinxi2(String piece_user_xinxi2) {
            this.piece_user_xinxi2 = piece_user_xinxi2;
        }

        public String getPiece_user_xinxi3() {
            return piece_user_xinxi3;
        }

        public void setPiece_user_xinxi3(String piece_user_xinxi3) {
            this.piece_user_xinxi3 = piece_user_xinxi3;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCoupon_used() {
            return coupon_used;
        }

        public void setCoupon_used(String coupon_used) {
            this.coupon_used = coupon_used;
        }

        public String getCoupon_surplus() {
            return coupon_surplus;
        }

        public void setCoupon_surplus(String coupon_surplus) {
            this.coupon_surplus = coupon_surplus;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "activity_id='" + activity_id + '\'' +
                    ", activity_name='" + activity_name + '\'' +
                    ", start_time='" + start_time + '\'' +
                    ", end_time='" + end_time + '\'' +
                    ", coupon_count='" + coupon_count + '\'' +
                    ", status='" + status + '\'' +
                    ", piece_user_xinxi2='" + piece_user_xinxi2 + '\'' +
                    ", piece_user_xinxi3='" + piece_user_xinxi3 + '\'' +
                    ", type='" + type + '\'' +
                    ", coupon_used='" + coupon_used + '\'' +
                    ", coupon_surplus='" + coupon_surplus + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MiandanInfoData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
