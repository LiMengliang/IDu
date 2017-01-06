package com.redoc.idu.utils.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.redoc.idu.R;

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
    public HttpClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new HttpImageCache());
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

    public void displayImage(String url, ImageView imageView) {
        ImageLoader.ImageListener imageListener = mImageLoader.getImageListener(imageView, R.color.light_gray, R.color.light_gray);
        mImageLoader.get(url, imageListener);
    }

    static class HttpImageCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;
        public HttpImageCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }
        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }
}
