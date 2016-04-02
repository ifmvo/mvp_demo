package com.ifmvo.yes.BeautifulGirl.View;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ifmvo.testoverscrollview.Custom.PullToRefreshLayout;
import com.ifmvo.testoverscrollview.Custom.PullableListView;
import com.ifmvo.yes.BaseActivity;
import com.ifmvo.yes.BeautifulGirl.Model.Girl;
import com.ifmvo.yes.BeautifulGirl.Presenter.BeautifulGirlAdapter;
import com.ifmvo.yes.BeautifulGirl.Presenter.BeautifulGirlPresenter;
import com.ifmvo.yes.R;
import com.mingle.widget.ShapeLoadingDialog;

import net.tsz.afinal.FinalBitmap;

import java.util.List;


/**
 * 我的注释呢
 *
 */
public class BeautifulGirlActivity extends BaseActivity implements IBeautifulGirlView {

    public static final String TAG = BeautifulGirlActivity.class.getSimpleName();
    /**
     * 无法加载的页面（没网、请求错误）
     */
    private LinearLayout mCannotLoadLayout;
    /**
     * 正在加载的对话框
     */
    private ShapeLoadingDialog shapeLoadingDialog;
    /**
     * 下拉刷新的布局
     */
    private PullToRefreshLayout mGirlPullToRefreshLayout;
    /**
     * 下拉刷新的ListView
     */
    private PullableListView mGirlListView;
    /**
     * 配合 ListView 使用的适配器
     */
    BeautifulGirlAdapter beautifulGirlAdapter;
    /**
     * 装girls数据的容器
     */
//    private List<Girl> girls;
    /**
     * 美女模块Presenter对象
     */
    private BeautifulGirlPresenter beautifulGirlPresenter;
    /**
     * AFinal框架的图像处理
     */
    private FinalBitmap finalBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.beautiful_girl), true);

        showProgressBar(true);
        beautifulGirlPresenter.requestGirlData();
        setHasMoreData(false);
    }

    @Override
    protected void initPresenter() {
        beautifulGirlPresenter = new BeautifulGirlPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_beautiful_girl;
    }

    @Override
    protected void initView() {
        mCannotLoadLayout = (LinearLayout) findViewById(R.id.cannot_load_container);
        mGirlPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.girl_pull_to_refresh_layout);
        mGirlListView = (PullableListView) findViewById(R.id.girl_pullabel_listview);
        shapeLoadingDialog = new ShapeLoadingDialog(this);
    }

    @Override
    protected void initData() {
        finalBitmap = FinalBitmap.create(BeautifulGirlActivity.this);
        finalBitmap.configLoadingImage(R.mipmap.ic_launcher);
        finalBitmap.configLoadfailImage(R.mipmap.logo);
    }

    @Override
    protected void setListener() {
        mCannotLoadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar(true);
                beautifulGirlPresenter.requestGirlData();
            }
        });

        mGirlPullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                beautifulGirlPresenter.requestGirlData();
            }
        });

        mGirlListView.setOnLoadListener(new PullableListView.OnLoadListener() {
            @Override
            public void onLoad(PullableListView pullableListView) {
                beautifulGirlPresenter.requestMoreGirlData();
            }
        });
    }

    @Override
    public void loadGirlDataToListView(List<Girl> girls) {
        beautifulGirlAdapter = new BeautifulGirlAdapter(BeautifulGirlActivity.this,
                girls, finalBitmap);
        mGirlListView.setAdapter(beautifulGirlAdapter);
    }

    @Override
    public void notifyAdapter() {
        beautifulGirlAdapter.notifyDataSetChanged();
        mGirlPullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show)
            shapeLoadingDialog.show();
        else
            shapeLoadingDialog.dismiss();
    }

    @Override
    public void showCannotLoad(boolean show) {
        if (show)
            mCannotLoadLayout.setVisibility(View.VISIBLE);
        else
            mCannotLoadLayout.setVisibility(View.GONE);
    }

    @Override
    public void setHasMoreData(boolean hasMoreData) {
        if (hasMoreData)
            mGirlListView.setHasMoreData(true);
        else
            mGirlListView.setHasMoreData(false);
    }
}
