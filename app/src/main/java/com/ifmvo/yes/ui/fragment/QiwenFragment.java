package com.ifmvo.yes.ui.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.ifmvo.yes.R;
import com.ifmvo.yes.app.G;
import com.ifmvo.yes.base.BaseFragment;
import com.ifmvo.yes.presenter.impl.QiWenPresenterImpl;
import com.ifmvo.yes.presenter.interfaces.IQiWenPresenter;
import com.ifmvo.yes.ui.activity.WebActivity;
import com.ifmvo.yes.ui.adapter.QiWenAdapter;
import com.ifmvo.yes.ui.custom.RecycleViewDivider;
import com.ifmvo.yes.ui.view.interfaces.IQiWenView;
import com.ifmvo.yes.vo.info.QiWen;
import com.ifmvo.yes.vo.request.QiWenRequest;

import java.util.List;

import butterknife.Bind;

/**
 * 骑车、游泳、编程、手游
 * ifmvo
 */
public class QiwenFragment extends BaseFragment implements IQiWenView,
        SwipeRefreshLayout.OnRefreshListener, QiWenAdapter.ItemClickListener{

    @Bind(R.id.swipe_refresh_layout_qiwen)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.rv_qiwen)
    RecyclerView recyclerView;

    IQiWenPresenter qiWenPresenter = new QiWenPresenterImpl();
    QiWenAdapter qiWenAdapter;

    /**
     * 数据是否已经请求过了
     */
    boolean haveLoadingData = false;
    /**
     * 是否在onStart方法里边请求数据
     */
    boolean doOnStartLoadData = true;
    /**
     * 是否还有更多的数据
     */
    boolean hasMoreData = true;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_qiwen;
    }

    @Override
    public void initView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        // 设置Item分割线
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayout.HORIZONTAL));
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setColorSchemeResources(R.color.app_theme);
        swipeRefreshLayout.setOnRefreshListener(this);

        qiWenAdapter = new QiWenAdapter(getContext());
        recyclerView.setAdapter(qiWenAdapter);
        //加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPosition()
                        >= qiWenAdapter.getItemCount() - 1;
                if (!swipeRefreshLayout.isRefreshing() && isBottom && hasMoreData) {
                    setRefreshViewVisibility(true);
                    notifyRequest(false);
                }
            }
        });

        qiWenAdapter.setOnItemClickListener(this);
    }

    private void setRefreshViewVisibility(boolean isShow) {
        swipeRefreshLayout.setRefreshing(isShow);
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void queryQiWenDataFromInternet(List<QiWen> list) {
        //如果请求回来的list大小为0 ，就说明没有更多数据了
        qiWenAdapter.clearAndReAddDataToList(list);
        setRefreshViewVisibility(false);
        hideShapeLoadingDialog();
        hasMoreData = true;
        hideShapeLoadingDialog();
    }

    @Override
    public void againQueryQiWenFromInternet(List<QiWen> list) {

        if (list != null){
            if (list.size() < G.QIWEN_PAGE_SIZE){
                hasMoreData = false;
                showToast(getString(R.string.no_more_data));
            }
            if (list.size() != 0)
                qiWenAdapter.againAddDataToList(list);
        }else{
            hasMoreData = false;
            showToast(getString(R.string.no_more_data));
        }
        setRefreshViewVisibility(false);
    }

    @Override
    public void handleNoConnect(String msg) {
        showNoConnect(msg);
    }

    @Override
    public void onRefresh() {
        notifyRequest(true);
    }

    @Override
    public void OnItemClick(QiWen qiWen) {
        WebActivity.action(getContext(), qiWen.getUrl(), qiWen.getDescription());
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

    /**
     * @param isFirst true 就是请求第一页，false 就是加载更多
     */
    public void notifyRequest(boolean isFirst){
        QiWenRequest params = new QiWenRequest();
        if (isFirst){
            params.page = "1";
            qiWenPresenter.requestQiWenData(QiwenFragment.this, params);
        }else{
            params.page = (qiWenAdapter.getItemCount() / G.GIRL_PAGE_SIZE + 1) + "";
            qiWenPresenter.requestMoreQiWenData(QiwenFragment.this, params);
        }
    }

}
