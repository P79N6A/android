package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class TeamMxBean{


    /**
     * code : 200
     * msg : 获取成功
     * result : {"re_num":1,"two_num":0,"data":[{"add_time":"0000.00.00","nickname":"lala","income":0}]}
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
         * re_num : 1
         * two_num : 0
         * data : [{"add_time":"0000.00.00","nickname":"lala","income":0}]
         */

        private String re_num;
        private String two_num;
        private List<DataBean> data;

        public String getRe_num() {
            return re_num;
        }

        public void setRe_num(String re_num) {
            this.re_num = re_num;
        }

        public String getTwo_num() {
            return two_num;
        }

        public void setTwo_num(String two_num) {
            this.two_num = two_num;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * add_time : 0000.00.00
             * nickname : lala
             * income : 0
             */

            private String add_time;
            private String nickname;
            private String up_user_name;
            private String total;

            public String getRe_nickname() {
                return re_nickname;
            }

            public void setRe_nickname(String re_nickname) {
                this.re_nickname = re_nickname;
            }

            private String re_nickname;
            private String income;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public String getUp_user_name() {
                return up_user_name;
            }

            public void setUp_user_name(String up_user_name) {
                this.up_user_name = up_user_name;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }
        }
    }
}
