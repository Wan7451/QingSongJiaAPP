package com.qingsongjia.qingsongjia.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.MainActivity;
import com.qingsongjia.qingsongjia.adapter.ItemClickDataAdapter;
import com.qingsongjia.qingsongjia.bean.ItemClickData;
import com.qingsongjia.qingsongjia.bean.OnMenuItemClick;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TeacherMenuFragment extends Fragment implements WanAdapter.OnItemClickListener {

    @Bind(R.id.menu_user_icon)
    SimpleDraweeView menuUserIcon;
    @Bind(R.id.menu_user_name)
    TextView menuUserName;
    @Bind(R.id.menu_user_center)
    RecyclerView menuUserCenter;
    private OnMenuItemClick l;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_menu, container, false);
        ButterKnife.bind(this, view);


        ArrayList<ItemClickData> datas = new ArrayList<>();
        datas.add(new ItemClickData(R.drawable.icon_menu_header, "我的学员", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_xyks, "学员考试", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_xyyl, "学员约练", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_plxc, "陪练行程", "", true));
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
            case 0:
                UIManager.startMyStudents(getContext());
                break;
            case 1:
                UIManager.startMyStudentYK(getContext());
                break;
            case 2:
                UIManager.startMyStudentYX(getContext());
                break;
            case 3:
                UIManager.startMyPeiLianXingCheng(getContext());
                break;
            case 4:
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

}
