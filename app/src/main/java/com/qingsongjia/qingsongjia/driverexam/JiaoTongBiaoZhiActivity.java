package com.qingsongjia.qingsongjia.driverexam;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.JiaoTongBiaoZhi;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanListActivity;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 交通标志
 */
public class JiaoTongBiaoZhiActivity extends WanListActivity {


    private String rootPath = "html/kemu1/jtbz";

    private ArrayList<JiaoTongBiaoZhi> data = new ArrayList<>();
    //标题标志
    String[] jtbz = {
            "asset:///html/kemu1/jtbz/jtbzdq/jlbz/83.jpg",
            "asset:///html/kemu1/jtbz/jtbzdq/jgbz/5eb4d75agw1e290jbi4otj.png",
            "asset:///html/kemu1/jtbz/jtbzdq/zsbz/5eb4d75agw1e290klgafrj.png",
            "asset:///html/kemu1/jtbz/jtbzdq/lyqbz/151.jpg",
    };

    //汽车仪表
    String[] qcyb = {
            "asset:///html/kemu1/jtbz/ybzsq/7.jpg",
            "asset:///html/kemu1/jtbz/ybzsq/8.jpg",
            "asset:///html/kemu1/jtbz/ybzsq/9.jpg",
            "asset:///html/kemu1/jtbz/ybzsq/10.jpg",
    };

    //交警手势
    String[] jjss = {
            "asset:///html/kemu1/jtbz/jjss/tupian/466.jpg",
            "asset:///html/kemu1/jtbz/jjss/tupian/471.jpg",
            "asset:///html/kemu1/jtbz/jjss/tupian/468.jpg",
            "asset:///html/kemu1/jtbz/jjss/tupian/469.jpg",
    };

    //车内功能按键
    String[] cngn = {
            "asset:///html/kemu1/jtbz/cngnaj/1.jpg",
            "asset:///html/kemu1/jtbz/cngnaj/2.jpg",
            "asset:///html/kemu1/jtbz/cngnaj/3.jpg",
            "asset:///html/kemu1/jtbz/cngnaj/4.jpg",
    };

    //责任事故认定
    String[] zrsg = {
            "asset:///html/kemu1/jtbz/zrrdtj/485.jpg",
            "asset:///html/kemu1/jtbz/zrrdtj/486.jpg",
            "asset:///html/kemu1/jtbz/zrrdtj/487.jpg",
            "asset:///html/kemu1/jtbz/zrrdtj/488.jpg",
    };

    //色盲测试
    String[] smcs = {
            "asset:///html/kemu1/jtbz/smcstj/28.jpg",
            "asset:///html/kemu1/jtbz/smcstj/29.jpg",
            "asset:///html/kemu1/jtbz/smcstj/30.jpg",
            "asset:///html/kemu1/jtbz/smcstj/31.jpg",
    };

    //路径
    String[] path = {"jtbzdq","ybzsq",
                     "jjss", "cngnaj",
                     "zrrdtj","smcstj" };

    @Override
    public void initView() {
        super.initView();
        setContentTitle("交通标志");
        setBackFinish();
        setIsShowLocalData();
    }

    @Override
    protected int getMainViewLayoutId() {
        return 0;
    }

    @Override
    protected boolean addData() {
        return false;
    }

    @Override
    public WanAdapter getAdapter() {
        JiaoTongFaGuiAdapter adapter = new JiaoTongFaGuiAdapter(getContext(), data, R.layout.item_jtfg_list);
        return adapter;
    }

    @Override
    protected void loadData() {
        data.clear();
        data.add(new JiaoTongBiaoZhi("交通标志大全", 389, jtbz));
        data.add(new JiaoTongBiaoZhi("仪表指示灯", 21, qcyb));
        data.add(new JiaoTongBiaoZhi("交警手势", 8, jjss));
        data.add(new JiaoTongBiaoZhi("车内功能按钮", 6, cngn));
        data.add(new JiaoTongBiaoZhi("交通事故责任认定图解", 35, zrsg));
        data.add(new JiaoTongBiaoZhi("色盲测试图集", 19, smcs));
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {

        switch (posotion) {
            case 0:
                UIManager.startJiaoTongBiaoZhiList(getContext());
                break;
            case 2:
                UIManager.startWebList(getContext());
                break;
            case 1:
            case 3:
            case 4:
            case 5:
                UIManager.startJiaoTongBiaoZhiGrid(getContext(),data.get(posotion).getTitle(), rootPath + "/" + path[posotion]);
                break;
        }
    }


    static class JiaoTongFaGuiAdapter extends WanAdapter<JiaoTongBiaoZhi> {


        public JiaoTongFaGuiAdapter(Context context, List<JiaoTongBiaoZhi> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, JiaoTongBiaoZhi item) {
            RecyclerView icons = holder.getView(R.id.jtfg_icons);
            JTFGIconsAdapter adapter = new JTFGIconsAdapter(getContext(), Arrays.asList(item.getIcons()), R.layout.item_jtfg_bz);
            icons.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            icons.setLayoutManager(manager);
            icons.setClickable(false);

            holder.setText(R.id.jtfg_name, item.getTitle());
            holder.setText(R.id.jtfg_count, item.getCount() + "张");


        }
    }

    static class JTFGIconsAdapter extends WanAdapter<String> {

        public JTFGIconsAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, String item) {
            SimpleDraweeView icon = holder.getView(R.id.jtfg_icon);
            icon.setImageURI(Uri.parse(item));
        }
    }
}
