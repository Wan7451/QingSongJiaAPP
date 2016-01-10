package com.wan7451.image;

import android.support.v7.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {

//    private int default_res = R.drawable.default_icon;
//    private ProgressBar progressBar;
//    private ImageView view;
//    private PhotoViewAttacher mAttacher;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image);
//        view = (ImageView) findViewById(R.id.image_icon);
//        progressBar = (ProgressBar) findViewById(R.id.image_progress);
//
//
//        default_res = getIntent().getIntExtra("default_image", R.drawable.default_icon);
//        String uri = getIntent().getStringExtra("uri");
//        String remotepath = getIntent().getExtras().getString("remotepath");
//        String secret = getIntent().getExtras().getString("secret");
//
//
//        BitmapUtils utils = new BitmapUtils(getContext());
//        mAttacher = new PhotoViewAttacher(view);
//
//        //本地存在，直接显示本地的图片
//        if (uri != null) {
//            File local = new File(uri);
//            if (local.exists()) {
//
//                utils.display(view, uri, new BitmapLoadCallBack<ImageView>() {
//                    @Override
//                    public void onLoadCompleted(ImageView touchImageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
//                        view.setImageBitmap(bitmap);
//                        progressBar.setVisibility(View.GONE);
//                        mAttacher.update();
//                    }
//
//                    @Override
//                    public void onLoadFailed(ImageView touchImageView, String s, Drawable drawable) {
//
//                    }
//                });
//
////                Bitmap mp = BitmapFactory.decodeFile(uri);
////                view.setImageBitmap(mp);
//
////                ImageLoader.getInstance().loadLocalImage(uri, view, new ImageLoader.OnLoadFinished() {
////                    @Override
////                    public void loadFinished(String path,Bitmap bm) {
////                    }
////                });
//            }
//        } else if (remotepath != null) {
//
//            utils.display(view, remotepath, new BitmapLoadCallBack<ImageView>() {
//                @Override
//                public void onLoadCompleted(ImageView touchImageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
//                    view.setImageBitmap(bitmap);
//                    progressBar.setVisibility(View.GONE);
//                    mAttacher.update();
//                }
//
//                @Override
//                public void onLoadFailed(ImageView touchImageView, String s, Drawable drawable) {
//
//                }
//            });
//
////            ImageLoader.getInstance().loadImage(remotepath, view, new ImageLoader.OnLoadFinished() {
////                @Override
////                public void loadFinished(String path,Bitmap bm) {
////                }
////            });
//        }
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//    }


}
