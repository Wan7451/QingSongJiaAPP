package com.wan7451.wancalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;


import java.util.ArrayList;
import java.util.Calendar;

public class DateView extends View {

    /**
     * 两种模式 （月份和星期）
     */
    public static final int MONTH_STYLE = 0;
    public static final int WEEK_STYLE = 1;

    public static int COLOR_TODAY = 0xFFCCECF9;
    public static int COLOR_SELECT = 0xFF55C0E9;
    public static int COLOR_HINT = 0xFFD8D3CD;


    private static final int TOTAL_COL = 7;
    private static final int TOTAL_ROW = 6;

    private Paint mCirclePaint; // 绘制圆形的画笔
    private Paint mTextPaint; // 绘制文本的画笔
    private int mViewWidth;  // 视图的宽度
    private int mViewHight;  // 视图的高度
    private int mCellSpace;  // 单元格间距
    private Row rows[] = new Row[TOTAL_ROW];  // 行数组，每个元素代表一行
    private static CustomDate mShowDate;//自定义的日期  包括year month day
    public static int style = MONTH_STYLE;
    private static final int WEEK = 7;
    private CallBack mCallBack;//回调
    private int touchSlop;
    private boolean callBackCellSpace;

    public interface CallBack {

        void clickDate(CustomDate date);//回调点击的日期

        void onMesureCellHeight(int cellSpace);//回调cell的高度确定slidingDrawer高度

        void changeDate(CustomDate date);//回调滑动viewPager改变的日期
    }

    public DateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);

    }

    public DateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public DateView(Context context) {
        super(context);
        init(context);
    }

    public DateView(Context context, int style, CallBack mCallBack) {
        super(context);
        DateView.style = style;
        this.mCallBack = mCallBack;

        init(context);

        setHintData(null);
    }

    private ArrayList<Calendar> showHintData;

    public void setHintData(ArrayList<Calendar> data) {
        this.showHintData = data;
        update();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < TOTAL_ROW; i++) {
            if (rows[i] != null)
                rows[i].drawCells(canvas);
        }
    }

    private void init(Context context) {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initDate();

    }

    private void initDate() {
        if (style == MONTH_STYLE) {
            mShowDate = new CustomDate();
        } else if (style == WEEK_STYLE) {
            mShowDate = DateUtil.getNextSunday();
        }
        fillDate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHight = h;
        mCellSpace = Math.min(mViewHight / TOTAL_ROW, mViewWidth / TOTAL_COL);
        if (!callBackCellSpace) {
            mCallBack.onMesureCellHeight(mCellSpace);
            callBackCellSpace = true;
        }
        mTextPaint.setTextSize(mCellSpace / 3);
    }

    private Cell mClickCell;
    private float mDownX;
    private float mDownY;

    /*
     *
     * 触摸事件为了确定点击的位置日期
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float disX = event.getX() - mDownX;
                float disY = event.getY() - mDownY;
                if (Math.abs(disX) < touchSlop && Math.abs(disY) < touchSlop) {
                    int col = (int) (mDownX / mCellSpace);
                    int row = (int) (mDownY / mCellSpace);
                    measureClickCell(col, row);
                }
                break;
        }
        return true;
    }

    /**
     * 计算点击的单元格
     *
     * @param col
     * @param row
     */
    private void measureClickCell(int col, int row) {
        if (col >= TOTAL_COL || row >= TOTAL_ROW)
            return;
        if (rows[row].cells[col].state == State.NEXT_MONTH_DAY || rows[row].cells[col].state == State.PAST_MONTH_DAY) {
            return;
        }
        if (mClickCell != null) {
            rows[mClickCell.j].cells[mClickCell.i] = mClickCell;
        }
        if (rows[row] != null) {
            mClickCell = new Cell(rows[row].cells[col].date,
                    rows[row].cells[col].state, rows[row].cells[col].i,
                    rows[row].cells[col].j);
            rows[row].cells[col].state = State.CLICK_DAY;
            CustomDate date = rows[row].cells[col].date;
            date.week = col;
            mCallBack.clickDate(date);
            invalidate();
        }
    }

    // 组
    class Row {
        public int j;

        Row(int j) {
            this.j = j;
        }

        public Cell[] cells = new Cell[TOTAL_COL];

        public void drawCells(Canvas canvas) {
            for (int i = 0; i < cells.length; i++) {
                if (cells[i] != null)
                    cells[i].drawSelf(canvas);
            }

        }
    }

    // 单元格
    class Cell {
        public CustomDate date;
        public State state;
        public int i;
        public int j;
        public boolean isShowHint;

        public Cell(CustomDate date, State state, int i, int j) {
            super();
            this.date = date;
            this.state = state;
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "date=" + date +
                    ", state=" + state +
                    ", i=" + i +
                    ", j=" + j +
                    ", isShowHint=" + isShowHint +
                    '}';
        }

        //绘制一个单元格 如果颜色需要自定义可以修改
        public void drawSelf(Canvas canvas) {

            // 绘制文字
            String content = date.day + "";

            switch (state) {
                case CURRENT_MONTH_DAY:    // 当前月日期
                    mTextPaint.setColor(Color.parseColor("#80000000"));
                    canvas.drawText(content,
                            (float) ((i + 0.5) * mCellSpace - mTextPaint.measureText(content) / 2),
                            (float) ((j + 0.7) * mCellSpace - mTextPaint.measureText(
                                    content, 0, 1) / 2), mTextPaint);
                    break;
                case NEXT_MONTH_DAY:    // 下一个月
                case PAST_MONTH_DAY:   // 过去一个月
                    //不显示
                    mTextPaint.setColor(Color.parseColor("#40000000"));
                    break;
                case TODAY:  // 今天
                    mTextPaint.setColor(Color.parseColor("#80000000"));

                    //圆形背景
                    mCirclePaint.setColor(COLOR_TODAY); // 红色圆形
                    canvas.drawCircle((float) (mCellSpace * (i + 0.5)),
                            (float) ((j + 0.5) * mCellSpace), mCellSpace / 2,
                            mCirclePaint);
                    //文字
                    canvas.drawText(content,
                            (float) ((i + 0.5) * mCellSpace - mTextPaint.measureText(content) / 2),
                            (float) ((j + 0.7) * mCellSpace - mTextPaint.measureText(
                                    content, 0, 1) / 2), mTextPaint);


                    break;
                case CLICK_DAY:
                    mTextPaint.setColor(Color.parseColor("#fffffe"));

                    //圆形背景
                    mCirclePaint.setColor(COLOR_SELECT);
                    canvas.drawCircle((float) (mCellSpace * (i + 0.5)),
                            (float) ((j + 0.5) * mCellSpace), mCellSpace / 2,
                            mCirclePaint);
                    //文字
                    canvas.drawText(content,
                            (float) ((i + 0.5) * mCellSpace - mTextPaint.measureText(content) / 2),
                            (float) ((j + 0.7) * mCellSpace - mTextPaint.measureText(
                                    content, 0, 1) / 2), mTextPaint);
                    break;
            }
            if (isShowHint) {
                mCirclePaint.setColor(COLOR_HINT);
                canvas.drawCircle((float) (mCellSpace * (i + 0.5)),
                        (float) ((j + 0.5) * mCellSpace) - (mTextPaint.ascent() - mTextPaint.descent()) / 2, mCellSpace / 20,
                        mCirclePaint);
            }
        }
    }

    /**
     * @author huang
     *         cell的state
     *         当前月日期，过去的月的日期，下个月的日期，今天，点击的日期
     */
    enum State {
        CURRENT_MONTH_DAY, PAST_MONTH_DAY, NEXT_MONTH_DAY, TODAY, CLICK_DAY
    }

    /**
     * 填充日期的数据
     */
    private void fillDate() {
        if (style == MONTH_STYLE) {
            fillMonthDate();
        } else if (style == WEEK_STYLE) {
            fillWeekDate();
        }
        mCallBack.changeDate(mShowDate);
    }

    /**
     * 填充星期模式下的数据 默认通过当前日期得到所在星期天的日期，然后依次填充日期
     */
    private void fillWeekDate() {
        int lastMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month - 1);
        rows[0] = new Row(0);
        int day = mShowDate.day;
        for (int i = TOTAL_COL - 1; i >= 0; i--) {
            day -= 1;
            if (day < 1) {
                day = lastMonthDays;
            }
            CustomDate date = CustomDate.modifiDayForObject(mShowDate, day);
            if (DateUtil.isToday(date)) {
                mClickCell = new Cell(date, State.TODAY, i, 0);
                date.week = i;
                mCallBack.clickDate(date);
                rows[0].cells[i] = new Cell(date, State.CLICK_DAY, i, 0);
                continue;
            }
            rows[0].cells[i] = new Cell(date, State.CURRENT_MONTH_DAY, i, 0);
        }
    }

    /**
     * 填充月份模式下数据 通过getWeekDayFromDate得到一个月第一天是星期几就可以算出所有的日期的位置
     * 然后依次填充
     * 这里最好重构一下
     */
    private void fillMonthDate() {
        int monthDay = DateUtil.getCurrentMonthDay();      // 今天
        int lastMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month - 1);  // 上个月的天数
        int currentMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month);  // 当前月的天数
        int firstDayWeek = DateUtil.getWeekDayFromDate(mShowDate.year, mShowDate.month); //当月第一天的星期书
        boolean isCurrentMonth = false;
        if (DateUtil.isCurrentMonth(mShowDate)) {
            isCurrentMonth = true;
        }
        int day = 0;


        for (int j = 0; j < TOTAL_ROW; j++) {
            rows[j] = new Row(j);
            for (int i = 0; i < TOTAL_COL; i++) {
                int postion = i + j * TOTAL_COL;  // 单元格位置

                // 这个月的
                if (postion >= firstDayWeek
                        && postion < firstDayWeek + currentMonthDays) {
                    day++;
                    // 今天
                    if (isCurrentMonth && day == monthDay) {
                        CustomDate date = CustomDate.modifiDayForObject(mShowDate, day);
                        // mClickCell = new Cell(date, State.TODAY, i, j);
                        date.week = i;
                        mCallBack.clickDate(date);
                        rows[j].cells[i] = new Cell(date, State.TODAY, i, j);
                        continue;
                    }
                    // 如果比这个月的今天要大，表示还没到
                    rows[j].cells[i] = new Cell(CustomDate.modifiDayForObject(mShowDate, day),
                            State.CURRENT_MONTH_DAY, i, j);

                    //是否显示提示
                    if (showHintData != null)
                        for (int h = 0; h < showHintData.size(); h++) {
                            Calendar c = showHintData.get(h);
                            if (c.get(Calendar.YEAR) == mShowDate.year
                                    && c.get(Calendar.MONTH) + 1 == mShowDate.month
                                    && c.get(Calendar.DAY_OF_MONTH) == day) {
                                rows[j].cells[i].isShowHint = true;
                            }
                        }
                    // 过去一个月
                } else if (postion < firstDayWeek) {
                    rows[j].cells[i] = new Cell(new CustomDate(mShowDate.year, mShowDate.month - 1, lastMonthDays - (firstDayWeek - postion - 1)), State.PAST_MONTH_DAY, i, j);
                    // 下个月
                } else if (postion >= firstDayWeek + currentMonthDays) {
                    rows[j].cells[i] = new Cell((new CustomDate(mShowDate.year, mShowDate.month + 1, postion - firstDayWeek - currentMonthDays + 1)), State.NEXT_MONTH_DAY, i, j);
                }
            }
        }
    }

    public void update() {
        fillDate();
        invalidate();
    }

    public void backToday() {
        initDate();
        invalidate();
    }

    //切换style
    public void switchStyle(int style) {
        DateView.style = style;
        if (style == MONTH_STYLE) {
            update();
        } else if (style == WEEK_STYLE) {
            int firstDayWeek = DateUtil.getWeekDayFromDate(mShowDate.year,
                    mShowDate.month);
            int day = 1 + WEEK - firstDayWeek;
            mShowDate.day = day;

            update();
        }

    }

    //向右滑动
    public void rightSilde() {
        if (style == MONTH_STYLE) {

            if (mShowDate.month == 12) {
                mShowDate.month = 1;
                mShowDate.year += 1;
            } else {
                mShowDate.month += 1;
            }

        } else if (style == WEEK_STYLE) {
            int currentMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month);
            if (mShowDate.day + WEEK > currentMonthDays) {
                if (mShowDate.month == 12) {
                    mShowDate.month = 1;
                    mShowDate.year += 1;
                } else {
                    mShowDate.month += 1;
                }
                mShowDate.day = WEEK - currentMonthDays + mShowDate.day;
            } else {
                mShowDate.day += WEEK;

            }
        }
        update();
    }

    //向左滑动
    public void leftSilde() {

        if (style == MONTH_STYLE) {
            if (mShowDate.month == 1) {
                mShowDate.month = 12;
                mShowDate.year -= 1;
            } else {
                mShowDate.month -= 1;
            }

        } else if (style == WEEK_STYLE) {
            int lastMonthDays = DateUtil.getMonthDays(mShowDate.year, mShowDate.month);
            if (mShowDate.day - WEEK < 1) {
                if (mShowDate.month == 1) {
                    mShowDate.month = 12;
                    mShowDate.year -= 1;
                } else {
                    mShowDate.month -= 1;
                }
                mShowDate.day = lastMonthDays - WEEK + mShowDate.day;

            } else {
                mShowDate.day -= WEEK;
            }
        }
        update();
    }
}
