package com.qingsongjia.qingsongjia.plxc;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.wan7451.base.WanActivity;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 发布陪练
 */
public class T_PushSparringActivity extends WanActivity {


    @Bind(R.id.training_time)
    TextView trainingTime;
    @Bind(R.id.training_choiceTime)
    LinearLayout trainingChoiceTime;
    @Bind(R.id.training_price)
    EditText trainingPrice;
    @Bind(R.id.training_typeA)
    CheckBox trainingTypeA;
    @Bind(R.id.training_typeAView)
    LinearLayout trainingTypeAView;
    @Bind(R.id.training_typeB)
    CheckBox trainingTypeB;
    @Bind(R.id.training_typeBView)
    LinearLayout trainingTypeBView;
    @Bind(R.id.training_other)
    EditText trainingOther;
    @Bind(R.id.training_send)
    Button trainingSend;

    Calendar calendar = Calendar.getInstance();

    private String upDate;
    private String upTime;
    private String tempDate;

    private int datacount;
    private int timecount;


    private int type;//1:定点上车 2 车接车送

    @Override
    public void initView() {
        setContentTitle("发布订单");
        setBackFinish();
        ButterKnife.bind(this);


        trainingTypeA.setChecked(false);
        trainingTypeB.setChecked(false);
        trainingTypeA.setClickable(false);
        trainingTypeB.setClickable(false);

        trainingChoiceTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showChoiceDialog();
            }
        });

        trainingTypeAView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainingTypeA.setChecked(true);
                trainingTypeB.setChecked(false);
                type = 1;
            }
        });

        trainingTypeBView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainingTypeA.setChecked(false);
                trainingTypeB.setChecked(true);
                type = 2;
            }
        });


        trainingSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String other = trainingOther.getText().toString();
                String price = trainingPrice.getText().toString();

                if (type == 0) {
                    showToast("请选择上车方式");
                    return;
                }

                NetRequest.pushOrder(getContext(), upDate,upTime,
                        price, type + "", other, new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("发布成功");
                        finish();
                    }

                    @Override
                    public void onResponseError(String error) {
                        if (TextUtils.isEmpty(error)) {
                            showToast("发布失败");
                        } else {
                            showToast(error);
                        }
                    }
                });
            }
        });
    }

    private void showChoiceDialog() {
        DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        upDate = year + "-" + (month+1) + "-" + day;
                        tempDate = year + "年" + (month+1) + "月" + day + "日";

                        datacount++;

                        if (datacount == 2) {
                            TimePickerDialog timeDialog = new TimePickerDialog(
                                    getContext(),
                                    new TimePickerDialog.OnTimeSetListener() {
                                        @Override
                                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                            timecount++;
                                            if (timecount == 2) {
                                                tempDate += i + "时";
                                                upTime=i+"";
                                                trainingTime.setText(tempDate);
                                            }
                                        }
                                    },
                                    calendar.get(Calendar.HOUR_OF_DAY),
                                    calendar.get(Calendar.MINUTE),
                                    true
                            );
                            timeDialog.setTitle("选择时间");
                            timeDialog.show();
                            datacount = 0;
                            timecount = 0;
                        }
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMinDate(Calendar.getInstance().getTimeInMillis() - 40 * 60 * 1000);
        dialog.show();
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_push_order;
    }

}
