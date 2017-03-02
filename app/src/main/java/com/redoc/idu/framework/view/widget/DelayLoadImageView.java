package com.redoc.idu.framework.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.redoc.idu.utils.image.BitmapUtils;
import com.redoc.widget.PullToLoadMoreRecyclerView;

import java.lang.ref.WeakReference;

/**
 * Delay load image view.
 * Created by meli on 2016/11/13.
 */

public class DelayLoadImageView extends ImageView {

    /**
     * Delay image view listener.
     */
    public interface IDelayLoadImageListener {
        /**
         * On loaded.
         */
        void onLoaded();
    }

    private int mResourceId = Integer.MIN_VALUE;
    private String mUri;
    private IDelayLoadImageListener mDelayLoadImageListener;

    private AsyncTask mImageLoadingTask;

    /**
     * Constructor.
     * @param context Context.
     */
    public DelayLoadImageView(Context context) {
        super(context);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     */
    public DelayLoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyleAttr Default style.
     */
    public DelayLoadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Set resource id.
     * @param resourceId
     */
    public void setImageSource(int resourceId) {
        mResourceId = resourceId;
    }

    /**
     * Set image source url.
     * @param uri
     */
    public void setImageSource(String uri) {
        mUri = uri;
    }

    /**
     * Set delay load listener.
     * @param listener Delay load listener.
     */
    public void setDelayLoadImageListener(IDelayLoadImageListener listener) {
        mDelayLoadImageListener = listener;
    }

    /**
     * Load image async.
     * @param url Url.
     */
    public void loadImageAsync(String url) {

        if(mImageLoadingTask != null && mImageLoadingTask.getStatus() != AsyncTask.Status.FINISHED) {
            mImageLoadingTask.cancel(false);
        }
            BitmapFactory.Options options = new BitmapFactory.Options();
                    //设置为true,表示解析Bitmap对象，该对象不占内存
                    //options.inJustDecodeBounds = false;
            try{
                ImageLoadTask imageLoadTask = new ImageLoadTask(this);

                mImageLoadingTask = imageLoadTask;
                imageLoadTask.execute(new ImageLoadInfo(url, getLayoutParams().width, getLayoutParams().height));
            }
            catch (Exception e)  {

            }
    }

    /**
     * On image loaded.
     */
    public void onImageLoaded() {
        mDelayLoadImageListener.onLoaded();
    }


    /**
     * Image load information.
     */
    static class ImageLoadInfo {
        private String mUri;
        private int mWidth;
        private int mHeight;
        private Bitmap mBitmap;

        /**
         * Bitmap of the image.
          * @return The bitmap.
         */
        public Bitmap getBitmap() {
            return mBitmap;
        }

        /**
         * Set bitmap
         * @param mBitmap The bitmap.
         */
        public void setBitmap(Bitmap mBitmap) {
            this.mBitmap = mBitmap;
        }

        /**
         * Get url of the image.
         * @return
         */
        public String getUri() {
            return mUri;
        }

        /**
         * Get width of the image.
         * @return
         */
        public int getWidth() {
            return mWidth;
        }

        /**
         * Get height of the image.
         * @return
         */
        public int getHeight() {
            return mHeight;
        }

        /**
         * Constructor.
         * @param url Url
         * @param width Width
         * @param height Height
         */
        public ImageLoadInfo(String url, int width, int height) {
            mUri = url;
            mWidth = width;
            mHeight = height;
        }
    }

    /**
     * Load image task.
      */
    class ImageLoadTask extends AsyncTask<ImageLoadInfo, Integer, ImageLoadInfo> {

        private WeakReference<DelayLoadImageView> mImageView;

        /**
         * Constructor.
         * @param imageView Delay load image view.
         */
        public ImageLoadTask(DelayLoadImageView imageView) {
            mImageView = new WeakReference<>(imageView);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected ImageLoadInfo doInBackground(ImageLoadInfo... params) {
            ImageLoadInfo loadInfo = params[0];
            loadInfo.setBitmap(BitmapUtils.decodeThumbBitmapForFile(loadInfo.getUri(), loadInfo.getWidth(), loadInfo.getHeight()));
            return loadInfo;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPostExecute(ImageLoadInfo result) {
            if(result.getBitmap() != null) {
                mImageView.get().setImageBitmap(result.getBitmap());
                mImageView.get().onImageLoaded();
            }
        }
    }
}
