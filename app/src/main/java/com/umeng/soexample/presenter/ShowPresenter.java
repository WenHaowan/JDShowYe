package com.umeng.soexample.presenter;

import android.view.View;

import com.umeng.soexample.model.ShowModel;
import com.umeng.soexample.view.ShowView;
import com.umeng.soexample.view.WenView;

/**
 * Created by HP on 2018/10/22.
 */

public class ShowPresenter {
    private ShowModel showModel;
    private ShowView showView;
    private WenView wenView;

    public ShowPresenter(ShowView showView) {
        this.showView = showView;
        showModel = new ShowModel();
    }

    public ShowPresenter(WenView wenView) {
        this.wenView = wenView;
        showModel = new ShowModel();
    }

    public void show(){
        showModel.show(new ShowModel.ShowCallback() {
            @Override
            public void failure(String msg) {
                showView.failure(msg);
            }

            @Override
            public void success(String msg) {
                showView.success(msg);
            }
        });
    }

    public void show1(){
        showModel.show1(new ShowModel.Show1Callback() {
            @Override
            public void onfailure(String msy) {
                wenView.onfailure(msy);
            }

            @Override
            public void onsuccess(String msy) {
                wenView.onsuccess(msy);
            }
        });
    }
}
