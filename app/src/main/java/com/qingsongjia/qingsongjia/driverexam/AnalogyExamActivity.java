package com.qingsongjia.qingsongjia.driverexam;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.User;
import com.qingsongjia.qingsongjia.bean.UserData;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 模拟考试入口
 */
public class AnalogyExamActivity extends WanActivity {


    @Bind(R.id.analogy_icon)
    SimpleDraweeView analogyIcon;  //用户头像
    @Bind(R.id.analogy_name)
    TextView analogyName;  //用户名称
    @Bind(R.id.analogy_school)
    TextView analogySchool;  //用户所在驾校
    @Bind(R.id.analogy_hint)
    TextView analogyHint;     //对用户的提示
    @Bind(R.id.analogy_subject)
    TextView analogySubject;  //考试科目
    @Bind(R.id.analogy_carType)
    TextView analogyCarType;  //车辆类型
    @Bind(R.id.analogy_tandard)
    TextView analogyTandard;   //考试标准
    @Bind(R.id.analogy_qualified)
    TextView analogyQualified;  //合格标准
    @Bind(R.id.analogy_analogy)
    Button analogyAnalogy;   //模拟考试
    @Bind(R.id.analogy_imquiryExam)
    Button analogyImquiryExam;   //预约报名
    @Bind(R.id.analogy_divier)
    View analogyDivier;
    private int type;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("模拟考试");


        type = getIntent().getIntExtra("type", 1);

        UserData user = LocalPreference.getCurrentUserData(getContext());
        if (!TextUtils.isEmpty(user.getDri_file_path())) {
            analogyIcon.setImageURI(Uri.parse(user.getDri_file_path()));
        }
        if (!TextUtils.isEmpty(user.getDri_nm()))
            analogyName.setText(user.getDri_nm());
        if (!TextUtils.isEmpty(user.getDri_campus_nm()))
            analogySchool.setText("(" + user.getDri_campus_nm() + ")");
        analogyHint.setText("您可以登录学员中心更新个人资料哦");

        analogySubject.setText(LocalPreference.getCurrentKemu(getContext()).getName());
        analogyCarType.setText(LocalPreference.getCurrentTiKu(getContext()).getName());
        analogyTandard.setText("100题 45分钟");
        analogyQualified.setText("满分100分，80分及格");


        analogyAnalogy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //模拟考试
                UIManager.startAnalogyTest(getContext(), type);

            }
        });

        analogyImquiryExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //预约考试
                UIManager.startInquiryExam(getContext(), type);
            }
        });

        User u = LocalPreference.getCurrentUser(getContext());
        int type = 0;
        if (!TextUtils.isEmpty(u.getDri_type()) && (
                !u.getDri_type().endsWith("0"))) {
            type = 1;
        }

        if (type == 1) {
            //教练
            analogyDivier.setVisibility(View.GONE);
            analogyImquiryExam.setVisibility(View.GONE);
        }


    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_analogy_exam;
    }

}
