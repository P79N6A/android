package com.judian.goule.store.utils.DialogUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.judian.goule.store.R;


/**
 * Created by chenweize on 2017/8/16.
 * 提示对话框
 */

public class HintDialog {

    private Context context;
    private String title;
    private String content;
    private String sure;
    private TextView tv;
    private ProgressBar pb;

    public HintDialog(Context context, String title, String content, String sure) {
        this.context = context;
        this.title = title;
        this.content = content;
        this.sure = sure;
    }

    public void setDefaultView(final DisimissListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        View view = View.inflate(context, R.layout.hint_dialog, null);
        Window window = dialog.getWindow();
        window.setContentView(view);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//设置不被虚拟键盘遮挡
        window.setBackgroundDrawableResource(R.color.transparent);//设置对话框以外的背景颜色
        TextView Title = (TextView) view.findViewById(R.id.title);
        Title.setText(title);
        if (title.equals("警告")) {
            Title.setTextColor(context.getResources().getColor(R.color.red_text_light));
        } else {
            Title.setTextColor(context.getResources().getColor(R.color.home_blue));
        }
        TextView Content = (TextView) view.findViewById(R.id.content);
        Content.setText(content);
        TextView finish = (TextView) view.findViewById(R.id.finish);
        finish.setText(sure);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.disimiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                listener.disimiss();
            }
        });
    }

    public void setProgressDialog(final DisimissListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        View view = View.inflate(context, R.layout.dialog_up_apk, null);
        Window window = dialog.getWindow();
        window.setContentView(view);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//设置不被虚拟键盘遮挡
        window.setBackgroundDrawableResource(R.color.transparent);//设置对话框以外的背景颜色
        pb = (ProgressBar) view.findViewById(R.id.progress_bar);
        final TextView text_content = (TextView) view.findViewById(R.id.text_content);
        tv = (TextView) view.findViewById(R.id.tv);
        final TextView tvSure = (TextView) view.findViewById(R.id.text_sure);
        TextView textTitle = (TextView) view.findViewById(R.id.text_title);
        textTitle.setText(title);
        text_content.setText(content);
        tvSure.setText(sure);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);
                text_content.setVisibility(View.GONE);
                tvSure.setVisibility(View.GONE);
                listener.progressDisimiss();
            }
        });
    }

    public ProgressBar getProgressBar(){
        return this.pb;
    }

    public TextView getProgress(){
        return tv;
    }
}

