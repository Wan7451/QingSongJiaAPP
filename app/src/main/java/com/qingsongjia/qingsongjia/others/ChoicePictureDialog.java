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
import com.facebook.drawee.view.SimpleDraweeView;
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

    private Bitmap bitmap;

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
            final File f = new File(FileManager.getCacheImageFile(context), PHOTO_CROP_NAME);

            String _uploadToken = getUploadToken();
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
            uploadManager.put(f, key, _uploadToken,
                    new UpCompletionHandler() {
                        @Override
                        public void complete(String key, final ResponseInfo info,
                                             org.json.JSONObject response) {
                            if (info.isOK()) {
                                icon.setImageURI(Uri.fromFile(f));
                                upPath="http://7xlt5l.com1.z0.glb.clouddn.com/"+key;
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
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(
                            new File(FileManager.getCacheImageFile(context),
                                    PHOTO_FILE_NAME)));
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
                tempFile = new File(FileManager.getCacheImageFile(context),
                        PHOTO_FILE_NAME);
                crop(context, Uri.fromFile(tempFile));
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
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(FileManager.getCacheImageFile(context), PHOTO_CROP_NAME))
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


    public static final String sk = "jYwlKfs605nrNVz5tdKxQ9IUzegG_IKNi1EQPHYl";
    public static final String ak = "p8xOqvSh-zym4DysE8nJ7EmrIgzMzaaLI9X8EeaW";


    private String getUploadToken() {
        try {
            // 1 构造上传策略
            JSONObject _json = new JSONObject();
            long _dataline = System.currentTimeMillis() / 1000 + 3600;
            _json.put("deadline", _dataline);// 有效时间为一个小时
            _json.put("scope", "driverplatform");
            String _encodedPutPolicy = UrlSafeBase64.encodeToString(_json
                    .toString().getBytes());
            byte[] _sign = HmacSHA1Encrypt(_encodedPutPolicy, sk);
            String _encodedSign = UrlSafeBase64.encodeToString(_sign);
            return ak + ':' + _encodedSign + ':'
                    + _encodedPutPolicy;
        } catch (Exception e) {

        }
        return null;
    }

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    /**
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }
}
