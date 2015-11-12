package com.wan7451.choicepicture;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wan7451.image.ImageLoader;
import com.wan7451.wanadapter.list.BasePopupWindowForListView;
import com.wan7451.wanadapter.mylibrary.R;
import com.wan7451.wanadapter.recycle.WanAdapter;
import com.wan7451.wanadapter.recycle.WanItemDecoration;
import com.wan7451.wanadapter.recycle.WanViewHolder;

import java.util.List;

public class ListImageDirPopupWindow extends BasePopupWindowForListView<ImageFloder> {
    private RecyclerView mListDir;
    private ChoicePathAdapter adapter;

    public ListImageDirPopupWindow(int width, int height,
                                   List<ImageFloder> datas, View convertView) {
        super(convertView, width, height, true, datas);
    }

    @Override
    public void initViews() {
        mListDir = (RecyclerView) findViewById(R.id.listImageView);
        adapter = new ChoicePathAdapter(context, mDatas, R.layout.item_choice_picture);

        mListDir.setAdapter(adapter);
        mListDir.setLayoutManager(new LinearLayoutManager(context));
        mListDir.addItemDecoration(new WanItemDecoration(context, WanItemDecoration.VERTICAL_LIST));
    }


    class ChoicePathAdapter extends WanAdapter<ImageFloder> {

        public ChoicePathAdapter(Context context, List<ImageFloder> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(WanViewHolder holder, int position, ImageFloder item) {
            TextView name = holder.getView(R.id.id_dir_item_name);
            name.setText(item.getName());
            ImageView icon = holder.getView(R.id.id_dir_item_image);
            ImageLoader.getInstance().loadLocalImage(item.getFirstImagePath(), icon, null);
            TextView count = holder.getView(R.id.id_dir_item_count);
            count.setText(item.getCount() + "张");
        }
    }

    public interface OnImageDirSelected {
        void selected(ImageFloder floder);
    }

    private OnImageDirSelected mImageDirSelected;

    public void setOnImageDirSelected(OnImageDirSelected mImageDirSelected) {
        this.mImageDirSelected = mImageDirSelected;
    }

    @Override
    public void initEvents() {
        adapter.setOnItemClickListener(new WanAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int posotion, WanViewHolder holder) {
                if (mImageDirSelected != null) {
                    mImageDirSelected.selected(mDatas.get(posotion));
                }
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    protected void beforeInitWeNeedSomeParams(Object... params) {
    }

}
