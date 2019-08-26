package com.judian.goule.store.db.liteorm;

import android.content.Context;
import com.judian.goule.store.bean.UserInfo;
import com.litesuits.orm.LiteOrm;
import java.util.ArrayList;

/**
 * Created by ChinaTeam on 2017/6/20.
 */

public class UserInfoDBUtil {
    public static UserInfo get(Context context) {
        ArrayList<UserInfo> al = LiteOrmHelper.getSingleInstance(context).query(UserInfo.class);
        if (al != null && al.size() > 0) {
            return al.get(0);
        }

        return new UserInfo();
    }

    public static void save(final Context context, final UserInfo info) {

        LiteOrm singleInstance = LiteOrmHelper.getSingleInstance(context);
        singleInstance.delete(UserInfo.class);
        singleInstance.save(info);

    }

    public static void update(Context context, UserInfo info) {
        LiteOrmHelper.getSingleInstance(context).update(info);
    }

    public static void delete(Context context) {
        LiteOrmHelper.getSingleInstance(context).delete(UserInfo.class);
    }

}
