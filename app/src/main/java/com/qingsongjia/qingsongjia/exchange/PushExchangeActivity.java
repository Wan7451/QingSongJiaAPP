package com.qingsongjia.qingsongjia.exchange;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.localdata.FileManager;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.others.QiniuUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.wan7451.base.WanActivity;
import com.wan7451.utils.BitmapUtil;
import com.wan7451.wanadapter.list.CommonAdapter;
import com.wan7451.wanadapter.list.ViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PushExchangeActivity extends WanActivity {


    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择


    @Bind(R.id.exchange_text)
    EditText exchangeText;
    @Bind(R.id.exchange_camera)
    LinearLayout exchangeCamera;
    @Bind(R.id.exchange_gallery)
    LinearLayout exchangeGallery;
    @Bind(R.id.exchange_imgs)
    GridView exchangeImgs;

    private String upText;
    private String upImgs;

    private ArrayList<File> imgs = new ArrayList<>();
    private File captureFile;
    private ImageAdapter adapter;
    private ProgressDialog dialog;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("发表话题");
        setRightText("发送", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push();

            }
        });

        exchangeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera();
            }
        });

        exchangeGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gallery();

            }
        });

        adapter = new ImageAdapter(getContext(), imgs, R.layout.item_exchange_imgs);
        exchangeImgs.setAdapter(adapter);
    }

    private void push() {
        upText = exchangeText.getText().toString();
        if (TextUtils.isEmpty(upText)) {
            showToast("内容不能为空！");
            return;
        }

        dialog = new ProgressDialog(getContext());
        if (imgs.size() <= 1) {
            dialog.setMessage("发布中....");
        } else {
            dialog.setMessage("正在上传图片中(" + currCount + "/" + (imgs.size() - 1) + ").....");
        }
        dialog.setCancelable(false);
        dialog.show();
        //上传图片
        handleImageUp();
    }

    int currCount = 1;
    StringBuffer uploadPicUrls = new StringBuffer();

    private void handleImageUp() {

        if (currCount >= imgs.size()) {
            pushRequest(uploadPicUrls);
            return;
        }
        //压缩图片
        BitmapUtil.getCompressByteBitmap(imgs.get(currCount - 1).getPath(),
                new BitmapUtil.OnCompressCompleteListener() {
                    @Override
                    public void onCompressOk(byte[] data) {

                        String _uploadToken = QiniuUtils.getUploadToken();

                        UploadManager uploadManager = new UploadManager();

                        final UpProgressHandler handler = new UpProgressHandler() {
                            @Override
                            public void progress(String key, double percent) {
                            }
                        };
                        UploadOptions options = new UploadOptions(null, null, false, handler, null);

                        String key = generate();
                        //上传图片的key,文件名字
                        uploadManager.put(data, key, _uploadToken,
                                new UpCompletionHandler() {
                                    @Override
                                    public void complete(String key, final ResponseInfo info,
                                                         org.json.JSONObject response) {
                                        if (info.isOK()) {
                                            uploadPicUrls.append(QiniuUtils.IMAGE_URL_HEADER + key);
                                            uploadPicUrls.append(",");
                                            currCount++;
                                            if (currCount <= imgs.size() - 1) {
                                                dialog.setMessage("正在上传图片中(" + currCount + "/" + (imgs.size() - 1) + ").....");
                                            }
                                            handleImageUp();
                                        } else {
                                            showLongToast(info.error);
                                            dialog.dismiss();
                                        }
                                    }
                                }, options);

                    }
                });
    }

    private void pushRequest(StringBuffer uploadPicUrls) {
        if (uploadPicUrls.length() > 0) {
            uploadPicUrls.setLength(uploadPicUrls.length() - 1);
        }
        dialog.dismiss();
        Log.i("=======",uploadPicUrls.toString());
    }

    private void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }


    /*
    * 从相机获取
    */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        captureFile = new File(FileManager.getCacheImageFile(getContext()),
                System.currentTimeMillis() + ".png");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(captureFile));
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                File f = new File(uri.getPath());
                imgs.add(f);
                adapter.notifyDataSetChanged();
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            Uri uri = Uri.fromFile(captureFile);
            imgs.add(captureFile);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_push_exchange;
    }


    class ImageAdapter extends CommonAdapter<File> {


        public ImageAdapter(Context context, List<File> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, final int position, File item) {
            SimpleDraweeView icon = helper.getView(R.id.img);
            icon.setImageURI(Uri.fromFile(item));
            helper.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgs.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FileManager.clearImageCache(getContext());
    }

    private String generate() {
        return System.currentTimeMillis() + LocalPreference.getCurrentUser(getContext()).getId() + ".jpg";
    }
}
