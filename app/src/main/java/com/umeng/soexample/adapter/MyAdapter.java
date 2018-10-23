package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2018/10/22.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    private List<Bean.DataBean.ListBean> list;

    public MyAdapter(Context context, List<Bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_wen, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {

        holder.price.setText("优惠价：¥" + list.get(position).getPrice());
        holder.name.setText(list.get(position).getTitle());
        String[] imgs = list.get(position).getImages().split("\\|");
        Glide.with(context).load(imgs[0]).into(holder.image_view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image_view;
        private final TextView price;
        private final TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);

            image_view = (ImageView) itemView.findViewById(R.id.image_view);
            price = (TextView) itemView.findViewById(R.id.price);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
