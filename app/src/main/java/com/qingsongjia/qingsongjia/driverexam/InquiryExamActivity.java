package com.qingsongjia.qingsongjia.driverexam;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 预约考试
 */
public class InquiryExamActivity extends WanActivity {

    public static final int INQUIRY_TYPE_ONE = 1;
    public static final int INQUIRY_TYPE_TWO = 2;
    public static final int INQUIRY_TYPE_THREE = 3;
    public static final int INQUIRY_TYPE_FOUR = 4;
    @Bind(R.id.inquiry_project)
    TextView inquiryProject;
    @Bind(R.id.inquiry_time)
    TextView inquiryTime;
    @Bind(R.id.inquiry_choiceTime)
    LinearLayout inquiryChoiceTime;
    @Bind(R.id.inquiry_send)
    Button inquirySend;


    @Override
    public void initView() {
        int type = getIntent().getIntExtra("type", -1);
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("预约考试");

        switch (type) {
            case INQUIRY_TYPE_ONE:
                inquiryProject.setText("科目一");
                break;
            case INQUIRY_TYPE_TWO:
                inquiryProject.setText("科目二");
                break;
            case INQUIRY_TYPE_THREE:
                inquiryProject.setText("科目三");
                break;
            case INQUIRY_TYPE_FOUR:
                inquiryProject.setText("科目四");
                break;
        }

        inquiryChoiceTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                inquiryTime.setText(year+"年"+month+"月"+day+"日");
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                DatePicker datePicker = dialog.getDatePicker();

                datePicker.setMinDate(Calendar.getInstance().getTimeInMillis()-40*60*1000);

                dialog.show();
            }
        });

        inquirySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("预约成功");
            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_inquiry_exam;
    }


}
