package com.judian.goule.store.bean;


import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table("OptimizationData")
public class OptimizationData implements Serializable{
    /**
     * code : 200
     * msg : 获取成功
     * result : [{"ad_id":"75","name":"优选栏目","type_id":"20","pic":"/public/uploads/20180714/dc3db3579abef6c2e599fe7568525a2a.png","url":"http://www.taobao.com","addtime":"1531547054","sort":"362156","open":"1","content":"","type":"0","style":"0"},{"ad_id":"74","name":"聚电科技","type_id":"20","pic":"/public/uploads/20180714/eecd2e3526eeeab01571cd915d46a081.jpg","url":"https://www.baidu.com","addtime":"1531546976","sort":"362156","open":"1","content":"","type":"0","style":"0"}]
     */
    @PrimaryKey(AssignType.BY_MYSELF)
    private int code;
    private String msg;
    @Mapping(Relation.ManyToMany)
    private ArrayList<YouXuanData> result;

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

    public ArrayList<YouXuanData> getResult() {
        return result;
    }

    public void setResult(ArrayList<YouXuanData> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OptimizationData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
