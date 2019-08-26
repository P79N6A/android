package com.judian.goule.store.utils.DialogUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.judian.goule.store.R;


/**
 * Created by chenweize on 2017/9/28.
 * 审核DIalog
 */

public class AuditDialog {

    private final AlertDialog dialog;
    private final int screenWidth;
    private DisimissListener disimiss;
    private Context context;
    private String message = "";
    private String tab = "审核中......";

    public AuditDialog(final Context context, DisimissListener disimiss, String message) {
        this.context = context;
        this.message = message;
        screenWidth = DisplayUtil.getScreenWidth(context);
        this.disimiss = disimiss;
        dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        setView();
    }

    public AuditDialog(final Context context, DisimissListener disimiss, String message, String tabs) {
        this.context = context;
        this.message = message;
        this.tab = tabs;
        screenWidth = DisplayUtil.getScreenWidth(context);
        this.disimiss = disimiss;
        dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        setView();
    }

    private void setView() {
        Window window = dialog.getWindow();
        View view = LayoutInflater.from(context).inflate(R.layout.audit_dialog, null, false);
        final TextView tvTime = (TextView) view.findViewById(R.id.tv_time);
        TextView tv_1 = (TextView) view.findViewById(R.id.tv_1);
        tv_1.setText(tab);
        TextView tv = (TextView) view.findViewById(R.id.tv_2);
        tv.setText(message);
        window.setContentView(view);
//        window.setWindowAnimations(R.style.dialoganimstyle);  //添加动画
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//设置不被虚拟键盘遮挡
        window.setBackgroundDrawableResource(R.color.transparent);//设置对话框以外的背景颜色

        //设置对话框内容填充整个窗口
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = screenWidth;
        window.setAttributes(lp);
        final CountDownTimer timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {
                tvTime.setText(l / 1000 + "s");
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
            }
        };
        timer.start();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                timer.cancel();
                disimiss.disimiss();
            }
        });
    }

    public void show() {
        dialog.show();
    }

    public void setCancle(boolean value) {
        dialog.setCancelable(value);
    }


}
