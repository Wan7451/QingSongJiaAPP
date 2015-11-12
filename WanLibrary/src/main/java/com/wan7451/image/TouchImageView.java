package com.wan7451.image;

//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.Config;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.graphics.PointF;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//import android.util.FloatMath;
//import android.view.MotionEvent;
//import android.widget.ImageView;

//public class TouchImageView extends ImageView {
//
//    float x_down = 0;
//    float y_down = 0;
//    PointF start = new PointF();
//    PointF mid = new PointF();
//    float oldDist = 1f;
//    float oldRotation = 0;
//    Matrix matrix = new Matrix();
//    Matrix matrix1 = new Matrix();
//    Matrix savedMatrix = new Matrix();
//
//    private static final int NONE = 0;
//    private static final int DRAG = 1;
//    private static final int ZOOM = 2;
//    int mode = NONE;
//
//    boolean matrixCheck = false;
//
//    int widthScreen;
//    int heightScreen;
//
//    Bitmap gintama;
//
//
//    public TouchImageView(Context context) {
//        super(context);
//    }
//
//    public TouchImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override
//    public void setImageBitmap(Bitmap bm) {
//        if (bm == null)
//            return;
//        super.setImageBitmap(bm);
//        gintama = bm;
//        DisplayMetrics dm = new DisplayMetrics();
//        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
//        widthScreen = dm.widthPixels;
//        heightScreen = dm.heightPixels;
//        //背景设置为balck
//        setBackgroundColor(Color.BLACK);
////        //将缩放类型设置为FIT_CENTER，表示把图片按比例扩大/缩小到View的宽度，居中显示
//        matrix = new Matrix();
//        setScaleType(ScaleType.FIT_CENTER);
//        matrix.set(getImageMatrix());
//        float[] values = new float[9];
//        matrix.getValues(values);
//
//        int px = (widthScreen - bm.getWidth()) / 2;
//        int py = (heightScreen - bm.getHeight()) / 2;
//
//
//        float sx = widthScreen / bm.getWidth();
//        float sy = widthScreen / bm.getWidth();
//
//        float lx = sx * bm.getWidth(); //放大后的大小
//        float ly = sy * bm.getHeight();//放大后的大小
//
//        float ptx = (widthScreen - lx) / 6;
//        float pty = (heightScreen - ly) / 6;
//
//        matrix.postTranslate(ptx, pty);
//        matrix.postScale(sx, sy, 0, 0);
//    }
//
//
//    protected void onDraw(Canvas canvas) {
//        canvas.save();
//        if (gintama != null)
//            canvas.drawBitmap(gintama, matrix, null);
//        canvas.restore();
//    }
//
//    public boolean onTouchEvent(MotionEvent event) {
//        if (gintama != null)
//            switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                case MotionEvent.ACTION_DOWN:
//                    mode = DRAG;
//                    x_down = event.getX();
//                    y_down = event.getY();
//                    savedMatrix.set(matrix);
//                    break;
//                case MotionEvent.ACTION_POINTER_DOWN:
//                    mode = ZOOM;
//                    oldDist = spacing(event);
//                    oldRotation = rotation(event);
//                    savedMatrix.set(matrix);
//                    midPoint(mid, event);
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    if (mode == ZOOM) {
//                        matrix1.set(savedMatrix);
//                        float rotation = rotation(event) - oldRotation;
//                        float newDist = spacing(event);
//                        float scale = newDist / oldDist;
//                        matrix1.postScale(scale, scale, mid.x, mid.y);// 縮放
//                        //   matrix1.postRotate(rotation, mid.x, mid.y);// 旋轉
//                        matrixCheck = matrixCheck();
//                        if (matrixCheck == false) {
//                            matrix.set(matrix1);
//                            invalidate();
//                        }
//                    } else if (mode == DRAG) {
//                        matrix1.set(savedMatrix);
//                        matrix1.postTranslate(event.getX() - x_down, event.getY()
//                                - y_down);// 平移
//                        matrixCheck = matrixCheck();
//                        matrixCheck = matrixCheck();
//                        if (matrixCheck == false) {
//                            matrix.set(matrix1);
//                            invalidate();
//                        }
//                    }
//                    break;
//                case MotionEvent.ACTION_UP:
//                case MotionEvent.ACTION_POINTER_UP:
//                    mode = NONE;
//                    break;
//            }
//        return true;
//    }
//
//    private boolean matrixCheck() {
//        float[] f = new float[9];
//        matrix1.getValues(f);
//        // 图片4个顶点的坐标
//        float x1 = f[0] * 0 + f[1] * 0 + f[2];
//        float y1 = f[3] * 0 + f[4] * 0 + f[5];
//        float x2 = f[0] * gintama.getWidth() + f[1] * 0 + f[2];
//        float y2 = f[3] * gintama.getWidth() + f[4] * 0 + f[5];
//        float x3 = f[0] * 0 + f[1] * gintama.getHeight() + f[2];
//        float y3 = f[3] * 0 + f[4] * gintama.getHeight() + f[5];
//        float x4 = f[0] * gintama.getWidth() + f[1] * gintama.getHeight() + f[2];
//        float y4 = f[3] * gintama.getWidth() + f[4] * gintama.getHeight() + f[5];
//        // 图片现宽度
//        double width = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
//        // 缩放比率判断
//        if (width < widthScreen / 3 || width > widthScreen * 3) {
//            return true;
//        }
//        // 出界判断
//        if ((x1 < widthScreen / 3 && x2 < widthScreen / 3
//                && x3 < widthScreen / 3 && x4 < widthScreen / 3)
//                || (x1 > widthScreen * 2 / 3 && x2 > widthScreen * 2 / 3
//                && x3 > widthScreen * 2 / 3 && x4 > widthScreen * 2 / 3)
//                || (y1 < heightScreen / 3 && y2 < heightScreen / 3
//                && y3 < heightScreen / 3 && y4 < heightScreen / 3)
//                || (y1 > heightScreen * 2 / 3 && y2 > heightScreen * 2 / 3
//                && y3 > heightScreen * 2 / 3 && y4 > heightScreen * 2 / 3)) {
//            return true;
//        }
//        return false;
//    }
//
//    // 触碰两点间距离
//    private float spacing(MotionEvent event) {
//        float x = event.getX(0) - event.getX(1);
//        float y = event.getY(0) - event.getY(1);
//        return FloatMath.sqrt(x * x + y * y);
//    }
//
//    // 取手势中心点
//    private void midPoint(PointF point, MotionEvent event) {
//        float x = event.getX(0) + event.getX(1);
//        float y = event.getY(0) + event.getY(1);
//        point.set(x / 2, y / 2);
//    }
//
//    // 取旋转角度
//    private float rotation(MotionEvent event) {
//        double delta_x = (event.getX(0) - event.getX(1));
//        double delta_y = (event.getY(0) - event.getY(1));
//        double radians = Math.atan2(delta_y, delta_x);
//        return (float) Math.toDegrees(radians);
//    }
//
//    // 将移动，缩放以及旋转后的图层保存为新图片
//    // 本例中沒有用到該方法，需要保存圖片的可以參考
//    public Bitmap CreatNewPhoto() {
//        Bitmap bitmap = Bitmap.createBitmap(widthScreen, heightScreen,
//                Config.ARGB_8888); // 背景图片
//        Canvas canvas = new Canvas(bitmap); // 新建画布
//        canvas.drawBitmap(gintama, matrix, null); // 画图片
//        canvas.save(Canvas.ALL_SAVE_FLAG); // 保存画布
//        canvas.restore();
//        return bitmap;
//    }
//
//}


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 带放大、缩小、移动效果的ImageView
 */
public class TouchImageView extends ImageView {
    private final static String TAG = "MatrixImageView";
    private GestureDetector mGestureDetector;
    /**
     * 模板Matrix，用以初始化
     */
    private Matrix mMatrix = new Matrix();
    /**
     * 图片长度
     */
    private float mImageWidth;
    /**
     * 图片高度
     */
    private float mImageHeight;

    public TouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        MatrixTouchListener mListener = new MatrixTouchListener();
        setOnTouchListener(mListener);
        mGestureDetector = new GestureDetector(getContext(), new GestureListener(mListener));
        //背景设置为balck
        setBackgroundColor(Color.BLACK);
        //将缩放类型设置为FIT_CENTER，表示把图片按比例扩大/缩小到View的宽度，居中显示
        setScaleType(ScaleType.FIT_CENTER);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        // TODO Auto-generated method stub
        super.setImageBitmap(bm);
        //设置完图片后，获取该图片的坐标变换矩阵
        mMatrix.set(getImageMatrix());
        float[] values = new float[9];
        mMatrix.getValues(values);
        //图片宽度为屏幕宽度除缩放倍数
        mImageWidth = getWidth() / values[Matrix.MSCALE_X];
        mImageHeight = (getHeight() - values[Matrix.MTRANS_Y] * 2) / values[Matrix.MSCALE_Y];
    }

    public class MatrixTouchListener implements OnTouchListener {
        /**
         * 拖拉照片模式
         */
        private static final int MODE_DRAG = 1;
        /**
         * 放大缩小照片模式
         */
        private static final int MODE_ZOOM = 2;
        /**
         * 不支持Matrix
         */
        private static final int MODE_UNABLE = 3;
        /**
         * 最大缩放级别
         */
        float mMaxScale = 6;
        /**
         * 双击时的缩放级别
         */
        float mDobleClickScale = 2;
        private int mMode = 0;//
        /**
         * 缩放开始时的手指间距
         */
        private float mStartDis;
        /**
         * 当前Matrix
         */
        private Matrix mCurrentMatrix = new Matrix();

        /**
         * 用于记录开始时候的坐标位置
         */
        private PointF startPoint = new PointF();

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    //设置拖动模式
                    mMode = MODE_DRAG;
                    startPoint.set(event.getX(), event.getY());
                    isMatrixEnable();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    reSetMatrix();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mMode == MODE_ZOOM) {
                        setZoomMatrix(event);
                    } else if (mMode == MODE_DRAG) {
                        setDragMatrix(event);
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (mMode == MODE_UNABLE) return true;
                    mMode = MODE_ZOOM;
                    mStartDis = distance(event);
                    break;
                default:
                    break;
            }

            return mGestureDetector.onTouchEvent(event);
        }

        public void setDragMatrix(MotionEvent event) {
            if (isZoomChanged()) {
                float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                float dy = event.getY() - startPoint.y; // 得到x轴的移动距离
                //避免和双击冲突,大于10f才算是拖动
                if (Math.sqrt(dx * dx + dy * dy) > 10f) {
                    startPoint.set(event.getX(), event.getY());
                    //在当前基础上移动
                    mCurrentMatrix.set(getImageMatrix());
                    float[] values = new float[9];
                    mCurrentMatrix.getValues(values);
                    dx = checkDxBound(values, dx);
                    dy = checkDyBound(values, dy);
                    mCurrentMatrix.postTranslate(dx, dy);
                    setImageMatrix(mCurrentMatrix);
                }
            }
        }

        /**
         * 判断缩放级别是否是改变过
         *
         * @return true表示非初始值, false表示初始值
         */
        private boolean isZoomChanged() {
            float[] values = new float[9];
            getImageMatrix().getValues(values);
            //获取当前X轴缩放级别
            float scale = values[Matrix.MSCALE_X];
            //获取模板的X轴缩放级别，两者做比较
            mMatrix.getValues(values);
            return scale != values[Matrix.MSCALE_X];
        }

        /**
         * 和当前矩阵对比，检验dy，使图像移动后不会超出ImageView边界
         *
         * @param values
         * @param dy
         * @return
         */
        private float checkDyBound(float[] values, float dy) {
            float height = getHeight();
            if (mImageHeight * values[Matrix.MSCALE_Y] < height)
                return 0;
            if (values[Matrix.MTRANS_Y] + dy > 0)
                dy = -values[Matrix.MTRANS_Y];
            else if (values[Matrix.MTRANS_Y] + dy < -(mImageHeight * values[Matrix.MSCALE_Y] - height))
                dy = -(mImageHeight * values[Matrix.MSCALE_Y] - height) - values[Matrix.MTRANS_Y];
            return dy;
        }

        /**
         * 和当前矩阵对比，检验dx，使图像移动后不会超出ImageView边界
         *
         * @param values
         * @param dx
         * @return
         */
        private float checkDxBound(float[] values, float dx) {
            float width = getWidth();
            if (mImageWidth * values[Matrix.MSCALE_X] < width)
                return 0;
            if (values[Matrix.MTRANS_X] + dx > 0)
                dx = -values[Matrix.MTRANS_X];
            else if (values[Matrix.MTRANS_X] + dx < -(mImageWidth * values[Matrix.MSCALE_X] - width))
                dx = -(mImageWidth * values[Matrix.MSCALE_X] - width) - values[Matrix.MTRANS_X];
            return dx;
        }

        /**
         * 设置缩放Matrix
         *
         * @param event
         */
        private void setZoomMatrix(MotionEvent event) {
            //只有同时触屏两个点的时候才执行
            if (event.getPointerCount() < 2) return;
            float endDis = distance(event);// 结束距离
            if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                float scale = endDis / mStartDis;// 得到缩放倍数
                mStartDis = endDis;//重置距离
                mCurrentMatrix.set(getImageMatrix());//初始化Matrix
                float[] values = new float[9];
                mCurrentMatrix.getValues(values);

                scale = checkMaxScale(scale, values);
                setImageMatrix(mCurrentMatrix);
            }
        }

        /**
         * 检验scale，使图像缩放后不会超出最大倍数
         *
         * @param scale
         * @param values
         * @return
         */
        private float checkMaxScale(float scale, float[] values) {
            if (scale * values[Matrix.MSCALE_X] > mMaxScale)
                scale = mMaxScale / values[Matrix.MSCALE_X];
            mCurrentMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            return scale;
        }

        /**
         * 重置Matrix
         */
        private void reSetMatrix() {
            if (checkRest()) {
                mCurrentMatrix.set(mMatrix);
                setImageMatrix(mCurrentMatrix);
            }
        }

        /**
         * 判断是否需要重置
         *
         * @return 当前缩放级别小于模板缩放级别时，重置
         */
        private boolean checkRest() {
            // TODO Auto-generated method stub
            float[] values = new float[9];
            getImageMatrix().getValues(values);
            //获取当前X轴缩放级别
            float scale = values[Matrix.MSCALE_X];
            //获取模板的X轴缩放级别，两者做比较
            mMatrix.getValues(values);
            return scale < values[Matrix.MSCALE_X];
        }

        /**
         * 判断是否支持Matrix
         */
        private void isMatrixEnable() {
            //当加载出错时，不可缩放
            if (getScaleType() != ScaleType.CENTER) {
                setScaleType(ScaleType.MATRIX);
            } else {
                mMode = MODE_UNABLE;//设置为不支持手势
            }
        }

        /**
         * 计算两个手指间的距离
         *
         * @param event
         * @return
         */
        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            /** 使用勾股定理返回两点之间的距离 */
            return (float) Math.sqrt(dx * dx + dy * dy);
        }

        /**
         * 双击时触发
         */
        public void onDoubleClick() {
            float scale = isZoomChanged() ? 1 : mDobleClickScale;
            mCurrentMatrix.set(mMatrix);//初始化Matrix
            mCurrentMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            setImageMatrix(mCurrentMatrix);
        }
    }


    private class GestureListener extends SimpleOnGestureListener {
        private final MatrixTouchListener listener;

        public GestureListener(MatrixTouchListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            //捕获Down事件
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //触发双击事件
            listener.onDoubleClick();
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            // TODO Auto-generated method stub

            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub
            super.onShowPress(e);
        }


        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            // TODO Auto-generated method stub
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // TODO Auto-generated method stub
            return super.onSingleTapConfirmed(e);
        }

    }


}