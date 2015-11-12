package com.wan7451.adbar;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wan7451.wanadapter.mylibrary.R;

import java.util.List;

/**
 * Created by Wan7451 on 2015/7/16.
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private Context context;
    private List<String> imageIdList;

    private int size;
    private boolean isInfiniteLoop;
    private final LayoutInflater layoutInflater;

    public ImagePagerAdapter(Context context, List<String> imageIdList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.imageIdList = imageIdList;
        this.size = imageIdList.size();
        isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : imageIdList.size();
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    public int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = (SimpleDraweeView)
                    layoutInflater.inflate(R.layout.view_adbar_image, null);

//                    new SimpleDraweeView(context);

//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//            holder.imageView.setLayoutParams(lp);
//
//            GenericDraweeHierarchyBuilder builder =
//                    new GenericDraweeHierarchyBuilder(context.getResources());
//            GenericDraweeHierarchy hierarchy = builder
//                    .setFadeDuration(300)
//                    .setPlaceholderImage(context.getResources().getDrawable(R.drawable.default_adview_image))
//                    .setFailureImage(context.getResources().getDrawable(R.drawable.fail_adview_image))
//                    .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
//                    .build();
//
//            RoundingParams roundingParams = new RoundingParams();
//            roundingParams.setCornersRadius(10);
//            hierarchy.setRoundingParams(roundingParams);
//            holder.imageView.setHierarchy(hierarchy);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (!TextUtils.isEmpty(imageIdList.get(getPosition(position))))
            holder.imageView.setImageURI(Uri.parse(imageIdList.get(getPosition(position))));

        return view;
    }

    private static class ViewHolder {

        SimpleDraweeView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
}
