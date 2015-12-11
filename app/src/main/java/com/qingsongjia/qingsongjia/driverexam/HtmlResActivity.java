package com.qingsongjia.qingsongjia.driverexam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.io.File;

public class HtmlResActivity extends WanListActivity {


    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        return null;
    }

    @Override
    protected void loadData() {
        String path = "file:///android_asset/html/驾校首页/科目一/新手上路";

        File f = new File(path);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            Log.i("========",files[i].getName());
        }

    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}
