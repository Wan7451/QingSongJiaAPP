package com.wan7451.choicepicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.wan7451.base.WanActivity;
import com.wan7451.wanadapter.mylibrary.R;

import java.io.File;
import java.util.ArrayList;

/**
 * 选择图片适配器
 * Created by Hello on 2015/5/13.
 */


public class ChoicePicturesAdapter extends RecyclerView.Adapter<ChoicePicturesAdapter.ChoiceViewHolder> {


    private final LayoutInflater mLayout;
    private WanActivity context;
    private ArrayList<String> mData;
    private SparseArray<Boolean> isChoice;
    private File mImgDir;

    public ChoicePicturesAdapter(WanActivity context, ArrayList<String> data) {
        this.context = context;
        this.mData = data;
        mLayout = LayoutInflater.from(context);
        isChoice = new SparseArray<>();
    }

    public void setImgDir(File mImgDir) {
        this.mImgDir = mImgDir;
        notifyDataSetChanged();
        isChoice.clear();
    }

    public void refershView() {
        notifyDataSetChanged();
    }

    public void destory() {
        mData.clear();
        mData = null;
        isChoice.clear();
        isChoice = null;
    }

    @Override
    public ChoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayout.inflate(R.layout.item_choicepictures, null);
        return new ChoiceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChoiceViewHolder holder, int position) {


        if (position == 0) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.paizhao1);
            holder.view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.view.setImageBitmap(bmp);
            holder.isChoiceView.setVisibility(View.GONE);
            return;
        }
        holder.isChoiceView.setVisibility(View.VISIBLE);
        String path = mData.get(mData.size() - position);

        Uri uri;
        if (mImgDir != null) {
            uri = Uri.fromFile(new File(mImgDir, path));
        } else {
            uri = Uri.fromFile(new File(path));
        }


        loadImage(holder.view, uri, position);
        Boolean isCurrChoice = isChoice.get(position - 1, false);
        if (isCurrChoice) {
            holder.isChoiceView.setChecked(true);
            holder.view.setColorFilter(Color.parseColor("#77000000"));
        } else {
            holder.view.setColorFilter(null);
            holder.isChoiceView.setChecked(false);
        }
    }

    private void loadImage(SimpleDraweeView view, Uri uri, final int p) {
        ImageRequest request1 = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setAutoRotateEnabled(true)
                .setResizeOptions(new ResizeOptions(context.getWindowWidthPx() / 4, context.getWindowWidthPx() / 4))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request1)
                .setOldController(view.getController())
                .setAutoPlayAnimations(false)
                .build();

        view.setController(controller);
    }


    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size() + 1;
        }
        return 0;
    }

    class ChoiceViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView view;

        RadioButton isChoiceView;

        public ChoiceViewHolder(View itemView) {
            super(itemView);
            view = (SimpleDraweeView) itemView.findViewById(R.id.prefect_icon);
            isChoiceView = (RadioButton) itemView.findViewById(R.id.prefect_isChoice);



            view.setLayoutParams(new FrameLayout.LayoutParams(context.getWindowWidthPx() / 3, context.getWindowWidthPx() / 3));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (l != null && getAdapterPosition() == 0) {
                        l.onItemClickListener(v, ChoiceViewHolder.this.getAdapterPosition());
                    } else {

                        Boolean isCurrChoice = isChoice.get(mData.size() - getAdapterPosition(), false);
                        if (!isCurrChoice && getChoiceCount() >= 5) {
                            context.showToast("做多选择5张图片");
                            return;
                        }
                        isChoiceView.setChecked(!isCurrChoice);
                        isChoice.put(mData.size() - getAdapterPosition(), !isCurrChoice);
                        if (!isCurrChoice) {
                            view.setColorFilter(Color.parseColor("#77000000"));
                        } else {
                            view.setColorFilter(null);
                        }
                    }
                }
            });
        }
    }


    public int getChoiceCount() {
        int count = 0;
        for (int i = 0; i < isChoice.size(); i++) {
            if (isChoice.get(isChoice.keyAt(i), false)) {
                count++;
            }
        }
        return count;
    }

    public ArrayList getChoicePictures() {
        ArrayList<String> choicePictures = new ArrayList<>();
        for (int i = 0; i < isChoice.size(); i++) {
            if (isChoice.get(isChoice.keyAt(i), false)) {

                if (mImgDir != null) {
                    choicePictures.add(mImgDir + File.separator + mData.get(isChoice.keyAt(i)));
                } else {
                    choicePictures.add(mData.get(isChoice.keyAt(i)));
                }
            }
        }
        return choicePictures;
    }

    private OnItemClickListener l;

    public void setOnItemClickListener(OnItemClickListener l) {
        this.l = l;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }
}
