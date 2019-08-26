package com.example.ccy.ccyui.util;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Constants {


    //微信
    //请同时修改  androidmanifest.xml里面，.PayActivityd里的属性<data android:scheme="wxb4ba3c02aa476ea1"/>为新设置的appid
    public static final String APP_ID = "wxdc9585088eafca22";
    public static final String APPSECRET = "d880738dc33172bfbd814f3faa4252bf";


    //    QQ
    public static final String QQAPPID = "1106985196";


    //商户号
    public static final String MCH_ID = "1480988852";


    //  API密钥，在商户平台设置
    public static final String API_KEY = "33d6590797f083e3e2b990d5978a465f";

    //音频文件存放目录
    public static final File AUDIO_DIR = new File(Environment.getExternalStorageDirectory(), "audio");
    public static final File IMAGE_DIR = new File(Environment.getExternalStorageDirectory(), "image");
    public static final File VIDEO_DIR = new File(Environment.getExternalStorageDirectory(), "video");


    /**
     * 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY
     * 微博
     */
    public static final String APP_KEY = "2196019427";

    /**
     * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     * 微博
     */
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    /**
     * WeiboSDKDemo 应用对应的权限，第三方开发者一般不需要这么多，可直接设置成空即可。
     * 详情请查看 Demo 中对应的注释。
     * 微博
     */
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";


}
