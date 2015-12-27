package com.qingsongjia.qingsongjia.exchange;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.qingsongjia.qingsongjia.adapter.OnlyImageAdapter;
import com.qingsongjia.qingsongjia.bean.Exchange;
import com.qingsongjia.qingsongjia.bean.ExchangeDetail;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;
import com.wan7451.wanadapter.recycle.WrappingLinearLayoutManager;

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
    @Bind(R.id.exch_imgs)
    RecyclerView exchImgs;
    private ArrayList<String> data = new ArrayList<>();
    private DisplayImageOptions displayOptions;
    private int id;
    private ExchangeDetail exchDetal;

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("详情");
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    public WanAdapter getAdapter() {
        ExchangeAdapter adapter = new ExchangeAdapter(getContext(), data, R.layout.item_exchange_detail);
        View headerView = getLayoutInflater().inflate(R.layout.activity_exchange_detail, null);
        ButterKnife.bind(this, headerView);
        adapter.addHeaderView(headerView);


        Button btn= new Button(getContext());
        btn.setText("评论");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startReply(getContext(),-1,exchDetal.getDid());
            }
        });
        adapter.addFooterView(btn);

        return adapter;
    }

    @Override
    protected void loadData() {

        NetRequest.loadExchangeDetail(getContext(), id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    String data = response.getString(0);
                    exchDetal = JSONObject.parseObject(data, ExchangeDetail.class);
                    if (!TextUtils.isEmpty(exchDetal.getUser_file())) {
                        exchangeIcon.setImageURI(Uri.parse(exchDetal.getUser_file()));
                    }
                    exchangeName.setText(exchDetal.getCreate_nm());
                    exchContent.setText(exchDetal.getDri_text());

                    String urls = exchDetal.getDri_image_url();

                    ArrayList<String> imgs = new ArrayList<>();
                    if (urls.contains(",")) {
                        String[] allImgs = urls.split(",");
                        for (int i = 0; i < allImgs.length; i++) {
                            imgs.add(allImgs[i]);
                        }
                    } else {
                        imgs.add(urls);
                    }

                    OnlyImageAdapter adapter1 = new OnlyImageAdapter(getContext(), imgs, R.layout.item_exchange_nolyimg);
                    exchImgs.setAdapter(adapter1);
                    exchImgs.setLayoutManager(new WrappingLinearLayoutManager(getContext()));

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getMainAdapter().notifyDataSetChanged();
                        }
                    },300);
                }
            }

            @Override
            public void onResponseError(String error) {

            }
        });


        NetRequest.loadExchangeComment(getContext(), id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {

            }

            @Override
            public void onResponseError(String error) {

            }
        });

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
            TextView reply = holder.getView(R.id.exchItem_reply);
            reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIManager.startReply(getContext(), position,exchDetal.getDid());
                }
            });
        }
    }
}



