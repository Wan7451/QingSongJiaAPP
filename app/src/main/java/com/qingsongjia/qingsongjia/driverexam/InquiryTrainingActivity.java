package com.qingsongjia.qingsongjia.driverexam;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InquiryTrainingActivity extends WanActivity {


    @Bind(R.id.training_time)
    TextView trainingTime;   //选择的时间
    @Bind(R.id.training_choiceTime)
    LinearLayout trainingChoiceTime;   //按钮
    @Bind(R.id.training_teacher)
    TextView trainingTeacher;    //选择教练
    @Bind(R.id.training_choiceTeacher)
    LinearLayout trainingChoiceTeacher;  //按钮
    @Bind(R.id.training_mytime)
    TextView trainingMytime;   //选好的时间
    @Bind(R.id.training_myteacher)
    TextView trainingMyteacher;  //选好的教练
    @Bind(R.id.training_send)
    Button trainingSend;  //预约

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("约练");

        trainingChoiceTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                trainingTime.setText(year + "年" + month + "月" + day + "日");
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


        trainingChoiceTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startTeacherList(getContext());
            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_inquiry_training;
    }

}
