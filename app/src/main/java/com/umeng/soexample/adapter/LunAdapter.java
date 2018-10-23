package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.MainActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.LunBoBean;

import java.util.List;

/**
 * Created by HP on 2018/10/22.
 */

public class LunAdapter extends PagerAdapter{

    private Context context;
    private List<LunBoBean.DataBean> list;

    public LunAdapter(Context context, List<LunBoBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.adapter_layout, null);
        ImageView image_view = (ImageView) view.findViewById(R.id.image_view);
        Glide.with(context).load(list.get(position%list.size()).getIcon().toString().replace("https","http")).into(image_view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
