package com.ifmvo.yes.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifmvo.yes.R;
import com.ifmvo.yes.ui.custom.RatioImageView;
import com.ifmvo.yes.vo.info.QiWen;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ifmvo on 2016/4/6.
 */
public class QiWenAdapter extends RecyclerView.Adapter<QiWenAdapter.QiWenViewHolder> {

    private List<QiWen> qiWens;
    private Context context;

    private ItemClickListener itemClickListener;

    public interface ItemClickListener{
        void OnItemClick(QiWen qiWen);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    public QiWenAdapter(Context context) {
        this.context = context;
        qiWens = new ArrayList<>();
    }

    @Override
    public QiWenAdapter.QiWenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_qiwen, null);
        QiWenViewHolder qiWenViewHolder = new QiWenViewHolder(view);
        return qiWenViewHolder;
    }

    @Override
    public void onBindViewHolder(QiWenViewHolder holder, int position) {
        final QiWen q = qiWens.get(position);
        ImageLoader.getInstance().displayImage(q.picUrl, holder.ratioImageView);
        holder.tvTitle.setText(q.title);
        holder.tvDescription.setText(q.description);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.OnItemClick(q);
            }
        });
    }

    @Override
    public int getItemCount() {
        return qiWens.size();
    }

    public void clearAndReAddDataToList(List<QiWen> q){
        qiWens.clear();
        qiWens.addAll(q);
        notifyDataSetChanged();
    }

    public void againAddDataToList(List<QiWen> q){
        qiWens.addAll(q);
        notifyDataSetChanged();
    }

    public class QiWenViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.riv_qiwen_image)
        RatioImageView ratioImageView;
        @Bind(R.id.tv_qiwen_title)
        TextView tvTitle;
        @Bind(R.id.tv_qiwen_description)
        TextView tvDescription;
        @Bind(R.id.container)
        LinearLayout container;

        public QiWenViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
