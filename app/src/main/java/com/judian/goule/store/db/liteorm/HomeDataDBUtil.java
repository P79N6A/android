package com.judian.goule.store.db.liteorm;

import android.content.Context;

import com.judian.goule.store.bean.BannerBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.bean.PulickData;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;

/**
 * 首页数据的保存
 */

public class HomeDataDBUtil {

    /**
     * 首页轮播
     */
    public static BannerBean getBanner(Context context) {
        ArrayList<BannerBean> al = LiteOrmHelper.getSingleInstance(context).query(BannerBean.class);
        if (al != null && al.size() > 0) {
            return al.get(0);
        }
        return new BannerBean();
    }

    public static void saveBanner(final Context context, final BannerBean info) {
        LiteOrm singleInstance = LiteOrmHelper.getSingleInstance(context);
        singleInstance.delete(BannerBean.class);
        singleInstance.save(info);
    }

    public static void updateBanner(Context context, BannerBean info) {
        LiteOrmHelper.getSingleInstance(context).update(info);
    }

    public static void deleteBanner(Context context) {
        LiteOrmHelper.getSingleInstance(context).delete(BannerBean.class);
    }

    /**
     * 首页分类
     */
    public static MsBean getClassify(Context context, String field, String[] value) {
        ArrayList<MsBean> al = LiteOrmHelper.getSingleInstance(context).query(new QueryBuilder(MsBean.class).where(field + "=?", value));
        if (al != null && al.size() > 0) {
            return al.get(0);
        }
        return new MsBean();
    }

    public static void saveClassify(final Context context, final MsBean info) {
        LiteOrm singleInstance = LiteOrmHelper.getSingleInstance(context);
        singleInstance.delete(MsBean.class);
        singleInstance.save(info);
    }

    public static void updateClassify(Context context, MsBean info) {
        LiteOrmHelper.getSingleInstance(context).update(info);
    }

    public static void deleteClassify(Context context) {
        LiteOrmHelper.getSingleInstance(context).delete(MsBean.class);
    }
}
