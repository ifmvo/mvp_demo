package com.ifmvo.yes.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;

import com.ifmvo.yes.R;
import com.ifmvo.yes.app.G;
import com.ifmvo.yes.base.BaseFragment;
import com.ifmvo.yes.presenter.impl.GirlPresenterImpl;
import com.ifmvo.yes.presenter.interfaces.IGirlPresenter;
import com.ifmvo.yes.ui.adapter.GirlAdapter;
import com.ifmvo.yes.ui.custom.RecycleViewDivider;
import com.ifmvo.yes.ui.view.interfaces.IGirlView;
import com.ifmvo.yes.vo.info.Girl;
import com.ifmvo.yes.vo.request.GirlRequest;

import java.util.List;

import butterknife.Bind;

/**
 * ifmvo on 2016/4/6.
 */
public class GirlFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IGirlView {

    @Bind(R.id.swipe_refresh_layout_girl)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.rv_girl)
    RecyclerView recyclerView;

    IGirlPresenter girlPresenter = new GirlPresenterImpl();
    GirlAdapter girlAdapter;

    /**
     * 数据是否已经请求过了
     */
    boolean haveLoadingData = false;
    /**
     * 是否在onStart方法里边请求数据
     */
    boolean doOnStartLoadData = true;

    boolean hasMoreData = true;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_girl;
    }

    @Override
    public void initView() {
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
                    notifyRequest(false);
                }
            }
        });
    }

    @Override
    public void initPresenter() {
    }

    /**
     * 是否显示 swipeRefreshLayout 刷新的圈
     * @param isShow
     */
    private void setRefreshViewVisibility(boolean isShow) {
        swipeRefreshLayout.setRefreshing(isShow);
    }

    @Override
    public void onRefresh() {
        notifyRequest(true);
    }

    /**
     * 在Presenter中的请求 Callback 里边调用此方法，把数据传过来
     */
    @Override
    public void queryGirlData(List<Girl> list) {
        //如果请求回来的list大小为0 ，就说明没有更多数据了
        girlAdapter.clearAndReAddDataToList(list);
        setRefreshViewVisibility(false);
        hideShapeLoadingDialog();
        hasMoreData = true;
        hideShapeLoadingDialog();
    }

    @Override
    public void againQueryGirlData(List<Girl> list) {

        /**
         * 如果没有更多数据，
         * list可能为 空 或 list.size() < page_size
         */
        if (list != null){
            if (list.size() < G.GIRL_PAGE_SIZE){
                hasMoreData = false;
                showToast(getString(R.string.no_more_data));
            }
            if (list.size() != 0)
                girlAdapter.againAddDataToList(list);
        }else{
            hasMoreData = false;
            showToast(getString(R.string.no_more_data));
        }
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && !doOnStartLoadData && !haveLoadingData){
            showShapeLoadingDialog(false, getString(R.string.loading));
            notifyRequest(true);
            haveLoadingData = true;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //可见、在onstart、没请求过
        if (doOnStartLoadData && !haveLoadingData){
            if (getUserVisibleHint()) {
                showShapeLoadingDialog(false, getString(R.string.loading));
                notifyRequest(true);
            }else{
                doOnStartLoadData = false;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        haveLoadingData = false;
    }

    public void notifyRequest(boolean isFirst){
        GirlRequest params = new GirlRequest();
        if (isFirst){
            params.page = "1";
            girlPresenter.requestGirlDataFromInternet(GirlFragment.this, params);
        }else{
            params.page = (girlAdapter.getItemCount() / G.GIRL_PAGE_SIZE + 1) + "";
            girlPresenter.requestMoreGirlDataFromInternet(GirlFragment.this, params);
        }
    }
}
