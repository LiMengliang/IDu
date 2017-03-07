package com.redoc.idu.framework.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.redoc.idu.utils.image.BitmapUtils;

import java.lang.ref.WeakReference;

/**
 * Async image view, this image view load data in background thread.
 * Created by meli on 2016/11/13.
 */

public class AsyncImageView extends ImageView {

    /**
     * Delay image view listener.
     */
    public interface IDelayLoadImageListener {
        /**
         * On loaded.
         */
        void onLoaded();
    }

    private IDelayLoadImageListener mDelayLoadImageListener;
    private AsyncTask mImageLoadingTask;

    /**
     * Constructor.
     * @param context Context.
     */
    public AsyncImageView(Context context) {
        super(context);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     */
    public AsyncImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyleAttr Default style.
     */
    public AsyncImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        try{
            ImageLoadTask imageLoadTask = new ImageLoadTask(this);

            mImageLoadingTask = imageLoadTask;
            // Use following to load image one after one is finished
            // imageLoadTask.execute(new ImageLoadInfo(url, getLayoutParams().width, getLayoutParams().height));
            // Use following to load images at the same time, with multiple thread in thread pool
            imageLoadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ImageLoadInfo(url, getLayoutParams().width, getLayoutParams().height));
        }
        catch (Exception e) {}
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

        private WeakReference<AsyncImageView> mImageView;

        /**
         * Constructor.
         * @param imageView Delay load image view.
         */
        public ImageLoadTask(AsyncImageView imageView) {
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
