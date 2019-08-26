package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */

public class LiveInfoBean {


    /**
     * code : 200
     * msg : 成功
     * result : {"num":"2000","avatars":["http://118.190.118.226/Uploads/userhead/2017-11-13/5a08f7f9e4fd7.png","http://wx.qlogo.cn/mmopen/vi_32/ibia6CABcpaJon4TicErUJUSoQ6OAJM5PeciaLXIcePia0ETnr8tLSqAkMCY21LKT22S2QHdYSkG1ap2rVKQbz0GMibQ/0","http://118.190.118.226/Uploads/userhead/2017-11-14/5a0ad085340f5.jpg","http://wx.qlogo.cn/mmopen/vi_32/wAnXUhgHSEYg326KARa1k01y5mKhnxk03AM4sTwwXNshyC2gqsQZyE35YWbJlAcfrrtJH0iaZPiaHuqHKNSDMvLg/0","http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIrg3ZKwyfUShxnuUypkibwEiaUT9pGLGMlsDrYuJQOFGKWKGQ4NiasJpWIkGC1kxsYf1sVy1KD8ic9tg/0","http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJBQUOnKgVfW2Q3Ua39ziaxxWncuxrEebO9FDI0WoaSsKGGRibENic50vxK9rLsKBNOIvheAsJ1dg2Qw/0"]}
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
         * num : 2000
         * avatars : ["http://118.190.118.226/Uploads/userhead/2017-11-13/5a08f7f9e4fd7.png","http://wx.qlogo.cn/mmopen/vi_32/ibia6CABcpaJon4TicErUJUSoQ6OAJM5PeciaLXIcePia0ETnr8tLSqAkMCY21LKT22S2QHdYSkG1ap2rVKQbz0GMibQ/0","http://118.190.118.226/Uploads/userhead/2017-11-14/5a0ad085340f5.jpg","http://wx.qlogo.cn/mmopen/vi_32/wAnXUhgHSEYg326KARa1k01y5mKhnxk03AM4sTwwXNshyC2gqsQZyE35YWbJlAcfrrtJH0iaZPiaHuqHKNSDMvLg/0","http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIrg3ZKwyfUShxnuUypkibwEiaUT9pGLGMlsDrYuJQOFGKWKGQ4NiasJpWIkGC1kxsYf1sVy1KD8ic9tg/0","http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJBQUOnKgVfW2Q3Ua39ziaxxWncuxrEebO9FDI0WoaSsKGGRibENic50vxK9rLsKBNOIvheAsJ1dg2Qw/0"]
         */

        private String num;
        private List<String> avatars;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public List<String> getAvatars() {
            return avatars;
        }

        public void setAvatars(List<String> avatars) {
            this.avatars = avatars;
        }
    }
}
