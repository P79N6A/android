package com.judian.goule.store.bean;

/**
 * Created by Administrator on 2017/11/7.
 */

public class ImageBean {

  private   int  iocn;

    public ImageBean(int iocn, String txt) {
        this.iocn = iocn;
        this.txt = txt;
    }

    public int getIocn() {
        return iocn;
    }

    public void setIocn(int iocn) {
        this.iocn = iocn;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    private   String  txt;


}
