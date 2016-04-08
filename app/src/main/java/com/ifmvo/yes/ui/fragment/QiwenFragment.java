package com.ifmvo.yes.ui.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.ifmvo.yes.utils.Logger;
import com.ifmvo.yes.vo.info.QiWen;
import com.ifmvo.yes.vo.request.QiWenRequest;
import com.ifmvo.yes.vo.response.QiWenResponse;

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

    IQiWenPresenter qiWenPresenter;
    QiWenAdapter qiWenAdapter;

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
                Log.e("info", "最后一个可见的：" + layoutManager.findLastCompletelyVisibleItemPosition());
                Log.e("info", "getItemCount()" + qiWenAdapter.getItemCount());
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPosition()
                        >= qiWenAdapter.getItemCount() - 1;
                if (!swipeRefreshLayout.isRefreshing() && isBottom && hasMoreData) {
                    setRefreshViewVisibility(true);
                    QiWenRequest params = new QiWenRequest();
                    params.page = (qiWenAdapter.getItemCount() / G.QIWEN_PAGE_SIZE + 1) + "";
                    qiWenPresenter.requestMoreQiWenData(QiwenFragment.this, params);
                }
            }
        });

        qiWenAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        QiWenRequest  params = new QiWenRequest();
        params.page = "1";
        qiWenPresenter.requestQiWenData(QiwenFragment.this, params);
    }

    private void setRefreshViewVisibility(boolean isShow) {
        swipeRefreshLayout.setRefreshing(isShow);
    }

    @Override
    public void initPresenter() {
        qiWenPresenter = new QiWenPresenterImpl();
    }

    @Override
    public void queryQiWenDataFromInternet(QiWenResponse qiWenResponse) {
        List<QiWen> list = qiWenResponse.result;
        Logger.e("个数："+list.size());
        //如果请求回来的list大小为0 ，就说明没有更多数据了
        qiWenAdapter.clearAndReAddDataToList(list);
        setRefreshViewVisibility(false);
        hideShapeLoadingDialog();
        hasMoreData = true;
    }

    @Override
    public void againQueryQiWenFromInternet(QiWenResponse qiWenResponse) {
        List<QiWen> list = qiWenResponse.result;
        if (list != null && list.size() < G.QIWEN_PAGE_SIZE){
            hasMoreData = false;
            showToast(getString(R.string.no_more_data));
        }
        qiWenAdapter.againAddDataToList(list);
        setRefreshViewVisibility(false);
    }

    @Override
    public void handleNoConnect(String msg) {
        showNoConnect(msg);
    }

    @Override
    public void onRefresh() {
        QiWenRequest  params = new QiWenRequest();
        params.page = "1";
        qiWenPresenter.requestQiWenData(QiwenFragment.this, params);
    }

    @Override
    public void OnItemClick(QiWen qiWen) {
        WebActivity.action(getContext(), qiWen.url,qiWen.description);
    }
}
