package com.wan7451.choicepicture;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wan7451.base.WanActivity;
import com.wan7451.formview.WanTextView;
import com.wan7451.wanadapter.mylibrary.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChoicePicturesActivity extends WanActivity implements ListImageDirPopupWindow.OnImageDirSelected {


    private static final int CAPTURE_PICTURE = 1;

    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 所有的图片
     */
    private List<String> mImgs;

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();

    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

    private int mScreenHeight;

    private ArrayList<String> mData;
    private ChoicePicturesAdapter mAdapter;
    private Uri captureFile;

    public int totalCount;

    private ProgressDialog mProgressDialog;
    private ArrayList<String> allImages;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
//            mProgressDialog.dismiss();
            // 为View绑定数据
            data2View();
            // 初始化展示文件夹的popupWindw
            initListDirPopupWindw();
            chiocePath.setClickable(true);
            loading.setVisibility(View.GONE);
        }
    };
    private ListImageDirPopupWindow mListImageDirPopupWindow;
    private RecyclerView mRecycleView;
    private WanTextView chiocePath;
    private WanTextView chioceCount;
    private ProgressBar loading;

    private void initListDirPopupWindw() {
        ImageFloder f = new ImageFloder();
        f.setCount(allImages.size());
        f.setDir("全部");
        f.setFirstImagePath(allImages.get(0));
        mImageFloders.add(0, f);

        mListImageDirPopupWindow = new ListImageDirPopupWindow(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
                mImageFloders, LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.layout_choice_picture, null));

        mListImageDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置选择文件夹的回调
        mListImageDirPopupWindow.setOnImageDirSelected(this);
    }

    private void data2View() {
        if (mImgDir == null) {
            Toast.makeText(getApplicationContext(), "擦，一张图片没扫描到",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        chioceCount.setText(mData.size() + "张");
        chiocePath.setText("全部");
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
    }

    public static void startChoicePicturesForResult(Activity context, int requestCode) {
        Intent i = new Intent();
        i.setClass(context, ChoicePicturesActivity.class);
        context.startActivityForResult(i, requestCode);
    }


    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_choice_pictures;
    }

    @Override
    protected void initView() {
        super.initView();
        setContentTitle("选择照片");

        setBackFinish();

        setRightText("发送", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> pics = mAdapter.getChoicePictures();
                Intent data = new Intent();
                data.putStringArrayListExtra("FilePaths", pics);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;

        mRecycleView = (RecyclerView) findViewById(R.id.main_view);
        chiocePath = (WanTextView) findViewById(R.id.choice_path);
        chioceCount = (WanTextView) findViewById(R.id.choice_count);
        loading = (ProgressBar) findViewById(R.id.choice_loading);

        mData = new ArrayList<>();
        allImages = new ArrayList<>();
        mAdapter = new ChoicePicturesAdapter(this, mData);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRecycleView.setLayoutManager(manager);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ChoicePicturesAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                if (position == 0) {

                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(getExternalCacheDir(), "temp.jpg");

                    captureFile = Uri.fromFile(f);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, captureFile);
                    startActivityForResult(i, CAPTURE_PICTURE);
                }
            }
        });

        getImages();

        chiocePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListImageDirPopupWindow
                        .setAnimationStyle(R.style.anim_popup_dir);
                mListImageDirPopupWindow.showAsDropDown(v, 0, 0);

                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = .3f;
                getWindow().setAttributes(lp);
            }
        });
        chiocePath.setClickable(false);
    }


    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }

        File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File camera = new File(pictures, "Camera");
        if (camera.exists()) {
            if (camera != null && camera.listFiles() != null && camera.list().length > 0) {
                File[] files = camera.listFiles();
                for (int i = 0; i < files.length; i++) {
                    mData.add(files[i].getPath());
                }
                mAdapter.notifyDataSetChanged();
            }
        }

        // 显示进度条
//        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {

            @Override
            public void run() {

                String firstImage = null;

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    allImages.add(path);
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    if (parentFile.list() == null || parentFile.list().length == 0)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }

                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            return filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg");
                        }
                    }).length;
                    totalCount += picSize;

                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);

                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;
                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);

            }
        }).start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_PICTURE) {
            Intent i = new Intent();
            i.putExtra("FilePath", captureFile.getPath());
            setResult(RESULT_OK, i);
            this.finish();
        }
    }


    @Override
    public void selected(ImageFloder floder) {

        if (TextUtils.isEmpty(floder.getDir())) {

            mData.clear();
            mData.addAll(allImages);
            mAdapter.setImgDir(null);

            chiocePath.setText("全部");
            chioceCount.setText(allImages.size() + "张");

            mListImageDirPopupWindow.dismiss();

            return;
        }


        mImgDir = new File(floder.getDir());
        mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg");
            }
        }));
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mData.clear();
        mData.addAll(mImgs);
        mAdapter.setImgDir(mImgDir);

        chiocePath.setText(floder.getName());
        chioceCount.setText(floder.getCount() + "张");

        mListImageDirPopupWindow.dismiss();
    }
}
