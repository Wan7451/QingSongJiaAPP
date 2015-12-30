package com.qingsongjia.qingsongjia.exchange;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.App;
import com.qingsongjia.qingsongjia.bean.ExchangeDetail;
import com.qingsongjia.qingsongjia.bean.ExchangeReply;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.DensityUtil;
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
    @Bind(R.id.exch_imgs)
    LinearLayout exchImgs;
    //    @Bind(R.id.exch_imgs)
//    RecyclerView exchImgs;
    private ArrayList<ExchangeReply> data = new ArrayList<>();
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

        findViewById(R.id.exchReply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startReply(ExchangeDetailActivity.this, 1, exchDetal.getDid(), -1);
            }
        });
    }

    @Override
    public WanAdapter getAdapter() {
        ExchangeAdapter adapter = new ExchangeAdapter(getContext(), data, R.layout.item_exchange_detail);
        View headerView = getLayoutInflater().inflate(R.layout.activity_exchange_top, null);
        ButterKnife.bind(this, headerView);
        adapter.addHeaderView(headerView);

//        addFooterView(adapter);

        return adapter;
    }

    private void addFooterView(ExchangeAdapter adapter) {
        LinearLayout layout=new LinearLayout(getContext());
        Button btn = new Button(getContext());
        btn.setTextColor(Color.WHITE);
        btn.setTextSize(14);
        btn.setBackgroundResource(R.drawable.shape_button_bg);
//        btn.setBackground(getResources().getDrawable(R.drawable.shape_button_bg));

        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(DensityUtil.dip2px(getContext(),16),
                DensityUtil.dip2px(getContext(),10),
                DensityUtil.dip2px(getContext(),16),
                DensityUtil.dip2px(getContext(),10)
        );
        btn.setLayoutParams(lp);
        layout.addView(btn);
        btn.setText("评论");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startReply(ExchangeDetailActivity.this, 1, exchDetal.getDid(), -1);
            }
        });
        adapter.addFooterView(layout);
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

//                    OnlyImageAdapter adapter1 = new OnlyImageAdapter(getContext(), imgs, R.layout.item_exchange_nolyimg);
//                    exchImgs.setAdapter(adapter1);
//                    exchImgs.setLayoutManager(new WrappingLinearLayoutManager(getContext()));

                    exchImgs.removeAllViews();

                    DisplayImageOptions options = ((App) getAppContext()).getDisplayOptions();
                    for (int i = 0; i < imgs.size(); i++) {
                        ImageView img = new ImageView(getContext());
                        img.setScaleType(ImageView.ScaleType.FIT_START);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(0, DensityUtil.dip2px(getContext(), 1), 0, 0);
                        img.setLayoutParams(lp);
                        exchImgs.addView(img);
                        ImageLoader.getInstance().displayImage(imgs.get(i), img, options);

                    }


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getMainAdapter().notifyDataSetChanged();
                        }
                    }, 300);
                }
            }

            @Override
            public void onResponseError(String error) {

            }
        });


        NetRequest.loadExchangeComment(getContext(), id, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray
                            .parseArray(response.toJSONString(),
                                    ExchangeReply.class));
                }
                getMainView().onRefreshComplete();
                getMainAdapter().notifyDataSetChanged();
//                loadFinish("目前没有评论,赶快评论吧~");
            }

            @Override
            public void onResponseError(String error) {
                loadError();
            }
        });


    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_exchange_detail;
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_exchange_detail;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData();
    }

    class ExchangeAdapter extends WanAdapter<ExchangeReply> {

        public ExchangeAdapter(Context context, List<ExchangeReply> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, final int position, final ExchangeReply item) {
            SimpleDraweeView icon = holder.getView(R.id.exchItem_icon);
            TextView name = holder.getView(R.id.exchItem_name);
            TextView floor = holder.getView(R.id.exchItem_floor);
            TextView replyName = holder.getView(R.id.exchItem_replyName);
            TextView replyText = holder.getView(R.id.exchItem_replyText);
            TextView text = holder.getView(R.id.exchItem_text);
            TextView time = holder.getView(R.id.exchItem_time);
            LinearLayout replyView = holder.getView(R.id.exchItem_replyView);

            if (!TextUtils.isEmpty(item.getUser_file())) {
                icon.setImageURI(Uri.parse(item.getUser_file()));
            }
            name.setText(item.getCreate_nm());
            floor.setText(position + 1 + "楼");
            time.setText(item.getCreate_tm_str());

            text.setText(item.getDri_text());

            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIManager.startReply(ExchangeDetailActivity.this, 2, item.getDid(), position);
                }
            });

            replyView.removeAllViews();

            if(item.getList()!=null && item.getList().size()>0) {
               List<ExchangeReply> list= item.getList();
                for (int i = 0; i <list.size(); i++) {
                    ExchangeReply reply=list.get(i);
                  View view=  getLayoutInflater().inflate(R.layout.item_exchange_reply,null);
                  TextView nameView= (TextView) view.findViewById(R.id.exchItem_replyName);
                  TextView textView= (TextView) view.findViewById(R.id.exchItem_replyText);

                    nameView.setText(reply.getCreate_nm());
                    textView.setText(reply.getDri_text());
                    replyView.addView(view);
                }
            }

//            switch (item.getDri_reply_type()) {
//                case "1":
//                    //对文章回复
//                    replyView.setVisibility(View.GONE);
//
//
//                    break;
//                case "2":
//                    //对评论回复
//                    replyView.setVisibility(View.GONE);
//                    for (ExchangeReply reply : data) {
//                        if (reply.getId() == item.getDri_reply_id()) {
//                            replyView.setVisibility(View.VISIBLE);
//                            replyText.setText(reply.getDri_text());
//                            replyName.setText(reply.getCreate_nm());
//                            break;
//                        }
//                    }
//
//                    holder.getConvertView().setOnClickListener(null);
//                    break;
//                case "0":
//                    replyView.setVisibility(View.GONE);
//                    break;
//            }


        }

    }

}



