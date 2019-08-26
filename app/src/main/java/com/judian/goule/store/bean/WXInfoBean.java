package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/5/26.
 */

public class WXInfoBean {

    /**
     * access_token : YXGj81MNNZZ1asZYB6qWlHUnsqbbv8aPG-cW8n4MI1wwXZ_UdtXU8RYsvKWl8hqJsiHPK7GMO0fJseMF0Lqv22cYcWwrg2K-dq5Fv4K0xRI
     * expires_in : 7200
     * refresh_token : 6Xgsjhrk4E86OiAou1qPF0spvjKyy8LYaA4JstlJ9LYGoYO0xYFIQkQGu9Ik5JBMRHZYJ_vicKcFrgdwb_2EiaTmhAPhLWOV17Ujr83Guqs
     * openid : oSUZv1HH1O_dVkTeKBbhcaFnvU4U
     * scope : snsapi_userinfo
     * unionid : oeunywkkaqT6yKsgpBdzFV6kKBOU
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
