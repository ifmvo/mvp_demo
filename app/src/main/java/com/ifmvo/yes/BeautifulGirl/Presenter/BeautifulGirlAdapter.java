package com.ifmvo.yes.BeautifulGirl.Presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifmvo.yes.BeautifulGirl.Model.Girl;
import com.ifmvo.yes.R;

import net.tsz.afinal.FinalBitmap;

import java.util.List;

/**
 * ifmvo on 2016/3/29.
 */
public class BeautifulGirlAdapter extends BaseAdapter {


    private static final String TAG = BeautifulGirlAdapter.class.getSimpleName();

    private Context context;
    private List<Girl> girls;
    FinalBitmap finalBitmap;

    public BeautifulGirlAdapter(Context context, List<Girl> girls, FinalBitmap finalBitmap) {
        this.finalBitmap = finalBitmap;
        this.context = context;
        this.girls = girls;
    }

    @Override
    public int getCount() {
        return girls.size();
    }

    @Override
    public Object getItem(int position) {
        return girls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GirlViewHolder holder;
        if (convertView == null) {
            holder = new GirlViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_girl, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.item_iv);
            convertView.setTag(holder);
        }
        else {
            holder = (GirlViewHolder) convertView.getTag();
        }

        Girl girl = girls.get(position);

        finalBitmap.display(holder.iv, girl.getPicUrl());
        return convertView;
    }

    class GirlViewHolder{
        ImageView iv;
        TextView tvTitle;
    }
}
