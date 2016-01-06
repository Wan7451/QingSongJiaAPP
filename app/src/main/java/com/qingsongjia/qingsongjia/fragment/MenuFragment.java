package com.qingsongjia.qingsongjia.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.activity.MainActivity;
import com.qingsongjia.qingsongjia.adapter.ItemClickDataAdapter;
import com.qingsongjia.qingsongjia.bean.ItemClickData;
import com.qingsongjia.qingsongjia.bean.OnMenuItemClick;
import com.qingsongjia.qingsongjia.bean.TiKu;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.bean.UserData;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import de.greenrobot.event.EventBus;


public class MenuFragment extends Fragment implements WanAdapter.OnItemClickListener {

    @Bind(R.id.menu_user_icon)
    SimpleDraweeView menuUserIcon;
    @Bind(R.id.menu_user_name)
    TextView menuUserName;
    @Bind(R.id.menu_user_design)
    TextView menuUserDesign;
    @Bind(R.id.menu_user_center)
    RecyclerView menuUserCenter;
    @Bind(R.id.userData)
    LinearLayout userData;
    private OnMenuItemClick l;


    ArrayList<ItemClickData> datas;//展示的数据
    ItemClickDataAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        datas = new ArrayList<>();
        datas.add(new ItemClickData(R.drawable.icon_menu_school, "我的驾校", "还没有选择驾校", true));

        TiKu tiKu = LocalPreference.getCurrentTiKu(getContext());
        datas.add(new ItemClickData(R.drawable.icon_menu_exam, "我的题库", tiKu.getName(), true));
        datas.add(new ItemClickData(R.drawable.icon_menu_header, "我的教练", "还没有选择教练", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_wallet, "我的钱包", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_test, "我的练习", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_exam, "我的考试", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_plxc, "我的陪练行程", "", true));
        datas.add(new ItemClickData(R.drawable.icon_menu_setting, "设置", "", true));


        loadData();

        adapter = new ItemClickDataAdapter(getContext(), datas, R.layout.item_icontext_arrows);
        menuUserCenter.setAdapter(adapter);
        menuUserCenter.setLayoutManager(new LinearLayoutManager(getContext()));
        menuUserCenter.addItemDecoration(new WanItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(this);

        userData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = LocalPreference.getCurrentUser(getContext());
                if (TextUtils.isEmpty(user.getDri_type())) {
                    UIManager.startLogin(getContext());
                    return;
                }
                UIManager.startStudentData(getContext());
            }
        });
        return view;
    }


    private void loadData() {

        User user = LocalPreference.getCurrentUser(getContext());
        if (!TextUtils.isEmpty(user.getDri_type()))
            NetRequest.loadNewMyData(getContext(), user.getDri_unm(), new NetUtils.NetUtilsHandler() {
                @Override
                public void onResponseOK(JSONArray response, int total) {
                    String data = response.getString(0);

                    LocalPreference.saveCurrentUserData(getContext(), data);

                    UserData loginData = JSONObject.parseObject(data, UserData.class);

                    JPushInterface.setAlias(getContext(), loginData.getDri_tel(), new TagAliasCallback() {
                        @Override
                        public void gotResult(int i, String s, Set<String> set) {
                        }
                    });

                    fillData(loginData);
                }

                @Override
                public void onResponseError(String error) {

                }
            });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    /**
     * 使用onEventMainThread来接收事件，那么不论分发事件在哪个线程运行，接收事件永远在UI线程执行，
     * 这对于android应用是非常有意义的
     */
    public void onEventMainThread(EventData data) {
        if (data.getType() == EventData.TYPE_REFRESH_MENU) {
            loadData();
        }
    }


    private void fillData(UserData loginData) {
        if (menuUserName != null)
            if (!TextUtils.isEmpty(loginData.getDri_nm())) {
                menuUserName.setText(loginData.getDri_nm());
            } else {
                menuUserName.setText("嘟嘟驾道");
            }

        if (!TextUtils.isEmpty(loginData.getDri_campus_nm())) {
            datas.get(0).setSecondText(loginData.getDri_campus_nm());
        }
        String teacherName = loginData.getDri_coach_nm();
        if (!TextUtils.isEmpty(teacherName)) {
            datas.get(2).setSecondText(teacherName);
        }
        if (menuUserIcon != null) {
            String iconPath = loginData.getDri_file_path();
            if (TextUtils.isEmpty(iconPath)) {
                menuUserIcon.setImageURI(Uri.parse("http://7xlt5l.com1.z0.glb.clouddn.com/1449734769519ohgmmj3048628"));
            } else {
                menuUserIcon.setImageURI(Uri.parse(iconPath));
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        if (l != null) {
            l.onItemClick(posotion);
        }

        User user = LocalPreference.getCurrentUser(getContext());
        if (TextUtils.isEmpty(user.getDri_type())) {
            UIManager.startLogin(getContext());
            return;
        }

        switch (posotion) {
            case 0:
                if(0==user.getDri_campus_id()){
                Toast.makeText(getContext(),"暂时没有报名驾校，赶快报名吧~",Toast.LENGTH_SHORT).show();
                    return;
                }
                UIManager.startSchoolDetail(getContext(), user.getDri_campus_id(),-1);
                break;
            case 1:
                UIManager.startChangeExamLibsForReslut(this, 1234);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            datas.get(1).setSecondText(LocalPreference.getCurrentTiKu(getContext()).getName());
            adapter.notifyDataSetChanged();
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
