package com.qingsongjia.qingsongjia.exchange;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.localdata.FileManager;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qingsongjia.qingsongjia.utils.NetRequest;
import com.qingsongjia.qingsongjia.utils.NetUtils;
import com.qingsongjia.qingsongjia.utils.QiniuUtils;
import com.qingsongjia.qingsongjia.utils.UIManager;
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

public class ReplyActivity extends WanActivity {


//    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
//    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择

    @Bind(R.id.exchange_text)
    EditText exchangeText;
//    @Bind(R.id.exchange_camera)
//    LinearLayout exchangeCamera;
//    @Bind(R.id.exchange_gallery)
//    LinearLayout exchangeGallery;
//    @Bind(R.id.exchange_imgs)
//    GridView exchangeImgs;

    private String upText;
//    private String upImg;

    //    private ArrayList<File> imgs = new ArrayList<>();
//    private File captureFile;
//    private ImageAdapter adapter;
//    private ProgressDialog dialog;
    private int replyId;
    private int type;

    @Override
    public void initView() {

        //判断是否登录
        if (TextUtils.isEmpty(LocalPreference.getCurrentUser(getContext()).getDri_type())) {
            UIManager.startLogin(getContext());
            finish();
            return;
        }

        ButterKnife.bind(this);
        setBackFinish();
        setContentTitle("回复");
        setRightText("发送", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push();
            }
        });

        int posotion = getIntent().getIntExtra("posotion", 0);
        replyId = getIntent().getIntExtra("replyId", 0);
        type = getIntent().getIntExtra("type", 0);
        if (posotion == -1) {
            setContentTitle("回帖");
        } else {
            setContentTitle("回复" + (posotion + 1) + "楼");
        }

//        exchangeCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (imgs.size() > 0) {
//                    showToast("只能回复一张图片");
//                    return;
//                }
//                camera();
//            }
//        });
//
//        exchangeGallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (imgs.size() > 0) {
//                    showToast("只能回复一张图片");
//                    return;
//                }
//                gallery();
//            }
//        });
//
//        adapter = new ImageAdapter(getContext(), imgs, R.layout.item_exchange_imgs);
//        exchangeImgs.setAdapter(adapter);
    }


    private void push() {
        upText = exchangeText.getText().toString();
        if (TextUtils.isEmpty(upText)) {
            showToast("内容不能为空！");
            return;
        }

//        private String dri_text;//评论内容
//        private String dri_reply_id ;//帖子ID
//        private Integer create_id;//（创建人ID）

        if (type == 1) {

            NetRequest.replyExchange(getContext(), upText, replyId, new NetUtils.NetUtilsHandler() {
                @Override
                public void onResponseOK(JSONArray response, int total) {

                    showToast("评论成功");
                    finish();
                }

                @Override
                public void onResponseError(String error) {
                    if (TextUtils.isEmpty(error)) {
                        showToast("评论失败");
                    } else {
                        showToast(error);
                    }
                }
            });

        } else if (type == 2) {

            NetRequest.replyExchangeReply(getContext(), upText, replyId, new NetUtils.NetUtilsHandler() {
                @Override
                public void onResponseOK(JSONArray response, int total) {

                    showToast("评论成功");
                    finish();
                }

                @Override
                public void onResponseError(String error) {
                    if (TextUtils.isEmpty(error)) {
                        showToast("评论失败");
                    } else {
                        showToast(error);
                    }
                }
            });
        }

//        if (imgs.size() == 0) {
//            pushRequest("");
//        } else {
//
//            dialog = new ProgressDialog(getContext());
//            dialog.setMessage("正在上传图片中.....");
//            dialog.setCancelable(false);
//            dialog.show();
//            //上传图片
//            handleImageUp();
//        }
    }


//    private void handleImageUp() {
//
//        //压缩图片
//        BitmapUtil.getCompressByteBitmap(imgs.get(0).getPath(),
//                new BitmapUtil.OnCompressCompleteListener() {
//                    @Override
//                    public void onCompressOk(byte[] data) {
//
//                        String _uploadToken = QiniuUtils.getUploadToken();
//
//                        UploadManager uploadManager = new UploadManager();
//
//                        final UpProgressHandler handler = new UpProgressHandler() {
//                            @Override
//                            public void progress(String key, double percent) {
//                            }
//                        };
//                        UploadOptions options = new UploadOptions(null, null, false, handler, null);
//
//                        String key = QiniuUtils.generate(getContext());
//                        //上传图片的key,文件名字
//                        uploadManager.put(data, key, _uploadToken,
//                                new UpCompletionHandler() {
//                                    @Override
//                                    public void complete(String key, final ResponseInfo info,
//                                                         org.json.JSONObject response) {
//                                        if (info.isOK()) {
//                                            String upPath = QiniuUtils.IMAGE_URL_HEADER + key;
//                                            pushRequest(upPath);
//                                        } else {
//                                            showLongToast(info.error);
//                                            dialog.dismiss();
//                                        }
//                                    }
//                                }, options);
//
//                    }
//                });
//    }
//
//    private void pushRequest(String uploadPicUrls) {
//        if (dialog != null)
//            dialog.dismiss();
//        upText = exchangeText.getText().toString();
//
//        Log.i("=======", upText);
//        Log.i("=======", uploadPicUrls);
//
//
//
//    }
//
//    private void gallery() {
//        // 激活系统图库，选择一张图片
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
//    }
//
//
//    /*
//    * 从相机获取
//    */
//    public void camera() {
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        // 判断存储卡是否可以用，可用进行存储
//        captureFile = new File(FileManager.getCacheImageFile(getContext()),
//                System.currentTimeMillis() + ".png");
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                Uri.fromFile(captureFile));
//        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PHOTO_REQUEST_GALLERY) {
//            if (resultCode == RESULT_OK && data != null) {
//                // 得到图片的全路径
//                Uri uri = data.getData();
//                File f = new File(uri.getPath());
//                imgs.add(f);
//                adapter.notifyDataSetChanged();
//            }
//
//        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
//            Uri uri = Uri.fromFile(captureFile);
//            if (resultCode == RESULT_OK && captureFile != null) {
//                imgs.add(captureFile);
//                adapter.notifyDataSetChanged();
//            }
//        }
//    }
//
//    class ImageAdapter extends CommonAdapter<File> {
//
//
//        public ImageAdapter(Context context, List<File> mDatas, int itemLayoutId) {
//            super(context, mDatas, itemLayoutId);
//        }
//
//        @Override
//        public void convert(ViewHolder helper, final int position, File item) {
//            SimpleDraweeView icon = helper.getView(R.id.img);
//            icon.setImageURI(Uri.fromFile(item));
//            helper.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    imgs.remove(position);
//                    adapter.notifyDataSetChanged();
//                }
//            });
//        }
//    }


    @Override
    protected int getMainViewLayoutId() {
        return R.layout.activity_reply;
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        FileManager.clearImageCache(getContext());
//    }

}
