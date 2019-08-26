package com.judian.goule.store.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/8/1.
 */

public class BaseBean  {

    /**
     * code : 200
     * msg : 验证成功
     * result : {"phone":"18545590350"}
     */

    private int code;
    private String msg;
    private ResultBean result;
    public String getDown_link() {
        return down_link;
    }

    public void setDown_link(String down_link) {
        this.down_link = down_link;
    }

    private String down_link;
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
         * phone : 18545590350
         */
        private String invite_code;
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private String password;
        private String pic_url;

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }
        private String phone;

        public String getSlide_name() {
            return slide_name;
        }

        public void setSlide_name(String slide_name) {
            this.slide_name = slide_name;
        }

        private String sign;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }





        private String slide_name;

        public String getAgent_phone() {
            return agent_phone;
        }

        public void setAgent_phone(String agent_phone) {
            this.agent_phone = agent_phone;
        }

        private String agent_phone;

        public String getWeixin_name() {
            return weixin_name;
        }

        public void setWeixin_name(String weixin_name) {
            this.weixin_name = weixin_name;
        }

        private String weixin_name;

        public String getData() {
            return data;
        }
        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        private String qq;

        public void setData(String data) {
            this.data = data;
        }
        private String data;
        private String pic;
        private String title;
        public String getIs_agent() {
            return is_agent;
        }

        public void setIs_agent(String is_agent) {
            this.is_agent = is_agent;
        }

        private String is_agent;
        public String getAble_score() {
            return able_score;
        }

        public void setAble_score(String able_score) {
            this.able_score = able_score;
        }

        private String able_score;

        public String getFriend_url() {
            return friend_url;
        }

        public void setFriend_url(String friend_url) {
            this.friend_url = friend_url;
        }

        private String friend_url;
        private String num_iid;

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

            public String getIf_show() {
                if (if_show==null)return "0";
                else
            return if_show;
        }

        public void setIf_show(String if_show) {
            this.if_show = if_show;
        }

        private String if_show;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;
        public String getTb_nickname() {
            return tb_nickname;
        }
        public void setTb_nickname(String tb_nickname) {
            this.tb_nickname = tb_nickname;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        private String tb_nickname;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        private String nick_name;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        public String getFanli_money() {
            return fanli_money;
        }

        public void setFanli_money(String fanli_money) {
            this.fanli_money = fanli_money;
        }

        private String fanli_money;



        private String live_link;

        public String getLive_link() {
            return live_link;
        }

        public void setLive_link(String live_link) {
            this.live_link = live_link;
        }

        public String getSearch_link() {
            return search_link;
        }

        public void setSearch_link(String search_link) {
            this.search_link = search_link;
        }

        private String search_link;



        private String ali_account;
        private String name;
        @SerializedName("interface")
        private String interfaceX;



        public String getInterfaceX() {
            return interfaceX;
        }

        public void setInterfaceX(String interfaceX) {
            this.interfaceX = interfaceX;
        }

        public String getZan_num() {
            return zan_num;
        }

        public void setZan_num(String zan_num) {
            this.zan_num = zan_num;
        }

        private String zan_num;
        private String gold;
        private String num;
        private String total;

        public String getIs_ali() {
            return is_ali;
        }

        public void setIs_ali(String is_ali) {
            this.is_ali = is_ali;
        }

        private String is_ali;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String img_url;
        private String link;
        private String type;

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        private String comment_num;

        public String getMe_url() {
            return me_url;
        }

        public void setMe_url(String me_url) {
            this.me_url = me_url;
        }

        private String me_url;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getToday_total() {
            return today_total;
        }

        public void setToday_total(String today_total) {
            this.today_total = today_total;
        }

        public String getToday_num() {
            return today_num;
        }

        public void setToday_num(String today_num) {
            this.today_num = today_num;
        }

        private String today_total;
        private String today_num;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getId() {
            return user_id;
        }

        public void setId(String id) {
            this.user_id = id;
        }

        private String token;
        private String user_id;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        private String money;

        public String getMin_money() {
            return min_money;
        }

        public void setMin_money(String min_money) {
            this.min_money = min_money;
        }

        public String getAcc_gold() {
            return acc_gold;
        }

        public void setAcc_gold(String acc_gold) {
            this.acc_gold = acc_gold;
        }

        private String min_money;


        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        private String avatar;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String code;
        private String today;

        public String getToday() {
            return today;
        }

        public void setToday(String today) {
            this.today = today;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        private String balance;
        private String acc_gold;

        public String getAli_account() {
            return ali_account;
        }

        public void setAli_account(String ali_account) {
            this.ali_account = ali_account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getGold_total() {
            return gold_total;
        }

        public void setGold_total(String gold_total) {
            this.gold_total = gold_total;
        }

        private String gold_total;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "invite_code='" + invite_code + '\'' +
                    ", password='" + password + '\'' +
                    ", pic_url='" + pic_url + '\'' +
                    ", phone='" + phone + '\'' +
                    ", sign='" + sign + '\'' +
                    ", slide_name='" + slide_name + '\'' +
                    ", agent_phone='" + agent_phone + '\'' +
                    ", weixin_name='" + weixin_name + '\'' +
                    ", qq='" + qq + '\'' +
                    ", data='" + data + '\'' +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", is_agent='" + is_agent + '\'' +
                    ", able_score='" + able_score + '\'' +
                    ", friend_url='" + friend_url + '\'' +
                    ", num_iid='" + num_iid + '\'' +
                    ", if_show='" + if_show + '\'' +
                    ", url='" + url + '\'' +
                    ", tb_nickname='" + tb_nickname + '\'' +
                    ", nick_name='" + nick_name + '\'' +
                    ", fanli_money='" + fanli_money + '\'' +
                    ", live_link='" + live_link + '\'' +
                    ", search_link='" + search_link + '\'' +
                    ", ali_account='" + ali_account + '\'' +
                    ", name='" + name + '\'' +
                    ", interfaceX='" + interfaceX + '\'' +
                    ", zan_num='" + zan_num + '\'' +
                    ", gold='" + gold + '\'' +
                    ", num='" + num + '\'' +
                    ", total='" + total + '\'' +
                    ", is_ali='" + is_ali + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", link='" + link + '\'' +
                    ", type='" + type + '\'' +
                    ", comment_num='" + comment_num + '\'' +
                    ", me_url='" + me_url + '\'' +
                    ", today_total='" + today_total + '\'' +
                    ", today_num='" + today_num + '\'' +
                    ", token='" + token + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", money='" + money + '\'' +
                    ", min_money='" + min_money + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", code='" + code + '\'' +
                    ", today='" + today + '\'' +
                    ", balance='" + balance + '\'' +
                    ", acc_gold='" + acc_gold + '\'' +
                    ", gold_total='" + gold_total + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                ", down_link='" + down_link + '\'' +
                '}';
    }
}
