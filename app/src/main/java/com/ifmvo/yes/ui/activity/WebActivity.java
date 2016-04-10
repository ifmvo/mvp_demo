package com.ifmvo.yes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BaseActivity;
import com.ifmvo.yes.ui.custom.TitleBar;

import butterknife.Bind;

/**
 * ifmvo on 2016/4/7.
 */
public class WebActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String WEB_URL = "web_url";
    public static final String WEB_TITLE = "web_title";
    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.swipe_refresh_layout_web)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.web_view)
    WebView webView;

    public static void action(Context context, String url, String title){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WEB_URL, url);
        intent.putExtra(WEB_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_web);
    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        String url = intent.getStringExtra(WEB_URL);
        String title = intent.getStringExtra(WEB_TITLE);
        titleBar.setTitle(title);

        ImageView iv = new ImageView(getContext());
        iv.setImageResource(R.mipmap.back_button);
        titleBar.setLeftView(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.app_theme);
        swipeRefreshLayout.setOnRefreshListener(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new LoveClient());

        webView.loadUrl(url);

    }

    @Override
    public void onRefresh() {
        webView.reload();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_open_url:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(webView.getUrl());
                intent.setData(uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(WebActivity.this, "打开失败", Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class LoveClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (TextUtils.isEmpty(url)){
                return true;
            }
            if(Uri.parse(url).getHost().equals("github.com")){
                return false;
            }
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            swipeRefreshLayout.setRefreshing(false);
            showToast(description);
        }
    }

    @Override
    public void initPresenter() {

    }
}
