package com.redoc.idu.utils.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.redoc.idu.R;
import com.redoc.idu.utils.cache.CacheManager;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A http client.
 * Created by limen on 2016/10/31.
 */
public class HttpClient {
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    /**
     * Construct a http client.
     * @param context Context.
     */
    public HttpClient(Context context, CacheManager cacheManager) {
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new HttpImageCache(cacheManager));
    }

    /**
     * Add json object request
     * @param url Url
     * @param jsonRequest An json request.
     * @param listener On received listener.
     * @param errorListener Error listener.
     */
    public void addJsonObjectRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        mRequestQueue.add(new JsonObjectRequest(url, jsonRequest, listener, errorListener));
    }

    /**
     * Add json array request.
     * @param url Url
     * @param listener Json array response listener.
     * @param errorListener Error listener.
     */
    public void addJsonArrayRequest(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        mRequestQueue.add(new JsonArrayRequest(url, listener, errorListener));
    }

    public void addStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        mRequestQueue.add(new StringRequest(url, listener, errorListener));
    }

    /**
     * Add a image request
     * @param url Request url.
     * @param listener Complete listener
     * @param maxWidth Max width
     * @param maxHeight Max height
     * @param decodeConfig Decoding configuration
     * @param errorListener Error listener.
     */
    public void addImageRequest(String url, Response.Listener<Bitmap> listener, int maxWidth,
                                int maxHeight, Bitmap.Config decodeConfig,
                                Response.ErrorListener errorListener) {
        mRequestQueue.add(new ImageRequest(url, listener, maxWidth, maxHeight, decodeConfig, errorListener));
    }

    /**
     * Display a image to image viewer
     * @param url Image url.
     * @param imageView The image view to display.
     */
    public void displayImage(String url, ImageView imageView) {
        ImageLoader.ImageListener imageListener = mImageLoader.getImageListener(imageView, R.color.light_gray, R.color.light_gray);
        mImageLoader.get(url, imageListener);
    }

    static class HttpImageCache implements ImageLoader.ImageCache {

        private CacheManager mCacheManager;
        public HttpImageCache(CacheManager cacheManager) {
            mCacheManager = cacheManager;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Bitmap getBitmap(String url) {
            return mCacheManager.readBitmap(url);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCacheManager.writeBitmap(url, bitmap);
        }
    }

    /**
     * Bitmap response listener.
     */
    public static class BitmapResponseListener implements Response.Listener<Bitmap> {

        private ImageView mImageView;

        /**
         * Construct a {@link BitmapResponseListener}
         * @param imageView imageView.
         */
        public BitmapResponseListener(ImageView imageView) {
            mImageView = imageView;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onResponse(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
        }
    }
}
