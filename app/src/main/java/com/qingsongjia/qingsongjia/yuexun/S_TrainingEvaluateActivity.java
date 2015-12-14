package com.qingsongjia.qingsongjia.yuexun;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 学员练习评价
 */
public class S_TrainingEvaluateActivity extends WanActivity {


    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.kemu)
    TextView kemu;
    @Bind(R.id.zhishidian)
    TextView zhishidian;
    @Bind(R.id.status)
    TextView status;
    @Bind(R.id.pingjia)
    Button pingjia;
    @Bind(R.id.content)
    EditText content;
    private MyYueKao data;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("练习评论");

        data = getIntent().getParcelableExtra("data");


        String t = data.getDri_dt_str()+ " " + data.getDri_start_hm() + "-" + data.getDri_end_hm();
        time.setText(t);

        kemu.setText(data.getDri_sub_nm());

        String state = data.getDri_state_nm();
        status.setText(state);

        pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mark = content.getText().toString();
                if (TextUtils.isEmpty(mark)) {
                    showToast("评价内容不能为空");
                    return;
                }
                NetRequest.markStudent(getContext(),data.getId()+"",mark, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("评价成功");
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("评价失败");
                        } else {
                            showToast(error);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_yxevaluate2;
    }

}
