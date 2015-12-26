package com.qingsongjia.qingsongjia.exchange;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExchangeDetailActivity extends WanListActivity {

    @Bind(R.id.exchange_icon)
    SimpleDraweeView exchangeIcon;
    @Bind(R.id.exchange_name)
    TextView exchangeName;
    @Bind(R.id.exch_content)
    TextView exchContent;
    @Bind(R.id.exch_img)
    ImageView exchImg;
    private ArrayList<String> data = new ArrayList<>();
    private DisplayImageOptions displayOptions;

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("详情");
        int id = getIntent().getIntExtra("id", 0);
    }

    @Override
    public WanAdapter getAdapter() {
        ExchangeAdapter adapter = new ExchangeAdapter(getContext(), data, R.layout.item_exchange_detail);
        View headerView = getLayoutInflater().inflate(R.layout.activity_exchange_detail, null);
        ButterKnife.bind(this, headerView);
        adapter.addHeaderView(headerView);
        String url="http://img1.imgtn.bdimg.com/it/u=2445236384,366556919&fm=21&gp=0.jpg";

        displayOptions = ((App) getAppContext()).getDisplayOptions();

        ImageLoader.getInstance().displayImage(url,exchImg,displayOptions);

        return adapter;
    }

    @Override
    protected void loadData() {
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        loadFinish("");
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }


    class ExchangeAdapter extends WanAdapter<String> {

        public ExchangeAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, final int position, String item) {
          TextView reply=  holder.getView(R.id.exchItem_reply);
            reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIManager.startReply(getContext(),position);
                }
            });
        }
    }
}



