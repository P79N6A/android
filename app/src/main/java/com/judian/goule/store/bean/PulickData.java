package com.judian.goule.store.bean;


import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Administrator on 2017/10/28.
 * 公共保存的数据
 */
@Table("PulickData")
public class PulickData {

    @PrimaryKey(AssignType.BY_MYSELF)
    private String id = "100";
    private String isUpData = "1";//是否需要升级   1代表不升级  0 需要升级

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsUpData() {
        return isUpData;
    }

    public void setIsUpData(String isUpData) {
        this.isUpData = isUpData;
    }

    @Override
    public String toString() {
        return "PulickData{" +
                "id='" + id + '\'' +
                ", isUpData='" + isUpData + '\'' +
                '}';
    }
}
