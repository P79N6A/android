package com.judian.goule.store.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccy.ccyui.bean.HomeCate;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.view.SelfDialog;
import com.google.android.flexbox.FlexboxLayout;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.activity.OrderActivity;
import com.judian.goule.store.activity.SearchActivity;
import com.judian.goule.store.activity.ShareActivity;
import com.judian.goule.store.activity.TKLWebActivity;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.bean.TKLBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.view.PushPopupwindow;
import com.judian.goule.store.views.BaseView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */

public class TestData {

    /**
     * 动态创建TextView
     *
     * @param book
     * @return
     */
    public static TextView createNewFlexItemTextView(Context context, final CateBean.ResultBean book, final FlexLintener lintener) {
        String name = book.getCategory_name();

        int num = name.indexOf("/");
        if (num != -1) {
            name = name.substring(0, num);
        }

        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setText(name);
        textView.setTextSize(14);
        textView.setTextColor(context.getResources().getColor(R.color.dark_grey));
        textView.setBackgroundResource(R.drawable.item_s);
        textView.setTag(book.getId());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lintener.flexOnClick(book.getCategory_name());
            }
        });
        int padding = 6;
        int paddingLeftAndRight = 6;
        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = 20;
        int marginTop = 20;
        layoutParams.setMargins(margin, marginTop, margin, marginTop);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    /**
     * 动态创建TextView
     *
     * @param book
     * @return
     */
    public static TextView createNewFlexItemTextView1(Context context, final CateBean.ResultBean book, final FlexLintener lintener) {
        String name = book.getCategory_name();

        int num = name.indexOf("/");
        if (num != -1) {
            name = name.substring(0, num);
        }

        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setText(name);
        textView.setTextSize(14);
        textView.setTextColor(context.getResources().getColor(R.color.searchcolor));
        textView.setBackgroundResource(R.drawable.item_s);
        textView.setTag(book.getId());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lintener.flexOnClick(book.getCategory_name());
            }
        });
        int padding = 6;
        int paddingLeftAndRight = 6;
        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = 10;
        int marginTop = 10;
        layoutParams.setMargins(margin, marginTop, margin, marginTop);
        textView.setLayoutParams(layoutParams);
        return textView;
    }


    /**
     * 动态创建TextView
     *
     * @param
     * @return
     */
    public static TextView createNewFlexItemTextView(Context context, final String name, final FlexLintener lintener) {


        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setText(name);
        textView.setTextSize(14);
        textView.setTextColor(context.getResources().getColor(R.color.dark_grey));
        textView.setBackgroundResource(R.drawable.item_s);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lintener.flexOnClick(name);
            }
        });
        int padding = 6;
        int paddingLeftAndRight = 8;
        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = 6;
        int marginTop = 8;
        layoutParams.setMargins(margin, marginTop, margin, 0);
        textView.setLayoutParams(layoutParams);
        return textView;
    }


    public static List<HomeCate> msg() {
        List<HomeCate> list = new ArrayList<>();
        list.add(new HomeCate(R.mipmap.m1, "系统通知", 0));
//         list.add(new HomeCate(R.mipmap.assistant, "我的消息","专属权益、积分提醒等在这里查看哦~" ,2));
        return list;
    }


    public interface FlexLintener {

        void flexOnClick(String txt);

    }


    public static void dialogGoods(final Activity activity, final String numId, final String goodId) {

        final SelfDialog dialog = new SelfDialog(activity, SelfDialog.LOGIN);

        dialog.setYesOnclickListener(new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                activity.startActivityForResult(new Intent(activity, LoginActivity.class), 16);
                dialog.dismiss();
            }
        });

        dialog.setNoOnclickListener(new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();


            }
        });
        dialog.show();
    }

    public static void dialogShaer(final Activity activity, final String numId, final String goodId) {

        final SelfDialog dialog = new SelfDialog(activity, SelfDialog.LOGIN);

        dialog.setYesOnclickListener(new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                activity.startActivityForResult(new Intent(activity, LoginActivity.class), 16);
                dialog.dismiss();
            }
        });

        dialog.setNoOnclickListener(new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
                ShareActivity.openMain(activity, numId);
            }
        });
        dialog.show();
    }

    public static void dialogUrl(final Activity context, final String goodId, final String url, final String title, final String numid) {

        final SelfDialog dialog = new SelfDialog(context, SelfDialog.LOGIN);

        dialog.setYesOnclickListener(new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
//                context.startActivityForResult(new Intent(context,LoginActivity.class),16);
                dialog.dismiss();
            }
        });

        dialog.setNoOnclickListener(new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
//                AliWebActivity.openXQ(context,url, 6,title,numid);

            }
        });
        dialog.show();
    }


    public static void setLevel(String level, TextView tv, ImageView iv) {
        switch (level) {
            case "1":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.member);
                break;
            case "2":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.member);
                break;
            case "3":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.member);
                break;
            case "4":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.member);
                break;
            case "5":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.member);
                break;
            case "6":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.member);
                break;
            case "7":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.member);
                break;


        }


    }


    public static void setLevel1(String level, TextView tv, ImageView iv) {
        switch (level) {
            case "1":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.wuxin_icon);
                break;
            case "2":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.wuxin_icon);
                break;
            case "3":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.wuxin_icon);
                break;
            case "4":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.wuxin_icon);
                break;
            case "5":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.wuxin_icon);
                break;
            case "6":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.wuxin_icon);
                break;
            case "7":
                tv.setText("普通会员");
                iv.setImageResource(R.mipmap.wuxin_icon);
                break;


        }


    }

    //获取到复制的文本
    public static String getClipboardText(Activity activity) {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        String text = "";
        try {
            if (clipboard != null && clipboard.hasText()) {
                CharSequence tmpText = clipboard.getText();
                clipboard.setText(tmpText);
                if (tmpText != null && tmpText.length() > 0) {
                    text = tmpText.toString().trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            text = "";
        }
        return text;
    }

    //设置复制文本
    public static void setClipboardText(Activity activity, String text) {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
        ClipData clipData = ClipData.newPlainText(null, text);
        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
    }

    //清除粘贴板数据
    public static void deleteClipboarbText(Activity activity) {
        ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText(null, ""));
        if (clipboardManager.hasPrimaryClip()) {
            clipboardManager.getPrimaryClip().getItemAt(0).getText();
        }
    }

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }


    }


    public static void taoKouLing(final Activity activity, String key) {
        String centnet = key;
        int m = key.indexOf("¥");
        int k = -1, j = -1;
        if (m != -1) {
            key = key.substring(m + 1, key.length());
            int n = key.indexOf("¥");
            if (n == -1) return;
            key = key.substring(0, n);
            Log.i("tiancao", "taokouLing111" + key);
            final int a = centnet.indexOf("【");
            if (a != -1) {
                centnet = centnet.substring(a + 1, centnet.length());
                int b = centnet.indexOf("】");
                if (b != -1) {
                    centnet = centnet.substring(0, b);
                }
            }

            final String finalCentnet = centnet;
            new CdataPresenter(activity).getTaokou(key, centnet, new BaseView<TKLBean>() {
                @Override
                public void result(TKLBean bean) {
                    if (bean.getCode() == 200) {

                        if (bean.getResult().getType().equals("0")) {
                            TKLWebActivity.openSurl(activity, bean.getResult().getUrl());
                        } else {
                            PushPopupwindow pushPopupwindow = new PushPopupwindow(activity, bean.getResult().getResult());
                            pushPopupwindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                        }

                    } else {
                        Toast.makeText(activity, bean.getMsg(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void error() {

                }
            });


        } else {
            k = key.indexOf("《");
            if (k != -1) {
                key = key.substring(k + 1, key.length());
                int n = key.indexOf("《");
                if (n == -1) return;
                key = key.substring(0, n);
                Log.i("tiancao", "taokouLing222" + key);
                int a = centnet.indexOf("【");
                if (a != -1) {
                    centnet = centnet.substring(a + 1, centnet.length());
                    int b = centnet.indexOf("】");
                    if (b != -1) {
                        centnet = centnet.substring(0, b);
                    }

                }


                new CdataPresenter(activity).getTaokou(key, centnet, new BaseView<TKLBean>() {
                    @Override
                    public void result(TKLBean bean) {
                        if (bean.getCode() == 200) {
                            if (bean.getResult().getType().equals("0")) {
                                TKLWebActivity.openSurl(activity, bean.getResult().getUrl());
                            } else {
                                PushPopupwindow pushPopupwindow = new PushPopupwindow(activity, bean.getResult().getResult());
                                pushPopupwindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                            }


                        } else {
                            Toast.makeText(activity, bean.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void error() {

                    }
                });

            } else {

                j = key.indexOf("￥");
                if (j != -1) {
                    key = key.substring(j + 1, key.length());
                    int n = key.indexOf("￥");
                    if (n == -1) return;
                    key = key.substring(0, n);
                    Log.i("tiancao", "taokouLing333" + key);
                    int a = centnet.indexOf("【");
                    if (a != -1) {
                        centnet = centnet.substring(a + 1, centnet.length());
                        int b = centnet.indexOf("】");
                        if (b != -1) {
                            centnet = centnet.substring(0, b);
                        }

                    }
                    new CdataPresenter(activity).getTaokou(key, centnet, new BaseView<TKLBean>() {
                        @Override
                        public void result(TKLBean bean) {
                            if (bean.getCode() == 200) {
                                if (bean.getResult().getType().equals("0")) {
                                    TKLWebActivity.openSurl(activity, bean.getResult().getUrl());
                                } else {
                                    PushPopupwindow pushPopupwindow = new PushPopupwindow(activity, bean.getResult().getResult());
                                    pushPopupwindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                                }

                            } else {
                                Toast.makeText(activity, bean.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void error() {

                        }
                    });

                } else {

                    j = key.indexOf("€");
                    if (j != -1) {
                        key = key.substring(j + 1, key.length());
                        int n = key.indexOf("€");
                        if (n == -1) return;
                        key = key.substring(0, n);
                        Log.i("tiancao", "taokouLing444" + key);
                        int a = centnet.indexOf("【");
                        if (a != -1) {
                            centnet = centnet.substring(a + 1, centnet.length());
                            int b = centnet.indexOf("】");
                            if (b != -1) {
                                centnet = centnet.substring(0, b);
                            }

                        }
//                        SearchActivity.openMain(activity, "€" + key + "€", HttpAPI.BD_SEARCH);
                        SearchActivity.openMain(activity, "", HttpAPI.BD_SEARCH);
                        //通过淘口令获取接口
                       /* new CdataPresenter(activity).getTaokou(key, centnet, new BaseView<TKLBean>() {
                            @Override
                            public void result(TKLBean bean) {
                                if (bean.getCode() == 200) {
                                    if (bean.getResult().getType().equals("0")) {
                                        TKLWebActivity.openSurl(activity, bean.getResult().getUrl());
                                    } else {
                                        PushPopupwindow pushPopupwindow = new PushPopupwindow(activity, bean.getResult().getResult());
                                        pushPopupwindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                                    }

                                } else {
                                    Toast.makeText(activity, bean.getMsg(), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void error() {
                            }
                        });*/


                    }


                }


            }
            if (m == -1 && k == -1 && j == -1) {
                //业务：  如果复制的是订单号，着要跳转到订单界面
                if (StringUtils.isNumeric(key)) {
                    OrderActivity.openMain(activity, key, 0);
                } else {
                    SearchActivity.openMain(activity, key, HttpAPI.BD_SEARCH);
                }

            }


        }


    }


}



