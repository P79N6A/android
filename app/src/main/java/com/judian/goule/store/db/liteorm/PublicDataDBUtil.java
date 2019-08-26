package com.judian.goule.store.db.liteorm;

import android.content.Context;

import com.judian.goule.store.bean.PulickData;
import com.judian.goule.store.bean.UserInfo;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;

/**
 * 公共数据的保存
 */

public class PublicDataDBUtil {
    public static PulickData get(Context context) {
        ArrayList<PulickData> al = LiteOrmHelper.getSingleInstance(context).query(PulickData.class);
        if (al != null && al.size() > 0) {
            return al.get(0);
        }

        return new PulickData();
    }

    public static void save(final Context context, final PulickData info) {

        LiteOrm singleInstance = LiteOrmHelper.getSingleInstance(context);
        singleInstance.delete(PulickData.class);
        singleInstance.save(info);

    }

    public static void update(Context context, PulickData info) {
        LiteOrmHelper.getSingleInstance(context).update(info);
    }

    public static void delete(Context context) {
        LiteOrmHelper.getSingleInstance(context).delete(PulickData.class);
    }

}
