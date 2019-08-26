package com.judian.goule.store.bean;

import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/28.
 */
@Table("UserInfo")
public class UserInfo implements Serializable {

    /**
     * code : 200
     * msg : 获取信息成功
     * result : {"id":"18","gold":0,"account_gold":0,"able_score":0,"all_able_score":"0.00","gold_total":"0.00","fans":"0","fans_order_num":"0","phone":"18776410384","password":"$2y$10$tUa8wYcET1BRwWEHwIG0.eoH/LkECBa98B3MQnoqzuQB/u1Yq4Awy","level":"1","nick_name":"18776410384","pid":"","avatar":"http://192.168.1.241:80/public/uploads/userhead/20180710/97b579dbf3105f79f715f58e2104337e.jpg","sex":"1","province":"","city":"","district":"","address":"","ali_account":"","name":"","unionid":"","tb_openid":"AAG7U72mAGc8Gr6UWES4I4Kj","tb_nickname":"tb007616002","me_url":"http://192.168.1.241/App/Login/phone?user_id=18","emw_pic_url":"","token":"adb1e14698ee6b45c4bf0c865a94dece036e199e0c528b7fb90532030a64b204","status":"1","add_time":"2018-07-02 07:35:19","is_agent":"0","red_if":"0","active_time":"2018-07-10 01:58:28","alipay":null,"realname":null,"wx_openid":null,"ready_rebate":0.09,"p_1":"0","p_2":"0","is_lock":"1","yq_code":"1003416","balance_s":0,"integral_s":0,"all_balance_s":0,"all_integral_s":0,"wait_gold":0.09,"etc":"0","order_num":"0","grade_name":"VIP0"}
     */
    @PrimaryKey(AssignType.BY_MYSELF)
    private String code;
    private String msg;
    @Mapping(Relation.OneToOne)
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 18
         * gold : 0
         * account_gold : 0
         * able_score : 0
         * all_able_score : 0.00
         * gold_total : 0.00
         * fans : 0
         * fans_order_num : 0
         * phone : 18776410384
         * password : $2y$10$tUa8wYcET1BRwWEHwIG0.eoH/LkECBa98B3MQnoqzuQB/u1Yq4Awy
         * level : 1
         * nick_name : 18776410384
         * pid :
         * avatar : http://192.168.1.241:80/public/uploads/userhead/20180710/97b579dbf3105f79f715f58e2104337e.jpg
         * sex : 1
         * province :
         * city :
         * district :
         * address :
         * ali_account :
         * name :
         * unionid :
         * tb_openid : AAG7U72mAGc8Gr6UWES4I4Kj
         * tb_nickname : tb007616002
         * me_url : http://192.168.1.241/App/Login/phone?user_id=18
         * emw_pic_url :
         * token : adb1e14698ee6b45c4bf0c865a94dece036e199e0c528b7fb90532030a64b204
         * status : 1
         * add_time : 2018-07-02 07:35:19
         * is_agent : 0
         * red_if : 0
         * active_time : 2018-07-10 01:58:28
         * alipay : null
         * realname : null
         * wx_openid : null
         * ready_rebate : 0.09
         * p_1 : 0
         * p_2 : 0
         * is_lock : 1
         * yq_code : 1003416
         * yqr_yqm : 1003416
         * yqr_name : muze
         * balance_s : 0
         * integral_s : 0
         * all_balance_s : 0
         * all_integral_s : 0
         * wait_gold : 0.09
         * etc : 0
         * order_num : 0
         * grade_name : VIP0
         */

        private String id;
        private double gold;
        private double account_gold;
        private double able_score;
        private String all_able_score;
        private String gold_total;
        private String fans;
        private String fans_order_num;
        private String phone;
        private String password;
        private String level;
        private String nick_name;
        private String pid;
        private String avatar;
        private String sex;
        private String province;
        private String city;
        private String district;
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
        private String is_agent;
        private String red_if;
        private String active_time;
        private Object alipay;
        private Object realname;
        private Object wx_openid;
        private double ready_rebate;
        private String p_1;
        private String p_2;
        private String is_lock;
        private String yq_code;
        private double balance_s;
        private double integral_s;
        private double all_balance_s;
        private double all_integral_s;
        private double wait_gold;
        private String etc;
        private String order_num;
        private String grade_name;
        private String user_order_num;
        private String yqr_yqm;
        private String yqr_name;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public double getAccount_gold() {
            return account_gold;
        }

        public void setAccount_gold(int account_gold) {
            this.account_gold = account_gold;
        }

        public double getAble_score() {
            return able_score;
        }

        public void setAble_score(int able_score) {
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

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getFans_order_num() {
            return fans_order_num;
        }

        public void setFans_order_num(String fans_order_num) {
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
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

        public String getIs_agent() {
            return is_agent;
        }

        public void setIs_agent(String is_agent) {
            this.is_agent = is_agent;
        }

        public String getRed_if() {
            return red_if;
        }

        public void setRed_if(String red_if) {
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

        public double getReady_rebate() {
            return ready_rebate;
        }

        public void setReady_rebate(double ready_rebate) {
            this.ready_rebate = ready_rebate;
        }

        public String getP_1() {
            return p_1;
        }

        public void setP_1(String p_1) {
            this.p_1 = p_1;
        }

        public String getP_2() {
            return p_2;
        }

        public void setP_2(String p_2) {
            this.p_2 = p_2;
        }

        public String getIs_lock() {
            return is_lock;
        }

        public void setIs_lock(String is_lock) {
            this.is_lock = is_lock;
        }

        public String getYq_code() {
            return yq_code;
        }

        public void setYq_code(String yq_code) {
            this.yq_code = yq_code;
        }

        public double getBalance_s() {
            return balance_s;
        }

        public void setBalance_s(int balance_s) {
            this.balance_s = balance_s;
        }

        public double getIntegral_s() {
            return integral_s;
        }

        public void setIntegral_s(int integral_s) {
            this.integral_s = integral_s;
        }

        public double getAll_balance_s() {
            return all_balance_s;
        }

        public void setAll_balance_s(int all_balance_s) {
            this.all_balance_s = all_balance_s;
        }

        public double getAll_integral_s() {
            return all_integral_s;
        }

        public void setAll_integral_s(int all_integral_s) {
            this.all_integral_s = all_integral_s;
        }

        public double getWait_gold() {
            return wait_gold;
        }

        public void setWait_gold(double wait_gold) {
            this.wait_gold = wait_gold;
        }

        public String getEtc() {
            return etc;
        }

        public void setEtc(String etc) {
            this.etc = etc;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getGrade_name() {
            return grade_name;
        }

        public void setGrade_name(String grade_name) {
            this.grade_name = grade_name;
        }

        public String getUser_order_num() {
            return user_order_num;
        }

        public void setUser_order_num(String user_order_num) {
            this.user_order_num = user_order_num;
        }

        public String getYqr_yqm() {
            return yqr_yqm;
        }

        public void setYqr_yqm(String yqr_yqm) {
            this.yqr_yqm = yqr_yqm;
        }

        public String getYqr_name() {
            return yqr_name;
        }

        public void setYqr_name(String yqr_name) {
            this.yqr_name = yqr_name;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", gold=" + gold +
                    ", account_gold=" + account_gold +
                    ", able_score=" + able_score +
                    ", all_able_score='" + all_able_score + '\'' +
                    ", gold_total='" + gold_total + '\'' +
                    ", fans='" + fans + '\'' +
                    ", fans_order_num='" + fans_order_num + '\'' +
                    ", phone='" + phone + '\'' +
                    ", password='" + password + '\'' +
                    ", level='" + level + '\'' +
                    ", nick_name='" + nick_name + '\'' +
                    ", pid='" + pid + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", sex='" + sex + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    ", address='" + address + '\'' +
                    ", ali_account='" + ali_account + '\'' +
                    ", name='" + name + '\'' +
                    ", unionid='" + unionid + '\'' +
                    ", tb_openid='" + tb_openid + '\'' +
                    ", tb_nickname='" + tb_nickname + '\'' +
                    ", me_url='" + me_url + '\'' +
                    ", emw_pic_url='" + emw_pic_url + '\'' +
                    ", token='" + token + '\'' +
                    ", status='" + status + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", is_agent='" + is_agent + '\'' +
                    ", red_if='" + red_if + '\'' +
                    ", active_time='" + active_time + '\'' +
                    ", alipay=" + alipay +
                    ", realname=" + realname +
                    ", wx_openid=" + wx_openid +
                    ", ready_rebate=" + ready_rebate +
                    ", p_1='" + p_1 + '\'' +
                    ", p_2='" + p_2 + '\'' +
                    ", is_lock='" + is_lock + '\'' +
                    ", yq_code='" + yq_code + '\'' +
                    ", balance_s=" + balance_s +
                    ", integral_s=" + integral_s +
                    ", all_balance_s=" + all_balance_s +
                    ", all_integral_s=" + all_integral_s +
                    ", wait_gold=" + wait_gold +
                    ", etc='" + etc + '\'' +
                    ", order_num='" + order_num + '\'' +
                    ", grade_name='" + grade_name + '\'' +
                    ", user_order_num='" + user_order_num + '\'' +
                    ", yqr_yqm='" + yqr_yqm + '\'' +
                    ", yqr_name='" + yqr_name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
