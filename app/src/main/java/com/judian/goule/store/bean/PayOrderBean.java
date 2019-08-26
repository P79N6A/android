package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/12/28.
 */

public class PayOrderBean {

    /**
     * errcode : 200
     * errmsg : 支付宝支付成功
     * result : {"sign":"alipay_sdk=alipay-sdk-php-20161101&app_id=2016100802057773&biz_content=%7B%22body%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9D%E8%B4%AD%E4%B9%B0%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A%22%E6%B5%8B%E8%AF%951%22%2C%22out_trade_no%22%3A%22151445544717096831273%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A900%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fceshi.niuos.cn%2FApp%2FPay%2Freturnurl&sign_type=RSA2&timestamp=2017-12-28+18%3A04%3A09&version=1.0&sign=iHtezBdoi7SbuU44aNx9Gb8WEMUwls0z5hEQ41wBqilhu%2B8RGLQ2SFb5AY486yq2xdy9h9JFec4gmDO1300vQOC9sfoWyTTY7ZDi23OIiKKegvkc%2FmDsrekiyAPhcUIcYchwEBAjiHZoIXOKiqrFdw4fhzbK9suaQFmvdYIme5XGYVTtkTw0LAv6Gjoy7aP6%2BsaONvdsrmYIJN6DA99OyEd0UUJ7sCuZN79b1xYYnnwWPk%2B5CsfI1v672L0KVcJgWTG9Q1qE92tfz7vqfHlrcPUQNHYnF%2B56G%2BGgEdHWKEcQ8Ga%2FoLnPY3ieXNqcD9i%2FYcxMIA%2FPBzvRVNEH0vg13g%3D%3D"}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getErrcode() {
        return code;
    }

    public void setErrcode(int errcode) {
        this.code = errcode;
    }

    public String getErrmsg() {
        return msg;
    }

    public void setErrmsg(String errmsg) {
        this.msg = errmsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * sign : alipay_sdk=alipay-sdk-php-20161101&app_id=2016100802057773&biz_content=%7B%22body%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9D%E8%B4%AD%E4%B9%B0%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A%22%E6%B5%8B%E8%AF%951%22%2C%22out_trade_no%22%3A%22151445544717096831273%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A900%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fceshi.niuos.cn%2FApp%2FPay%2Freturnurl&sign_type=RSA2&timestamp=2017-12-28+18%3A04%3A09&version=1.0&sign=iHtezBdoi7SbuU44aNx9Gb8WEMUwls0z5hEQ41wBqilhu%2B8RGLQ2SFb5AY486yq2xdy9h9JFec4gmDO1300vQOC9sfoWyTTY7ZDi23OIiKKegvkc%2FmDsrekiyAPhcUIcYchwEBAjiHZoIXOKiqrFdw4fhzbK9suaQFmvdYIme5XGYVTtkTw0LAv6Gjoy7aP6%2BsaONvdsrmYIJN6DA99OyEd0UUJ7sCuZN79b1xYYnnwWPk%2B5CsfI1v672L0KVcJgWTG9Q1qE92tfz7vqfHlrcPUQNHYnF%2B56G%2BGgEdHWKEcQ8Ga%2FoLnPY3ieXNqcD9i%2FYcxMIA%2FPBzvRVNEH0vg13g%3D%3D
         */

        private String sign;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
