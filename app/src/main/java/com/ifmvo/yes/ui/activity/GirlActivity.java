package com.ifmvo.yes.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;

import com.ifmvo.yes.R;
import com.ifmvo.yes.app.G;
import com.ifmvo.yes.base.BaseActivity;
import com.ifmvo.yes.presenter.impl.GirlPresenterImpl;
import com.ifmvo.yes.presenter.interfaces.IGirlPresenter;
import com.ifmvo.yes.ui.adapter.GirlAdapter;
import com.ifmvo.yes.ui.custom.RecycleViewDivider;
import com.ifmvo.yes.ui.custom.TitleBar;
import com.ifmvo.yes.ui.view.interfaces.IGirlView;
import com.ifmvo.yes.utils.Logger;
import com.ifmvo.yes.vo.info.Girl;
import com.ifmvo.yes.vo.request.GirlRequest;
import com.ifmvo.yes.vo.response.GirlResponse;

import java.util.List;

import butterknife.Bind;

/**
 * BeautifulGirl List
 * ifmvo on 2016/4/3.
 */
public class GirlActivity extends BaseActivity implements IGirlView,
        SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.swipe_refresh_layout_girl)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.rv_girl)
    RecyclerView recyclerView;

    IGirlPresenter girlPresenter;
    GirlAdapter girlAdapter;

    boolean hasMoreData = true;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_girl);
    }

    @Override
    public void initView() {
        titleBar.setTitle(getString(R.string.girl_title));
        //2列
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        // 设置Item分割线
        recyclerView.addItemDecoration(new RecycleViewDivider(10));
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setColorSchemeResources(R.color.app_theme);
        swipeRefreshLayout.setOnRefreshListener(this);

        girlAdapter = new GirlAdapter(getContext());
        recyclerView.setAdapter(girlAdapter);
        //加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottom =
                        layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1]
                                >= girlAdapter.getItemCount() - 4;
                if (!swipeRefreshLayout.isRefreshing() && isBottom && hasMoreData) {
                    setRefreshViewVisibility(true);
                    GirlRequest params = new GirlRequest();
                    params.page = (girlAdapter.getItemCount() / Integer.parseInt(G.PAGE_SIZE) + 1) +"";
                    girlPresenter.requestMoreGirlDataFromInternet(GirlActivity.this, params);
                }
            }
        });
    }

    /**
     * 是否显示 swipeRefreshLayout 刷新的圈
     * @param isShow
     */
    private void setRefreshViewVisibility(boolean isShow) {
        swipeRefreshLayout.setRefreshing(isShow);
    }

    /**
     * 1、初始化请求数据没在 onCreate 方法里边进行，
     *    是因为当你里边此 Activity 再回来，就不调用 onCreate 了
     * 2、还有就是 此Activity被动离开再回来（例如点击任务栏里边的通知再回来），
     *    这个方法也会被调用，而 onStart 不会
     *
     * 3、所熟知的声明周期并没有这个方法呀，还有待研究！！
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        showShapeLoadingDialog(true, getString(R.string.loading));

        GirlRequest params = new GirlRequest();
        params.page = "1";  //这个是第一次请求或刷新的参数，永远 1
        girlPresenter.requestGirlDataFromInternet(GirlActivity.this, params);
    }

    @Override
    public void initPresenter() {
        girlPresenter = new GirlPresenterImpl();
    }
    /**
     * 在Presenter中的请求 Callback 里边调用此方法，把数据传过来
     * @param girlResponse
     */
    @Override
    public void queryGirlData(GirlResponse girlResponse) {
        List<Girl> list = girlResponse.newslist;
        Logger.e(list.toString());
        //如果请求回来的list大小为0 ，就说明没有更多数据了
        girlAdapter.clearAndReAddDataToList(list);
        setRefreshViewVisibility(false);
        hideShapeLoadingDialog();
    }

    @Override
    public void againQueryGirlData(GirlResponse girlResponse) {
        List<Girl> list = girlResponse.newslist;
        if (list != null && list.size() < 10){
            hasMoreData = false;
            showToast(getString(R.string.no_more_data));
        }
        girlAdapter.againAddDataToList(list);
        setRefreshViewVisibility(false);
    }

    @Override
    public void handleNoConnect(String error) {
        //提示用户没网
        showNoConnect(error);
        //把Dialog关了
        hideShapeLoadingDialog();
        //把swipeRefreshLayout的刷新也关了
        setRefreshViewVisibility(false);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        GirlRequest params = new GirlRequest();
        params.page = "1";  //这个是第一次请求或刷新的参数，永远 1
        girlPresenter.requestGirlDataFromInternet(GirlActivity.this, params);
    }
}
