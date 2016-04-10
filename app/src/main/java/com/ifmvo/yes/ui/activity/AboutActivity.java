package com.ifmvo.yes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifmvo.yes.R;
import com.ifmvo.yes.app.BaseApplication;
import com.ifmvo.yes.base.BaseActivity;
import com.ifmvo.yes.ui.custom.TitleBar;

import butterknife.Bind;

/**
 * ifmvo on 2016/4/7.
 */
public class AboutActivity extends BaseActivity {

    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.tv_version_code)
    TextView tvVersion;

    public static void action(Context context){
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    public void initView() {

        titleBar.setTitle(getString(R.string.about));

        ImageView iv = new ImageView(getContext());
        iv.setImageResource(R.mipmap.back_button);
        titleBar.setLeftView(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvVersion.setText(getString(R.string.about_version_code, BaseApplication.getVersion()));

    }

    @Override
    public void initPresenter() {

    }
}
