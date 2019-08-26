package com.judian.goule.store.db.liteorm;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

/**
 * Description :
 * <p>
 * LiteORM 数据库框架，适用于无需加密的SQLite数据库需求
 * 介绍页：https://github.com/litesuits/android-lite-orm
 * 帮助类
 * <p>
 * Date : 16/6/8
 *
 * @author zhuwx QQ:24926483
 */
public class LiteOrmHelper {

    private static final String DB_NAME = "goule_store";

    private static final boolean DEBUGGABLE = true; // 是否输出log

    private static volatile LiteOrm sLiteOrm;


    public static LiteOrm getSingleInstance(Context context) {
        LiteOrm liteOrm = sLiteOrm;
        if (sLiteOrm == null) {
            synchronized (LiteOrmHelper.class) {
                liteOrm = sLiteOrm;
                if (liteOrm == null) {
                    liteOrm = LiteOrm.newCascadeInstance(context, DB_NAME);
                    liteOrm.setDebugged(DEBUGGABLE);
                    sLiteOrm = liteOrm;
                }
            }
        }
        return liteOrm;
    }

    public static void closeDB() {
        if (sLiteOrm != null) {
            sLiteOrm.close();
        }
    }


}
