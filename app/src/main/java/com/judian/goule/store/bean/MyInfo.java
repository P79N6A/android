package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/10/28.
 */

public class MyInfo {

    /**
     * code : 200
     * msg : 获取成功
     * result : {"id":"9","phone":"15066663318","password":"$2y$10$yWqX41tVF/O7CCgv2hA0muQ0EHlVqtOk1SpfIPqGG61dA0ehVt1ba","level":"1","nick_name":"","pid":"144574392","avatar":"","sex":"1","gold":"0.00","prediction_total":"0.00","gold_total":"0.00","effective_gold_total":"0.00","score":"0","able_score":"0","province":"","city":"","district":"","address":"","ali_account":"","name":"","referrer":"0","unionid":"","me_url":"","emw_pic_url":"","token":"ca5ef0d41f217cd8cab05c6e25cdfad34373cdbdc56165b3beb91d84caf89642","status":"1","add_time":"2017-10-27 13:38:24"}
     */

    private String code;
    private String msg;
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
         * id : 9
         * phone : 15066663318
         * password : $2y$10$yWqX41tVF/O7CCgv2hA0muQ0EHlVqtOk1SpfIPqGG61dA0ehVt1ba
         * level : 1
         * nick_name :
         * pid : 144574392
         * avatar :
         * sex : 1
         * gold : 0.00
         * prediction_total : 0.00
         * gold_total : 0.00
         * effective_gold_total : 0.00
         * score : 0
         * able_score : 0
         * province :
         * city :
         * district :
         * address :
         * ali_account :
         * name :
         * referrer : 0
         * unionid :
         * me_url :
         * emw_pic_url :
         * token : ca5ef0d41f217cd8cab05c6e25cdfad34373cdbdc56165b3beb91d84caf89642
         * status : 1
         * add_time : 2017-10-27 13:38:24
         */

        private String id;
        private String phone;
        private String password;
        private String level;
        private String nick_name;
        private String pid;
        private String avatar;
        private String sex;
        private String gold;
        private String prediction_total;
        private String gold_total;
        private String effective_gold_total;
        private String score;
        private String able_score;
        private String province;
        private String city;
        private String district;
        private String address;
        private String ali_account;
        private String name;
        private String referrer;
        private String unionid;
        private String me_url;
        private String emw_pic_url;
        private String token;
        private String status;
        private String add_time;

        private String order_total;

        public String getOrder_total() {
            return order_total;
        }

        public void setOrder_total(String order_total) {
            this.order_total = order_total;
        }

        public String getPrediction_order() {
            return prediction_order;
        }

        public void setPrediction_order(String prediction_order) {
            this.prediction_order = prediction_order;
        }
        private String invite_code;
        public String getIs_agent() {
            return is_agent;
        }

        public void setIs_agent(String is_agent) {
            this.is_agent = is_agent;
        }

        private String is_agent;
        private String pass;
        private String prediction_order;
        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }
        private String team_income;
        public String getIncome() {
            return income;
        }
        public String getTeam_income() {
            return team_income;
        }

        public void setTeam_income(String team_income) {
            this.team_income = team_income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }


        private String income;
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getPrediction_total() {
            return prediction_total;
        }

        public void setPrediction_total(String prediction_total) {
            this.prediction_total = prediction_total;
        }

        public String getGold_total() {
            return gold_total;
        }

        public void setGold_total(String gold_total) {
            this.gold_total = gold_total;
        }

        public String getEffective_gold_total() {
            return effective_gold_total;
        }

        public void setEffective_gold_total(String effective_gold_total) {
            this.effective_gold_total = effective_gold_total;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getAble_score() {
            return able_score;
        }

        public void setAble_score(String able_score) {
            this.able_score = able_score;
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

        public String getReferrer() {
            return referrer;
        }

        public void setReferrer(String referrer) {
            this.referrer = referrer;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
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
    }
}
