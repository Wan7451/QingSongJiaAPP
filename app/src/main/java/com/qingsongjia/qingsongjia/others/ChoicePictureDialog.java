package com.qingsongjia.qingsongjia.others;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qingsongjia.qingsongjia.R;
import com.qingsongjia.qingsongjia.localdata.FileManager;
import com.qingsongjia.qingsongjia.localdata.LocalPreference;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.utils.UrlSafeBase64;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by wanggang on 15/12/17.
 */
public class ChoicePictureDialog {

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果


    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    /**
     * 裁剪后图片路径
     **/
    private static final String PHOTO_CROP_NAME = "temp_crop.jpg";
    private File tempFile;

    private Activity context;
    private SimpleDraweeView icon;
    private AlertDialog dialog;

    private String upPath;

    private File cropFile;
    private File captureFile;

    public ChoicePictureDialog(final Activity context,String upPath, SimpleDraweeView icon) {

        this.upPath=upPath;
        this.context = context;
        this.icon=icon;

        String[] items = {"拍照", "相册"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    //拍照
                    camera(context);
                } else if (i == 1) {
                    //相册
                    gallery(context);
                }
            }
        });
        dialog = builder.show();
    }

    /*
         * 上传图片
         */
    public void upload() {
        try {

            String _uploadToken = QiniuUtils.getUploadToken();
            UploadManager uploadManager = new UploadManager();

            final UpProgressHandler handler = new UpProgressHandler() {
                @Override
                public void progress(String key, double percent) {
                }
            };
            UploadOptions options = new UploadOptions(null, null, false, handler, null);

            String key = System.currentTimeMillis() +
                    LocalPreference.getCurrentUser(context).getId()
                    + "icon.jpg";



            //上传图片的key,文件名字
            uploadManager.put(cropFile, key, _uploadToken,
                    new UpCompletionHandler() {
                        @Override
                        public void complete(String key, final ResponseInfo info,
                                             org.json.JSONObject response) {
                            if (info.isOK()) {
                                Uri uri=  Uri.fromFile(cropFile);
                                //清除缓存
                                Fresco.getImagePipeline().evictFromMemoryCache(uri);
                                icon.setImageURI(uri);
                                upPath=QiniuUtils.IMAGE_URL_HEADER+key;
                                icon.setTag(upPath);
                            } else {
                                Toast.makeText(context, "头像上传失败，请重试", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, options);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 从相册获取
     */
    public void gallery(Activity activity) {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 从相机获取
     */
    public void camera(Activity activity) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            captureFile = new File(FileManager.getCacheImageFile(context),
                    PHOTO_FILE_NAME);
            if(captureFile.exists()){
                captureFile.delete();
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(
                            captureFile));
        }
        activity.startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(context, uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
//                tempFile = new File(FileManager.getCacheImageFile(context),
//                        PHOTO_FILE_NAME);
                crop(context, Uri.fromFile(captureFile));
            } else {
                Toast.makeText(context, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {

                upload();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 剪切图片
     *
     * @param uri
     * @function:
     * @author:Jerry
     * @date:2013-12-30
     */
    private void crop(Activity activity, Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        cropFile = new File(FileManager.getCacheImageFile(context), PHOTO_CROP_NAME);
        if(cropFile.exists()){
            cropFile.delete();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(cropFile)
        );
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }



}
