package com.qingsongjia.qingsongjia.driverexam;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
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

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("模拟考试");
        analogyIcon.setImageURI(Uri.parse("res:// /" + R.drawable.icon_default_head));
        analogyName.setText("好运到");
        analogySchool.setText("(东方时尚驾校)");
        analogyHint.setText("您可以登录学员中心更新个人资料哦");

        analogySubject.setText("教练员考试");
        analogyCarType.setText("手动档");
        analogyTandard.setText("100题 45分钟");
        analogyQualified.setText("满分100分，80分及格");

        analogyAnalogy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //模拟考试
                UIManager.startAnalogyTest(getContext());

            }
        });

        analogyImquiryExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //预约考试
                UIManager.startInquiryExam(getContext(), InquiryExamActivity.INQUIRY_TYPE_ONE);
            }
        });


    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_analogy_exam;
    }

}
