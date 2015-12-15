package com.qingsongjia.qingsongjia.driverschool;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.wan7451.base.WanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SchoolImgsActivity extends WanActivity {


    @Bind(R.id.imgsView)
    ViewPager imgsView;
    @Bind(R.id.imgsCount)
    TextView imgsCount;


    String[] imgs = {"http://img1.imgtn.bdimg.com/it/u=2643220050,487988016&fm=21&gp=0.jpg",
    "http://i3.dpfile.com/2009-03-12/1701983_b.jpg%28700x700%29/thumb.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2332649694,1718080041&fm=21&gp=0.jpg"};

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("驾校街景");
        imgsCount.setText(1+"/"+imgs.length);

      String[] imgss=  getIntent().getStringArrayExtra("imgs");

        ImageAdapter adapter=new ImageAdapter();
        imgsView.setAdapter(adapter);
        imgsView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imgsCount.setText((position+1)+"/"+imgs.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_school_imgs;
    }


    class ImageAdapter extends PagerAdapter {

        SimpleDraweeView[] icons = new SimpleDraweeView[5];

        public ImageAdapter() {

          LayoutInflater inflater=  LayoutInflater.from(getContext());

            for (int i = 0; i < icons.length; i++) {
                SimpleDraweeView icon = (SimpleDraweeView) inflater.inflate(R.layout.item_school_img,null);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                icon.setLayoutParams(lp);

                icons[i] = icon;
            }

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            SimpleDraweeView icon = icons[position % icons.length];
            container.removeView(icon);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SimpleDraweeView icon = icons[position % icons.length];
            container.addView(icon);
            icon.setImageURI(Uri.parse(imgs[position]));
            return icon;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
