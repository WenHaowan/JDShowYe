package com.umeng.soexample;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.abner.ming.MingUtils;
import com.abner.ming.ResultListener;
import com.abner.ming.UmengBean;
import com.google.gson.Gson;
import com.umeng.socialize.UMShareAPI;
import com.umeng.soexample.adapter.ItemAdapter;
import com.umeng.soexample.adapter.LunAdapter;
import com.umeng.soexample.adapter.MyAdapter;
import com.umeng.soexample.bean.Bean;
import com.umeng.soexample.bean.LunBoBean;
import com.umeng.soexample.presenter.ShowPresenter;
import com.umeng.soexample.view.ShowView;
import com.umeng.soexample.view.WenView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShowView,WenView{

    private ImageView login;
    private ImageView shared;
    private List<LunBoBean.DataBean> list = new ArrayList();
    private ViewPager view_page;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                int i = view_page.getCurrentItem();
                i++;
                view_page.setCurrentItem(i);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        };
    };
    private RecyclerView recy_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (ImageView) findViewById(R.id.login);
        shared = (ImageView) findViewById(R.id.shared);
        recy_view = (RecyclerView) findViewById(R.id.recy_view);
        view_page = (ViewPager) findViewById(R.id.view_pager);
        //调用p层
        recy_view.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        ShowPresenter presenter = new ShowPresenter((WenView) MainActivity.this);
        ShowPresenter presenter1 = new ShowPresenter((ShowView) MainActivity.this);
        presenter1.show();
        presenter.show1();
        //qq登录点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        //qq分享点击事件
        shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared();
            }
        });
    }
    //qq登录
    public void login(){
        MingUtils.login(MainActivity.this, 0, new ResultListener() {
            @Override
            public void success(UmengBean umengBean) {
                umengBean.getName();
                umengBean.getIconurl();
                umengBean.getGender();
                Toast.makeText(MainActivity.this,umengBean.getName()+""+umengBean.getGender()+""+umengBean.getIconurl(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //qq分享
    public  void shared(){
        MingUtils.shared(MainActivity.this,0,"","","","");
    }
    //qq回调并吐司
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void failure(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void success(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                LunBoBean showBean = gson.fromJson(msg, LunBoBean.class);
                List<LunBoBean.DataBean> data = showBean.getData();
                LunAdapter adapter = new LunAdapter(MainActivity.this,data);
                view_page.setAdapter(adapter);
                handler.sendEmptyMessageDelayed(0, 2000);
                view_page.setCurrentItem(list.size()*1000);
            }
        });
    }

    @Override
    public void onfailure(final String msy) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msy,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onsuccess(final String msy) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(msy, Bean.class);
                List<Bean.DataBean> data = bean.getData();
                ItemAdapter whAdapter = new ItemAdapter(MainActivity.this,data);
                recy_view.setAdapter(whAdapter);
            }
        });
    }
}
