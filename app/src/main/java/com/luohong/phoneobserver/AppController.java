package com.luohong.phoneobserver;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.luohong.phoneobserver.bean.Use;
import com.luohong.phoneobserver.db.UseDb;
import com.luohong.phoneobserver.receiver.ScreenListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * 如果您的工程中实现了Application的继承类，那么，您需要将父类改为com.baidu.frontia.FrontiaApplication。
 * 如果您没有实现Application的继承类，那么，请在AndroidManifest.xml的Application标签中增加属性： 
 * <application android:name="com.baidu.frontia.FrontiaApplication"
 * 。。。
 */
public class AppController extends Application {

	public static final String TAG = AppController.class.getSimpleName();

//	private RequestQueue mRequestQueue;
//	private ImageLoader mImageLoader;
//
//	private GenericDraweeHierarchyBuilder mDraweeBuilder = null;
//	private Drawable mTransparentOverlayDrawable = null;
//
//	private SoftReference<Bitmap> mBlurBitmapRef = null;
//    private SoftReference<List<Music>> mRecommentMusics = null;

	private static AppController mInstance;
    private UseDb mUseDb;


    @Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;

        mUseDb = new UseDb(this);

		ScreenListener mListener = new ScreenListener(this);
		mListener.begin(new ScreenListener.ScreenStateListener() {
			@Override
			public void onScreenOn() {
                mUseDb.insert(new Use("on"));
			}

			@Override
			public void onScreenOff() {
                mUseDb.insert(new Use("off"));
            }

			@Override
			public void onUserPresent() {
                mUseDb.insert(new Use("present"));
			}
		});

        List<Use> list = mUseDb.findAll();
        Log.d(TAG, "use size: " + list.size());

//        String channel = ConfigManager.getInstance().getChannel();
//        boolean isDebug = ConfigManager.getInstance().isDebug();
//
//        LogUtils.LOGGABLE = isDebug;
//
//        // 初始化Facebook Fresco
//		Fresco.initialize(this);
//
//        // 初始化百度统计
//        BaiduStatistics.init(channel);
//
//        // 初始化bugly，设置渠道
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
//        strategy.setAppChannel(channel); //设置渠道
//		CrashReport.initCrashReport(this, "900008867", isDebug, strategy);
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public static Context getAppContext() { return mInstance.getApplicationContext(); }

//	public synchronized RequestQueue getRequestQueue() {
//		if (mRequestQueue == null) {
//			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//		}
//
//		return mRequestQueue;
//	}
//
//	public ImageLoader getImageLoader() {
//		getRequestQueue();
//		if (mImageLoader == null) {
//			mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
//		}
//		return this.mImageLoader;
//	}
//
//	public <T> void addToRequestQueue(Request<T> req, String tag) {
//		// set the default tag if tag is empty
//		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
//		getRequestQueue().add(req);
//	}
//
//	public <T> void addToRequestQueue(Request<T> req) {
//		req.setTag(TAG);
//		getRequestQueue().add(req);
//	}
//
//	public void cancelPendingRequests(Object tag) {
//		if (mRequestQueue != null) {
//			mRequestQueue.cancelAll(tag);
//		}
//	}
//
//	public GenericDraweeHierarchyBuilder getGenericDraweeHierarchyBuilder() {
//		if (mDraweeBuilder == null) {
//			mTransparentOverlayDrawable = new ColorDrawable(getResources().getColor(R.color.transparent));
//			mDraweeBuilder = new GenericDraweeHierarchyBuilder(getResources());
//			mDraweeBuilder.setFadeDuration(300)// 按下效果必须配合overlay使用，否则会crash
//					.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
//		}
//		mDraweeBuilder.setOverlay(mTransparentOverlayDrawable);
//		return mDraweeBuilder;
//	}
//
//	public Drawable getTransparentOverlayDrawable() {
//		return mTransparentOverlayDrawable;
//	}
//
//	public void setBlurBitmap(Bitmap blurBitmap) {
//		if (mBlurBitmapRef != null) {
//			Bitmap bitmap = mBlurBitmapRef.get();
//			if (bitmap != null && !bitmap.isRecycled()) {
//				bitmap.recycle();
//				bitmap = null;
//			}
//			mBlurBitmapRef.clear();
//			mBlurBitmapRef = null;
//		}
//        if (blurBitmap != null && !blurBitmap.isRecycled()) {
//            mBlurBitmapRef = new SoftReference<Bitmap>(blurBitmap);
//        }
//	}
//
//	public Bitmap getBlurBitmap() {
//		if (mBlurBitmapRef != null) {
//			Bitmap bitmap = mBlurBitmapRef.get();
//			if (bitmap != null && !bitmap.isRecycled()) {
//				return bitmap;
//			}
//		}
//		return null;
//	}
//
//    public void setRecommendMusics(List<Music> musicList) {
//        if (mRecommentMusics != null) {
//            List<Music> list = mRecommentMusics.get();
//            if (list != null) {
//                list.clear();
//                list = null;
//            }
//            mRecommentMusics.clear();
//            mRecommentMusics = null;
//        }
//        if (musicList != null && !musicList.isEmpty()) {
//            mRecommentMusics = new SoftReference<List<Music>>(musicList);
//        }
//    }
//
//    public List<Music> getRecommendMusics() {
//        if (mRecommentMusics != null) {
//            return mRecommentMusics.get();
//        }
//        return null;
//    }

}
