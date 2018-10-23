package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.R;
import com.umeng.soexample.bean.Bean;

import java.util.List;

/**
 * Created by HP on 2018/10/23.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private Context context;
    private List<Bean.DataBean> list;

    public ItemAdapter(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ItemViewHolder holder, int position) {
        holder.recycle_view.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        final MyAdapter showAdapter = new MyAdapter(context,list.get(position).getList());
        holder.recycle_view.setAdapter(showAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recycle_view;

        public ItemViewHolder(View itemView) {
            super(itemView);
            recycle_view = (RecyclerView) itemView.findViewById(R.id.recycle_view);
        }
    }
}
