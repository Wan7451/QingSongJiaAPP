package com.qingsongjia.qingsongjia.yuexun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.MyYueKao;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 约训详情
 */
public class S_TrainingDetailActivity extends WanActivity {


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
    private MyYueKao data;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("练习详情");

        data = getIntent().getParcelableExtra("data");

        String t = data.getDri_dt_str()+ " " + data.getDri_start_hm() + "-" + data.getDri_end_hm();
        time.setText(t);

        kemu.setText(data.getDri_sub_nm());

        String state = data.getDri_state_nm();
        status.setText(state);

        pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //学生评论练习
                UIManager.startStudentYXEval(getContext(),data);
            }
        });
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_my_test_detail;
    }

}
