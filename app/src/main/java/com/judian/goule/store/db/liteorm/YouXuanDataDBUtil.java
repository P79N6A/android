package com.judian.goule.store.db.liteorm;

import android.content.Context;

import com.judian.goule.store.bean.BannerBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.bean.OptimizationData;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;

/**
 * 优选数据的保存
 */

public class YouXuanDataDBUtil {

    /**
     * 首页轮播
     */
    public static OptimizationData getYouXuanData(Context context) {
        ArrayList<OptimizationData> al = LiteOrmHelper.getSingleInstance(context).query(OptimizationData.class);
        if (al != null && al.size() > 0) {
            return al.get(0);
        }
        return new OptimizationData();
    }

    public static void saveYouXuanData(final Context context, final OptimizationData info) {
        LiteOrm singleInstance = LiteOrmHelper.getSingleInstance(context);
        singleInstance.delete(OptimizationData.class);
        singleInstance.save(info);
    }

    public static void updateYouXuanData(Context context, OptimizationData info) {
        LiteOrmHelper.getSingleInstance(context).update(info);
    }

    public static void deleteYouXuanData(Context context) {
        LiteOrmHelper.getSingleInstance(context).delete(OptimizationData.class);
    }

}
