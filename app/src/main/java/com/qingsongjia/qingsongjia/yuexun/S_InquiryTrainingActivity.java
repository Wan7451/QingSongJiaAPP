package com.qingsongjia.qingsongjia.yuexun;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.bean.Teacher;
import com.qingsongjia.qingsongjia.bean.TeacherDetail;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.EventData;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
import com.wan7451.base.WanActivity;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 预约训练
 */
public class S_InquiryTrainingActivity extends WanActivity {


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

    private String time;
    private int teacherId;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("约练");

        if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
            UIManager.startLogin(getContext());
            finish();
            return;
        }

        trainingChoiceTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                trainingTime.setText(year + "年" + (month + 1) + "月" + day + "日");
                                time = year + "-" + (month + 1) + "-" + day;
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
                if (TextUtils.isEmpty(time)) {
                    showToast("请首先选择时间");
                    return;
                }
                UIManager.startTeacherList(getContext(), time);
            }
        });

        trainingSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (teacherId == 0) {
                    showToast("请先选择时间与教练");
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

                NetRequest.sendInuiryTraining(getContext(), teacherId + "", new NetUtils.NetUtilsHandler() {
                    @Override
                    public void onResponseOK(JSONArray response, int total) {
                        showToast("预约成功");
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


    public void onEventMainThread(EventData data) {
        if (data.getType() == EventData.TYPE_YUELIAN) {
            TeacherDetail detail = (TeacherDetail) data.getData();
            trainingTeacher.setText(detail.getDri_coach_nm());
            trainingMytime.setText(time);
            trainingMyteacher.setText(detail.getDri_plate_num());
            teacherId = detail.getId();
        }
    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_inquiry_training;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
