package com.ifmvo.yes.ui.adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifmvo.yes.R;
import com.ifmvo.yes.ui.custom.RatioImageView;
import com.ifmvo.yes.vo.info.Girl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ifmvo on 2016/4/3.
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder> {

    Context context;
    List<Girl> girls;

    ColorFilter mColorFilter;
    public GirlAdapter(Context context) {
        this.context = context;
        girls = new ArrayList<>();

        float[]array = new float[]{
                1,0,0,0,-70,
                0,1,0,0,-70,
                0,0,1,0,-70,
                0,0,0,1,0,
        };
        mColorFilter = new ColorMatrixColorFilter(new ColorMatrix(array));
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_girl, null);
        GirlViewHolder girlViewHolder = new GirlViewHolder(view);
        return girlViewHolder;
    }

    @Override
    public void onBindViewHolder(final GirlViewHolder holder, int position) {
        Girl g = girls.get(position);

//        Glide.with(context)
//                .load(g.picUrl)
////                .centerCrop()
//                .into(holder.ratioImageView)
//                .getSize(new SizeReadyCallback() {
//                    @Override
//                    public void onSizeReady(int width, int height) {
//                        holder.ratioImageView.setColorFilter(mColorFilter);
//                    }
//                });

        ImageLoader.getInstance().displayImage(g.getPicUrl(), holder.ratioImageView);
        holder.textView.setText(g.getTitle() + "," + g.getDescription());
    }

    @Override
    public int getItemCount() {
        return girls.size();
    }

    public void clearAndReAddDataToList(List<Girl> g){
        if (g != null && g.size() > 0){
            girls.clear();
            girls.addAll(g);
            notifyDataSetChanged();
        }
    }

    public void againAddDataToList(List<Girl> g){
        if (g != null && g.size() > 0){
            girls.addAll(g);
            notifyDataSetChanged();
        }
    }

    public class GirlViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.riv_girl_image)
        RatioImageView ratioImageView;
        @Bind(R.id.tv_girl_title)
        TextView textView;

        public GirlViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            ratioImageView.setOriginalSize(50, 50);
        }
    }
}
