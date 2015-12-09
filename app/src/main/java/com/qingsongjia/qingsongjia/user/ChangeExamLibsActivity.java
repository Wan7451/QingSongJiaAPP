package com.qingsongjia.qingsongjia.user;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.adapter.ExamImageAdapter;
import com.qingsongjia.qingsongjia.adapter.ExamImageTextAdapter;
import com.qingsongjia.qingsongjia.bean.ExamImageText;
import com.qingsongjia.qingsongjia.bean.TiKu;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.wan7451.base.WanActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 改变题库
 */
public class ChangeExamLibsActivity extends WanActivity {


    @Bind(R.id.changeExam_jsz)
    GridView changeExamJsz;  //驾驶证
    @Bind(R.id.changeExam_zgz)
    GridView changeExamZgz;  //资格证

    private int tiku;
    private ExamImageAdapter adapter2;
    private ExamImageAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);

        setBackFinish();
        setContentTitle("却换题库");


        final ArrayList<ExamImageText> data = new ArrayList<>();
        data.add(new ExamImageText(R.drawable.icon_menu_xc, ""));
        data.add(new ExamImageText(R.drawable.icon_menu_hc, ""));
        data.add(new ExamImageText(R.drawable.icon_menu_kc, ""));
        data.add(new ExamImageText(R.drawable.icon_menu_mt, ""));
        adapter = new ExamImageAdapter(getContext(), data, R.layout.item_exam_image);
        adapter.setChoicePosition(-1);
        changeExamJsz.setAdapter(adapter);
        changeExamJsz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setChoicePosition(i);
                adapter.notifyDataSetChanged();
                tiku = i;
                adapter2.setChoicePosition(-1);
                adapter2.notifyDataSetChanged();
                switch (i) {
                    case 0:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.XiaoChe);
                        break;
                    case 1:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.HuoChe);
                        break;
                    case 2:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.KeChe);
                        break;
                    case 3:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.MotoChe);
                        break;
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("提示");
                builder.setMessage("您已修改题库为"+LocalPreference.getCurrentTiKu(getContext()).getName()+"么?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
                builder.show();



            }
        });

        ArrayList<ExamImageText> data2 = new ArrayList<>();
        data2.add(new ExamImageText(R.drawable.icon_menu_jly, ""));
        data2.add(new ExamImageText(R.drawable.icon_menu_hy, ""));
        data2.add(new ExamImageText(R.drawable.icon_menu_wxp, ""));
        data2.add(new ExamImageText(R.drawable.icon_menu_ky, ""));
        data2.add(new ExamImageText(R.drawable.icon_menu_czc, ""));
        adapter2 = new ExamImageAdapter(getContext(), data2, R.layout.item_exam_image);
        adapter2.setChoicePosition(-1);
        changeExamZgz.setAdapter(adapter2);
        changeExamZgz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter2.setChoicePosition(i);
                adapter2.notifyDataSetChanged();
                tiku = i + 4;
                adapter.setChoicePosition(-1);
                adapter.notifyDataSetChanged();
                switch (i) {
                    case 0:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.JiaoLianYuan);
                        break;
                    case 1:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.HuoYun);
                        break;
                    case 2:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.WeiXianPin);
                        break;
                    case 3:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.KeYun);
                        break;
                    case 4:
                        LocalPreference.setCurrentTiKu(getContext(), TiKu.ChuZuChe);
                        break;
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("提示");
                builder.setMessage("您已修改题库为"+LocalPreference.getCurrentTiKu(getContext()).getName()+"么?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
                builder.show();
            }
        });

        TiKu tiKu = LocalPreference.getCurrentTiKu(getContext());
        int index = tiKu.getIndex();
        if(index<5){
            adapter.setChoicePosition(index-1);
            adapter.notifyDataSetChanged();
        }else {
            adapter2.setChoicePosition(index-5);
            adapter2.notifyDataSetChanged();
        }

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_change_exam_libs;
    }

}
