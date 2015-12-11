package com.qingsongjia.qingsongjia.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.KeMu;
import com.qingsongjia.qingsongjia.bean.OnMenuItemClick;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.fragment.ExamFragment;
import com.qingsongjia.qingsongjia.fragment.ExchangeFragment;
import com.qingsongjia.qingsongjia.fragment.JiaoLianToolsFragment;
import com.qingsongjia.qingsongjia.fragment.MenuFragment;
import com.qingsongjia.qingsongjia.fragment.SchoolFragment;
import com.qingsongjia.qingsongjia.fragment.TeacherMenuFragment;
import com.qingsongjia.qingsongjia.fragment.ToolsFragment;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, OnMenuItemClick {

    @Bind(R.id.main_img_drawerIcon)
    ImageView mainImgDrawerIcon;
    @Bind(R.id.main_tv_title)
    TextView mainTvTitle;
    @Bind(R.id.main_realtabcontent)
    FrameLayout mainRealtabcontent;
    @Bind(R.id.main_tabHost)
    FragmentTabHost mainTabHost;
    @Bind(R.id.main_drawerLayout)
    DrawerLayout mainDrawerLayout;
    @Bind(R.id.main_mainView)
    LinearLayout mainMainView;
    @Bind(R.id.main_tab1)
    RadioButton mainTab1;
    @Bind(R.id.main_tab2)
    RadioButton mainTab2;
    @Bind(R.id.main_tab3)
    RadioButton mainTab3;
    @Bind(R.id.main_tab4)
    RadioButton mainTab4;
    @Bind(R.id.main_navigation)
    RadioGroup mainNavigation;
    @Bind(R.id.main_img_search)
    ImageView mainImgSearch;

    @Bind(R.id.main_menu_left)
    FrameLayout mainMenuLeft;

    private boolean isUser = false;

    private SystemBarTintManager.SystemBarConfig config;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSystemBar(getResources().getColor(R.color.toolbar_bg));

        if (getApplication() instanceof App) {
            ((App) getApplication()).addAcitivty(this);
        }

        mainTvTitle.setText("轻松驾");


        mainTabHost.setup(this, getSupportFragmentManager(), R.id.main_realtabcontent);

        mainTabHost.addTab(mainTabHost.newTabSpec("1").setIndicator("A"), ExamFragment.class, null);
        mainTabHost.addTab(mainTabHost.newTabSpec("2").setIndicator("B"), SchoolFragment.class, null);
        mainTabHost.addTab(mainTabHost.newTabSpec("3").setIndicator("C"), ExchangeFragment.class, null);


        //dri_type 0 :学生 1 教练
        User u = LocalPreference.getCurrentUser(this);
        type = 0;
        if (!TextUtils.isEmpty(u.getDri_type()) && (
                !u.getDri_type().endsWith("0"))) {
            type = 1;
        }

        if (type == 0) {
            //学员
            mainTabHost.addTab(mainTabHost.newTabSpec("4").setIndicator("D"), ToolsFragment.class, null);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            MenuFragment useMenu = new MenuFragment();
            ft.replace(R.id.main_menu_left, useMenu).commit();

        } else {
            //教练
            mainTabHost.addTab(mainTabHost.newTabSpec("4").setIndicator("D"), JiaoLianToolsFragment.class, null);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            TeacherMenuFragment teacherMenu = new TeacherMenuFragment();
            ft.replace(R.id.main_menu_left, teacherMenu).commit();
        }


        mainTabHost.setCurrentTab(0);
        mainNavigation.setOnCheckedChangeListener(this);
        mainImgDrawerIcon.setOnClickListener(this);

        ((RadioButton) (mainNavigation.getChildAt(0))).setChecked(true);

        mainImgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startSchoolSearch(MainActivity.this);
            }
        });
    }

    public void initSystemBar(int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setTintColor(color);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(color);
            View v = findViewById(R.id.main_mainView);
            if (v != null && config == null) {
                config = tintManager.getConfig();
                v.setPadding(0, config.getPixelInsetTop(true), 0, 0);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.main_tab1:
                mainTabHost.setCurrentTab(0);
                LocalPreference.setCurrentKemu(this, KeMu.KEMU1);
                mainImgSearch.setVisibility(View.GONE);
                mainTvTitle.setText("轻松驾");
                break;
            case R.id.main_tab2:
                mainTabHost.setCurrentTab(1);
                mainTvTitle.setText("找驾校");
                mainImgSearch.setVisibility(View.VISIBLE);
                LocalPreference.setCurrentKemu(this, KeMu.KEMU2);
                break;
            case R.id.main_tab3:
                mainTabHost.setCurrentTab(2);
                LocalPreference.setCurrentKemu(this, KeMu.KEMU3);
                mainImgSearch.setVisibility(View.GONE);
                mainTvTitle.setText("交流中心");
                break;
            case R.id.main_tab4:
                mainTabHost.setCurrentTab(3);
                LocalPreference.setCurrentKemu(this, KeMu.KEMU4);
                mainImgSearch.setVisibility(View.GONE);
                mainTvTitle.setText("陪驾");
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if (mainDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mainDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            mainDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onItemClick(int position) {
        mainDrawerLayout.closeDrawer(Gravity.LEFT);
    }
}
