package com.judian.goule.store.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 检查 手机是否安装有该应用
 */

public class JianCheYYUtils {

    public static boolean checkPackage(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager
                    .GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

    }
}
