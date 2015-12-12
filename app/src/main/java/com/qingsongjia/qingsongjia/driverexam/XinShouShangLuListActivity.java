package com.qingsongjia.qingsongjia.driverexam;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ItemClickDataAdapter;
import com.qingsongjia.qingsongjia.bean.ItemClickData;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

public class XinShouShangLuListActivity extends WanListActivity {

    private String rootPath = "file:///android_asset/html/kemu1/xssl/";

    ArrayList<ItemClickData> datas = new ArrayList<>();//展示的数据

    String[] data1 = {"车辆基本操作",
            "隧道行驶注意事项",
            "特殊天气驾驶技巧",
            "夜间行驶技巧",
            "刹车技巧"};
    String[] dataPath1 = {"cljbcz", "scjq", "sdxszysx",
            "tstqjsjq", "yjxsjq"};


    String[] data2 = {"基本停车",
            "垂直式停车位",
            "侧方停车位",
            "斜线停车位",
            "串联式停车",
            "并联式停车",
            "地下车库停车位",
            "非字形车位"};
    String[] dataPath2 = {"jbtc", "czstc", "cfwtc",
            "xxtcw", "clstc", "blstc", "dxcktcw", "fzxcw"};
    private int type;

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public void initView() {
        super.initView();
        setBackFinish();
        setContentTitle("新手上路");

        //1 驾车技巧     1 停车技巧
        type = getIntent().getIntExtra("type", 0);

    }

    @Override
    public WanAdapter getAdapter() {
        return new ItemClickDataAdapter(getContext(), datas, R.layout.item_icontext_arrows);
    }

    @Override
    protected void loadData() {
        datas.clear();
        switch (type) {
            case 1:
                datas.add(new ItemClickData(R.drawable.icon_1, data1[0], "", true));
                datas.add(new ItemClickData(R.drawable.icon_2, data1[1], "", true));
                datas.add(new ItemClickData(R.drawable.icon_3, data1[2], "", true));
                datas.add(new ItemClickData(R.drawable.icon_4, data1[3], "", true));
                datas.add(new ItemClickData(R.drawable.icon_5, data1[4], "", true));
                break;
            case 2:
                datas.add(new ItemClickData(R.drawable.icon_1, data2[0], "", true));
                datas.add(new ItemClickData(R.drawable.icon_2, data2[1], "", true));
                datas.add(new ItemClickData(R.drawable.icon_3, data2[2], "", true));
                datas.add(new ItemClickData(R.drawable.icon_4, data2[3], "", true));
                datas.add(new ItemClickData(R.drawable.icon_5, data2[4], "", true));
                datas.add(new ItemClickData(R.drawable.icon_5, data2[5], "", true));
                datas.add(new ItemClickData(R.drawable.icon_5, data2[6], "", true));
                datas.add(new ItemClickData(R.drawable.icon_5, data2[7], "", true));
                break;
        }

        loadFinish("");
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

        String p = "";

        switch (type) {
            case 1:
                p = "jcjq/";
                UIManager.startWebView(getContext(), data1[posotion], rootPath+ p+ dataPath1[posotion] + ".html");
                break;
            case 2:
                p = "tcjq/";
                UIManager.startWebView(getContext(), data2[posotion], rootPath+ p+ dataPath2[posotion] + ".html");
                break;
        }

    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }
}
