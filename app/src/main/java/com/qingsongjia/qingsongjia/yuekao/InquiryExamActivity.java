package com.qingsongjia.qingsongjia.yuekao;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import java.util.Calendar;

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
    @Bind(R.id.inquiry_date)
    TextView inquiryDate;
    @Bind(R.id.inquiry_choiceDate)
    LinearLayout inquiryChoiceDate;
    @Bind(R.id.inquiry_time)
    TextView inquiryTime;
    @Bind(R.id.inquiry_choiceTime)
    LinearLayout inquiryChoiceTime;
    @Bind(R.id.inquiry_send)
    Button inquirySend;

    String[] times = {"上午", "下午"};


    private String upDate;
    private String upTime = "1";
    private int upKemu;

    @Override
    public void initView() {
        int type = getIntent().getIntExtra("type", -1);
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("预约考试");

        if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
            UIManager.startLogin(getContext());
            finish();
            return;
        }

        switch (type) {
            case INQUIRY_TYPE_ONE:
                inquiryProject.setText("科目一");
                upKemu = 1;
                break;
            case INQUIRY_TYPE_TWO:
                inquiryProject.setText("科目二");
                upKemu = 2;
                break;
            case INQUIRY_TYPE_THREE:
                inquiryProject.setText("科目三");
                upKemu = 3;
                break;
            case INQUIRY_TYPE_FOUR:
                inquiryProject.setText("科目四");
                upKemu = 4;
                break;
        }

        inquiryTime.setText("上午");

        inquiryChoiceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                upDate = year + "-" + (month + 1) + "-" + day;
                                inquiryDate.setText(year + "年" + (month + 1) + "月" + day + "日");
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                DatePicker datePicker = dialog.getDatePicker();

                datePicker.setMinDate(Calendar.getInstance().getTimeInMillis() - 40 * 60 * 1000);

                dialog.show();
            }
        });

        inquiryChoiceTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setSingleChoiceItems(times, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                upTime = i + 1 + "";
                                inquiryTime.setText(times[i]);
                            }
                        })
                        .setTitle("选择时间")
                        .setPositiveButton("确认", null)
                        .show();
            }
        });

        inquirySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(upDate)) {
                    showToast("请选择约考时间");
                    return;
                }
                if (TextUtils.isEmpty(upTime)) {
                    showToast("请选择约考时间");
                    return;
                }

                int coachId = LocalPreference.getCurrentUserData(getContext()).getDri_coach_id();
                if (coachId == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("提示");
                    builder.setMessage("未分配教练，不能进行约考，请联系负责人员");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                    return;
                }

                NetRequest.yuekao(getContext(),view, upDate, upTime, upKemu + "", new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("预约成功");
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("预约失败");
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
        return R.layout.activity_inquiry_exam;
    }


}
