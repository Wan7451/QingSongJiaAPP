package com.wan7451.wanadapter.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以添加多个透视图、尾视图的适配器
 * 只需重写简单方法即可
 * 需要 DmlViewHolder 配合
 * Created by Wan7451 on 2015/6/27.
 */
public abstract class WanAdapter<T> extends RecyclerView.Adapter<WanViewHolder> {


    public static final int VIEW_TYPE_COUSTOM = 123454; //用于区分头尾视图的值

    private ArrayList<View> mHeaderViews = new ArrayList<>(); //头视图
    private ArrayList<View> mFooterViews = new ArrayList<>();   //尾视图


    private ArrayList<Integer> mHeaderViewTypes = new ArrayList<>();
    private ArrayList<Integer> mFooterViewTypes = new ArrayList<>();


    private ArrayList<View> mAllItemView = new ArrayList<>();//所有的View
    private boolean isSetSelector;

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    /**
     * 构造器
     *
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public WanAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }


    public LayoutInflater getInflater() {
        return mInflater;
    }

    /**
     * 是否需要Selector
     *
     * @param isSetSelector
     */
    public void setIsSetSelector(boolean isSetSelector) {
        this.isSetSelector = isSetSelector;
    }

    public ArrayList<View> getAllItemView() {
        return mAllItemView;
    }

    /**
     * 设置选中效果，默认效果
     *
     * @param itemView     要设置的View
     * @param selectColor  选中颜色
     * @param defaultColor 默认颜色
     */
    public void setSelector(View itemView, int selectColor, int defaultColor) {

        for (View view : mAllItemView) {
            view.setBackgroundColor(defaultColor);
            if (view == itemView) {
                view.setBackgroundColor(selectColor);
            }
        }

    }


    /**
     * 设置选中效果，默认效果
     *
     * @param itemView        要设置的View
     * @param selectColor     选中颜色
     * @param defaultDrawable 默认Drawable
     */
    public void setSelectorDrawable(View itemView, int selectColor, int defaultDrawable) {

        for (View view : mAllItemView) {
            view.setBackgroundResource(defaultDrawable);
            if (view == itemView) {
                view.setBackgroundColor(selectColor);
            }
        }

    }

    public Context getContext() {
        return mContext;
    }

    /**
     * 返回显示数据集合
     *
     * @return
     */
    public List<T> getDatas() {
        return mDatas;
    }

    /**
     * 可以添加多个头视图
     *
     * @param headerView
     */
    public void addHeaderView(View headerView) {
        mHeaderViews.add(headerView);
    }

    /**
     * 可以添加多个尾视图
     *
     * @param footerView 尾视图
     */
    public void addFooterView(View footerView) {
        mFooterViews.add(footerView);
    }

    @Override
    public int getItemViewType(int position) {

        if (mHeaderViews.size() > 0 && position < mHeaderViews.size()) {
            //用position作为HeaderView 的   ViewType标记
            //记录每个ViewType标记
            mHeaderViewTypes.add(setType(position));
            return setType(position);
        }

        if (mFooterViews.size() > 0 && position > getAdvanceCount() - 1 + mHeaderViews.size()) {
            //用position作为FooterView 的   ViewType标记
            //记录每个ViewType标记
            mFooterViewTypes.add(setType(position));
            return setType(position);
        }

        if (mHeaderViews.size() > 0) {
            return getAdvanceViewType(position - mHeaderViews.size());
        }

        return getAdvanceViewType(position);
    }

    private int setType(int position) {
        return position + VIEW_TYPE_COUSTOM;
    }

    /**
     * Item页布局类型个数，默认为1
     * @param position
     * @return
     */
    public int getAdvanceViewType(int position) {
        return 1;
    }

    /**
     * 最多多少个Item
     */
    public int getMaxCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    private int getAdvanceCount() {
        return getMaxCount();
    }

    private void onBindAdvanceViewHolder(WanViewHolder holder, int i) {
        convert(holder, i, mDatas.get(i));
    }

    /**
     * 设置每个页面显示的内容
     *
     * @param holder itemHolder
     * @param item   每一Item显示的数据
     */
    public abstract void convert(WanViewHolder holder, int position, T item);


    /**
     * 设置ItemView 样式
     *
     * @param itemView 要设置的ItemView
     */
    public void customItemViewStyle(View itemView) {

    }

    /**
     * 创建ViewHolder
     *
     * @param parent   RecycleView对象
     * @param viewType viee类型
     * @return Holder对象
     */
    protected WanViewHolder onCreateAdvanceViewHolder(ViewGroup parent, int viewType) {

        View v = mInflater.inflate(mItemLayoutId, null);
        customItemViewStyle(v);
        if (isSetSelector)
            mAllItemView.add(v);
        return new WanViewHolder(v, this);
    }

    @Override
    public WanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (mHeaderViewTypes.contains(viewType)) {
            return new HeaderHolder(mHeaderViews.get(getType(viewType)));
        }

        if (mFooterViewTypes.contains(viewType)) {
            int index = getType(viewType) - getAdvanceCount() - mHeaderViews.size();
            return new FooterHolder(mFooterViews.get(index));
        }

        return onCreateAdvanceViewHolder(parent, viewType);
    }

    private int getType(int viewType) {
        return viewType - VIEW_TYPE_COUSTOM;
    }

    @Override
    public void onBindViewHolder(WanViewHolder holder, int position) {

        if (mFooterViews.size() > 0 && (position > getAdvanceCount() - 1 + mHeaderViews.size())) {
            return;
        }


        if (mHeaderViews.size() > 0) {
            if (position < mHeaderViews.size()) {
                return;
            }
            onBindAdvanceViewHolder(holder, position - mHeaderViews.size());
            return;
        }
        onBindAdvanceViewHolder(holder, position);
    }


    class HeaderHolder extends WanViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    class FooterHolder extends WanViewHolder {

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderViews.size() > 0 && mFooterViews.size() > 0) {
            return getAdvanceCount() + mHeaderViews.size() + mFooterViews.size();
        }
        if (mHeaderViews.size() > 0) {
            return getAdvanceCount() + mHeaderViews.size();
        }
        if (mFooterViews.size() > 0) {
            return getAdvanceCount() + mFooterViews.size();
        }

        return getAdvanceCount();
    }

    public void setDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }


    public int getHeaderViewsCount() {
        return mHeaderViews.size();
    }

    public int getFooterViewsCount() {
        return mFooterViews.size();
    }


    protected OnItemClickListener l;


    public OnItemClickListener getItemClickListener() {
        return l;
    }

    /**
     * 设置点击事件监听器
     *
     * @param l 监听器对象
     */
    public void setOnItemClickListener(OnItemClickListener l) {
        this.l = l;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int posotion, WanViewHolder holder);
    }


}
