package com.judian.goule.store.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class GradeBean {

    /**
     * code : 200
     * msg : 获取成功
     * result : {"user_level":[{"level_id":1,"level_name":"普通会员","sort":1,"bomlimit":0,"toplimit":500,"proportion":50,"money":"0.00"},{"level_id":6,"level_name":"超级VIP合伙人","sort":2,"bomlimit":60000,"toplimit":99999,"proportion":150,"money":"2000.00"},{"level_id":7,"level_name":"店长","sort":3,"bomlimit":100000,"toplimit":100000,"proportion":250,"money":"3000.00"}],"user_info":{"id":282,"gold":"858.9","account_gold":"958.9","able_score":"58.00","all_able_score":"58.00","gold_total":"0.00","fans":0,"fans_order_num":0,"phone":"18189124921","password":"$2y$10$BLGQKjPPCiFDgRw1CBWQdu8zSJ3lWB8kTAb6U.gFWAVLlv9/8ue1C","level":6,"nick_name":"只是凡人8","pid":"282558687","avatar":"http://47.104.193.113/Uploads/userhead/2018-03-22/5ab3090bb4d81.jpg","sex":1,"province":null,"city":null,"district":null,"address":"","ali_account":"18189124921","name":"何永豹","unionid":"ow4sExLOoHhapSAvkkPBx7OwEwp4","tb_openid":"AAG50h1iAF7irBzSompW7KW_","tb_nickname":"只是凡人8","me_url":"http://47.104.193.113/App/Login/phone?user_id=282","emw_pic_url":"","token":"41d970a3dca9e2cd5fddc5ac09d180422fd97cecd730badec77a3ffbbee2dbaf","status":"1","add_time":"2018-02-07 15:39:02","is_agent":2,"red_if":1,"active_time":"2018-05-09 11:20:23","alipay":null,"realname":null,"wx_openid":null,"ready_rebate":0,"p_1":0,"p_2":0,"is_lock":1,"balance_s":"858.9","integral_s":"58.00","all_balance_s":"958.9","all_integral_s":"58.00","wait_gold":0,"etc":0,"order_num":0,"grade_name":"超级VIP合伙人"},"banner":""}
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
         * user_level : [{"level_id":1,"level_name":"普通会员","sort":1,"bomlimit":0,"toplimit":500,"proportion":50,"money":"0.00"},{"level_id":6,"level_name":"超级VIP合伙人","sort":2,"bomlimit":60000,"toplimit":99999,"proportion":150,"money":"2000.00"},{"level_id":7,"level_name":"店长","sort":3,"bomlimit":100000,"toplimit":100000,"proportion":250,"money":"3000.00"}]
         * user_info : {"id":282,"gold":"858.9","account_gold":"958.9","able_score":"58.00","all_able_score":"58.00","gold_total":"0.00","fans":0,"fans_order_num":0,"phone":"18189124921","password":"$2y$10$BLGQKjPPCiFDgRw1CBWQdu8zSJ3lWB8kTAb6U.gFWAVLlv9/8ue1C","level":6,"nick_name":"只是凡人8","pid":"282558687","avatar":"http://47.104.193.113/Uploads/userhead/2018-03-22/5ab3090bb4d81.jpg","sex":1,"province":null,"city":null,"district":null,"address":"","ali_account":"18189124921","name":"何永豹","unionid":"ow4sExLOoHhapSAvkkPBx7OwEwp4","tb_openid":"AAG50h1iAF7irBzSompW7KW_","tb_nickname":"只是凡人8","me_url":"http://47.104.193.113/App/Login/phone?user_id=282","emw_pic_url":"","token":"41d970a3dca9e2cd5fddc5ac09d180422fd97cecd730badec77a3ffbbee2dbaf","status":"1","add_time":"2018-02-07 15:39:02","is_agent":2,"red_if":1,"active_time":"2018-05-09 11:20:23","alipay":null,"realname":null,"wx_openid":null,"ready_rebate":0,"p_1":0,"p_2":0,"is_lock":1,"balance_s":"858.9","integral_s":"58.00","all_balance_s":"958.9","all_integral_s":"58.00","wait_gold":0,"etc":0,"order_num":0,"grade_name":"超级VIP合伙人"}
         * banner :
         */

        private UserInfoBean user_info;
        private String banner;

        public String getFooter() {
            return footer;
        }

        public void setFooter(String footer) {
            this.footer = footer;
        }

        private String footer;


        private List<UserLevelBean> user_level;

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public String getBanner() {
            return banner;
        }


        public void setBanner(String banner) {
            this.banner = banner;
        }

        public List<UserLevelBean> getUser_level() {
            return user_level;
        }

        public void setUser_level(List<UserLevelBean> user_level) {
            this.user_level = user_level;
        }

        public static class UserInfoBean {
            /**
             * id : 282
             * gold : 858.9
             * account_gold : 958.9
             * able_score : 58.00
             * all_able_score : 58.00
             * gold_total : 0.00
             * fans : 0
             * fans_order_num : 0
             * phone : 18189124921
             * password : $2y$10$BLGQKjPPCiFDgRw1CBWQdu8zSJ3lWB8kTAb6U.gFWAVLlv9/8ue1C
             * level : 6
             * nick_name : 只是凡人8
             * pid : 282558687
             * avatar : http://47.104.193.113/Uploads/userhead/2018-03-22/5ab3090bb4d81.jpg
             * sex : 1
             * province : null
             * city : null
             * district : null
             * address :
             * ali_account : 18189124921
             * name : 何永豹
             * unionid : ow4sExLOoHhapSAvkkPBx7OwEwp4
             * tb_openid : AAG50h1iAF7irBzSompW7KW_
             * tb_nickname : 只是凡人8
             * me_url : http://47.104.193.113/App/Login/phone?user_id=282
             * emw_pic_url :
             * token : 41d970a3dca9e2cd5fddc5ac09d180422fd97cecd730badec77a3ffbbee2dbaf
             * status : 1
             * add_time : 2018-02-07 15:39:02
             * is_agent : 2
             * red_if : 1
             * active_time : 2018-05-09 11:20:23
             * alipay : null
             * realname : null
             * wx_openid : null
             * ready_rebate : 0
             * p_1 : 0
             * p_2 : 0
             * is_lock : 1
             * balance_s : 858.9
             * integral_s : 58.00
             * all_balance_s : 958.9
             * all_integral_s : 58.00
             * wait_gold : 0
             * etc : 0
             * order_num : 0
             * grade_name : 超级VIP合伙人
             */

            private String id;
            private String gold;
            private String account_gold;
            private String able_score;
            private String all_able_score;
            private String gold_total;
            private int fans;
            private int fans_order_num;
            private String phone;
            private String password;
            private int level;
            private String nick_name;
            private String pid;
            private String avatar;
            private int sex;
            private Object province;
            private Object city;
            private Object district;
            private String address;
            private String ali_account;
            private String name;
            private String unionid;
            private String tb_openid;
            private String tb_nickname;
            private String me_url;
            private String emw_pic_url;
            private String token;
            private String status;
            private String add_time;
            private int is_agent;
            private int red_if;
            private String active_time;
            private Object alipay;
            private Object realname;
            private Object wx_openid;
            private String ready_rebate;
            private int p_1;
            private int p_2;
            private int is_lock;
            private String balance_s;
            private String integral_s;
            private String all_balance_s;
            private String all_integral_s;
            private String wait_gold;
            private int etc;
            private int order_num;
            private String grade_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGold() {
                return gold;
            }

            public void setGold(String gold) {
                this.gold = gold;
            }

            public String getAccount_gold() {
                return account_gold;
            }

            public void setAccount_gold(String account_gold) {
                this.account_gold = account_gold;
            }

            public String getAble_score() {
                return able_score;
            }

            public void setAble_score(String able_score) {
                this.able_score = able_score;
            }

            public String getAll_able_score() {
                return all_able_score;
            }

            public void setAll_able_score(String all_able_score) {
                this.all_able_score = all_able_score;
            }

            public String getGold_total() {
                return gold_total;
            }

            public void setGold_total(String gold_total) {
                this.gold_total = gold_total;
            }

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public int getFans_order_num() {
                return fans_order_num;
            }

            public void setFans_order_num(int fans_order_num) {
                this.fans_order_num = fans_order_num;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getDistrict() {
                return district;
            }

            public void setDistrict(Object district) {
                this.district = district;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

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

            public String getUnionid() {
                return unionid;
            }

            public void setUnionid(String unionid) {
                this.unionid = unionid;
            }

            public String getTb_openid() {
                return tb_openid;
            }

            public void setTb_openid(String tb_openid) {
                this.tb_openid = tb_openid;
            }

            public String getTb_nickname() {
                return tb_nickname;
            }

            public void setTb_nickname(String tb_nickname) {
                this.tb_nickname = tb_nickname;
            }

            public String getMe_url() {
                return me_url;
            }

            public void setMe_url(String me_url) {
                this.me_url = me_url;
            }

            public String getEmw_pic_url() {
                return emw_pic_url;
            }

            public void setEmw_pic_url(String emw_pic_url) {
                this.emw_pic_url = emw_pic_url;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public int getIs_agent() {
                return is_agent;
            }

            public void setIs_agent(int is_agent) {
                this.is_agent = is_agent;
            }

            public int getRed_if() {
                return red_if;
            }

            public void setRed_if(int red_if) {
                this.red_if = red_if;
            }

            public String getActive_time() {
                return active_time;
            }

            public void setActive_time(String active_time) {
                this.active_time = active_time;
            }

            public Object getAlipay() {
                return alipay;
            }

            public void setAlipay(Object alipay) {
                this.alipay = alipay;
            }

            public Object getRealname() {
                return realname;
            }

            public void setRealname(Object realname) {
                this.realname = realname;
            }

            public Object getWx_openid() {
                return wx_openid;
            }

            public void setWx_openid(Object wx_openid) {
                this.wx_openid = wx_openid;
            }

            public String getReady_rebate() {
                return ready_rebate;
            }

            public void setReady_rebate(String ready_rebate) {
                this.ready_rebate = ready_rebate;
            }

            public int getP_1() {
                return p_1;
            }

            public void setP_1(int p_1) {
                this.p_1 = p_1;
            }

            public int getP_2() {
                return p_2;
            }

            public void setP_2(int p_2) {
                this.p_2 = p_2;
            }

            public int getIs_lock() {
                return is_lock;
            }

            public void setIs_lock(int is_lock) {
                this.is_lock = is_lock;
            }

            public String getBalance_s() {
                return balance_s;
            }

            public void setBalance_s(String balance_s) {
                this.balance_s = balance_s;
            }

            public String getIntegral_s() {
                return integral_s;
            }

            public void setIntegral_s(String integral_s) {
                this.integral_s = integral_s;
            }

            public String getAll_balance_s() {
                return all_balance_s;
            }

            public void setAll_balance_s(String all_balance_s) {
                this.all_balance_s = all_balance_s;
            }

            public String getAll_integral_s() {
                return all_integral_s;
            }

            public void setAll_integral_s(String all_integral_s) {
                this.all_integral_s = all_integral_s;
            }

            public String getWait_gold() {
                return wait_gold;
            }

            public void setWait_gold(String wait_gold) {
                this.wait_gold = wait_gold;
            }

            public int getEtc() {
                return etc;
            }

            public void setEtc(int etc) {
                this.etc = etc;
            }

            public int getOrder_num() {
                return order_num;
            }

            public void setOrder_num(int order_num) {
                this.order_num = order_num;
            }

            public String getGrade_name() {
                return grade_name;
            }

            public void setGrade_name(String grade_name) {
                this.grade_name = grade_name;
            }
        }

        public static class UserLevelBean {
            /**
             * level_id : 1
             * level_name : 普通会员
             * sort : 1
             * bomlimit : 0
             * toplimit : 500
             * proportion : 50
             * money : 0.00
             */

            private int level_id;
            private String level_name;
            private String sort;
            private String bomlimit;
            private String toplimit;
            private int proportion;
            private String money;
            private String yxzf;//有效直接分时
            private String jjfs;//间接粉丝
            private String ztdd;//本月直推订单
            private String yjzh;//佣金总和

            public int getLevel_id() {
                return level_id;
            }

            public void setLevel_id(int level_id) {
                this.level_id = level_id;
            }

            public String getLevel_name() {
                return level_name;
            }

            public void setLevel_name(String level_name) {
                this.level_name = level_name;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getBomlimit() {
                return bomlimit;
            }

            public void setBomlimit(String bomlimit) {
                this.bomlimit = bomlimit;
            }

            public String getToplimit() {
                return toplimit;
            }

            public void setToplimit(String toplimit) {
                this.toplimit = toplimit;
            }

            public int getProportion() {
                return proportion;
            }

            public void setProportion(int proportion) {
                this.proportion = proportion;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getYxzf() {
                return yxzf;
            }

            public void setYxzf(String yxzf) {
                this.yxzf = yxzf;
            }

            public String getJjfs() {
                return jjfs;
            }

            public void setJjfs(String jjfs) {
                this.jjfs = jjfs;
            }

            public String getZtdd() {
                return ztdd;
            }

            public void setZtdd(String ztdd) {
                this.ztdd = ztdd;
            }

            public String getYjzh() {
                return yjzh;
            }

            public void setYjzh(String yjzh) {
                this.yjzh = yjzh;
            }

            @Override
            public String toString() {
                return "UserLevelBean{" +
                        "level_id=" + level_id +
                        ", level_name='" + level_name + '\'' +
                        ", sort='" + sort + '\'' +
                        ", bomlimit='" + bomlimit + '\'' +
                        ", toplimit='" + toplimit + '\'' +
                        ", proportion=" + proportion +
                        ", money='" + money + '\'' +
                        ", yxzf='" + yxzf + '\'' +
                        ", jjfs='" + jjfs + '\'' +
                        ", ztdd='" + ztdd + '\'' +
                        ", yjzh='" + yjzh + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "user_info=" + user_info +
                    ", banner='" + banner + '\'' +
                    ", footer='" + footer + '\'' +
                    ", user_level=" + user_level +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GradeBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
