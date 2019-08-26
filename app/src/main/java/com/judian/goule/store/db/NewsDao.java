package com.judian.goule.store.db;


import com.judian.goule.store.MyApplication;
import com.judian.goule.store.greendao.NewsBeanDao;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class NewsDao {
    /**
     * 添加数据 如果有重复则覆盖
     * @param user
     */
    public  static  void  insert(NewsBean user) {

        MyApplication.getDaoInstant().getNewsBeanDao().insertOrReplace(user);


    }
    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteShop(long id) {
        MyApplication.getDaoInstant().getNewsBeanDao().deleteByKey(id);
    }

    /**
     * 更新数据
     */
    public static void updateShop(NewsBean shop) {
        MyApplication.getDaoInstant().getNewsBeanDao().update(shop);
    }

    /**
     * 查询Type为1的所有数据
     *
     * @return
     */
    public static List<NewsBean> queryShop(String type) {
        return MyApplication.getDaoInstant().getNewsBeanDao().queryBuilder().where(NewsBeanDao.Properties.Type.eq(type)).list();

    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<NewsBean> queryAll() {
        return MyApplication.getDaoInstant().getNewsBeanDao().loadAll();
    }




}
