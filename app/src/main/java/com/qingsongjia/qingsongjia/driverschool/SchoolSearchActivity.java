package com.qingsongjia.qingsongjia.driverschool;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.SchoolListAdapter;
import com.qingsongjia.qingsongjia.bean.School;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanRecycleView;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by wanggang on 15/11/17.
 */
public class SchoolSearchActivity extends Activity implements PullToRefreshBase.OnRefreshListener, WanAdapter.OnItemClickListener {

    @Bind(R.id.search_view)
    WanRecycleView searchView;
    @Bind(R.id.search_back)
    ImageView searchBack;
    @Bind(R.id.search_content)
    EditText searchContent;
    @Bind(R.id.search_submit)
    TextView searchSubmit;
    private String search;
    ArrayList<School> data = new ArrayList<>();
    private SchoolListAdapter adapter;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_search);
        ButterKnife.bind(this);
        initSystemBar();

        searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    // 在这里编写自己想要实现的功能
                    search(textView);
                }
                return false;
            }
        });

        searchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(view);
            }
        });


        searchView.setOnRefreshListener(this);

        adapter = new SchoolListAdapter(this, data, R.layout.item_school_list);
        searchView.getRefreshableView().setAdapter(adapter);
        searchView.getRefreshableView().setLayoutManager(new LinearLayoutManager(this));
        searchView.getRefreshableView().addItemDecoration(new WanItemDecoration(this,WanItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(this);
    }

    private void search(View v) {
        // 在这里编写自己想要实现的功能
        search = searchContent.getText().toString();
        if (imm == null)
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                searchView.setRefreshing();
            }
        }, 300);
    }


    protected void initSystemBar() {
        initSystemBar(getResources().getColor(com.wan7451.wanadapter.mylibrary.R.color.title_bar_color));
    }

    private SystemBarTintManager.SystemBarConfig config;


    public void initSystemBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setTintColor(color);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(color);
            View v = findViewById(android.R.id.text1);
            if (v != null && config == null) {
                config = tintManager.getConfig();
                v.setPadding(0, config.getPixelInsetTop(true), 0, 0);
            }
        }
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        loadData();
    }

    private void loadData() {
        NetRequest.searchSchoolList(this, null,search, new NetUtils.NetUtilsHandler() {
            @Override
            public void onResponseOK(JSONArray response, int total) {
                data.clear();
                if (!TextUtils.equals("[{}]", response.toJSONString())) {
                    data.addAll(JSONArray.parseArray(response.toJSONString(), School.class));
                }
                if (data.size() == 0) {
                    Toast.makeText(SchoolSearchActivity.this, "没有搜索到数据", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
                searchView.onRefreshComplete();
            }

            @Override
            public void onResponseError(String error) {

            }
        });
    }

    @Override
    public void onItemClickListener(int posotion, WanViewHolder holder) {
        UIManager.startSchoolDetail(this, data.get(posotion).getDri_campus_id(),-1);

    }
}
