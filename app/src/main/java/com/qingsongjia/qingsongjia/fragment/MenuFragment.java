package com.qingsongjia.qingsongjia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.MainActivity;
import com.qingsongjia.qingsongjia.adapter.ItemClickDataAdapter;
import com.qingsongjia.qingsongjia.bean.ItemClickData;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.advancedview.CircleImageView;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MenuFragment extends Fragment implements WanAdapter.OnItemClickListener {

    @Bind(R.id.menu_user_icon)
    SimpleDraweeView menuUserIcon;
    @Bind(R.id.menu_user_name)
    TextView menuUserName;
    @Bind(R.id.menu_user_design)
    TextView menuUserDesign;
    @Bind(R.id.menu_user_center)
    RecyclerView menuUserCenter;
    private OnMenuItemClick l;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);


        ArrayList<ItemClickData> datas = new ArrayList<>();
        datas.add(new ItemClickData(R.drawable.icon_menu_school, "我的驾校", "XX学校", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_exam, "我的题库", "小车", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_header, "我的教练", "张教练", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_wallet, "我的钱包", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_test, "我的练习", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_exam, "我的考试", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_plxc, "我的陪练行程", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_setting, "设置", "", true));


        ItemClickDataAdapter adapter = new ItemClickDataAdapter(getContext(), datas, R.layout.item_icontext_arrows);
        menuUserCenter.setAdapter(adapter);
        menuUserCenter.setLayoutManager(new LinearLayoutManager(getContext()));
        menuUserCenter.addItemDecoration(new WanItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        if (l != null) {
            l.onItemClick(posotion);
        }
        switch (posotion) {
            case 1:
                UIManager.startChangeExamLibs(getContext());
                break;
            case 2:
                UIManager.startMyTeacher(getContext());
                break;
            case 3:
                UIManager.startMyMessage(getContext());
                break;
            case 4:
                UIManager.startMyTest(getContext());
                break;
            case 5:
                UIManager.startMyExam(getContext());
                break;
            case 6:
                UIManager.startPenLian(getContext());
                break;
            case 7:
                UIManager.startSetting(getContext());
                break;

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            l = (OnMenuItemClick) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        l = null;
    }

    public interface OnMenuItemClick {
        void onItemClick(int position);
    }
}
