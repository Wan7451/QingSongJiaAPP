package com.qingsongjia.qingsongjia.exchange;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.wan7451.base.WanActivity;


import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageActivity extends WanActivity {

    private ProgressBar progressBar;
    private ImageView view;
    private PhotoViewAttacher mAttacher;

    @Override
    protected boolean isShowTitleView() {
        return false;
    }

    @Override
    public void initView() {

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        view = (ImageView) findViewById(R.id.image_icon);
        progressBar = (ProgressBar) findViewById(R.id.image_progress);

        initSystemBar(0xFF000000);

        Uri uri = getIntent().getParcelableExtra("imgUri");

        DisplayImageOptions options = ((App) getAppContext()).getDisplayOptions();
        mAttacher = new PhotoViewAttacher(view);

        ImageLoader.getInstance().displayImage(uri.toString(), view, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                progressBar.setVisibility(View.GONE);
                mAttacher.update();
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_image;
    }


}
