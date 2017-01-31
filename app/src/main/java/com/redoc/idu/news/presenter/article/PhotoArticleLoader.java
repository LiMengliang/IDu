package com.redoc.idu.news.presenter.article;

import android.os.Parcel;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.redoc.idu.IDuApplication;
import com.redoc.idu.framework.contract.article.IArticleContract;
import com.redoc.idu.framework.presenter.article.IArticleLoader;
import com.redoc.idu.utils.json.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Photo article loader.
 * Created by limen on 2017/1/28.
 */

public class PhotoArticleLoader implements IArticleLoader {

    private IArticleContract.IArticlePresenter mPhotoArticlePresenter;
    private IArticleLoadListener mLoadListener;
    private String mUrl;

    /**
     * Construct a photo article loader.
     * @param source Parcel.
     */
    public PhotoArticleLoader(Parcel source) {
        mUrl = source.readString();
    }

    /**
     * Url.
     * @param url Url.
     */
    public PhotoArticleLoader(String url) {
        mUrl = url;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(IArticleLoadListener loadListener) {
        mLoadListener = loadListener;
        IDuApplication.HttpClient.addJsonObjectRequest(mUrl, null, new PhotoInfoResponse(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUrl);
    }

    /**
     * A creator
     */
    public static final Creator<IArticleLoader> CREATOR = new Creator<IArticleLoader>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public IArticleLoader createFromParcel(Parcel source) {
            return new PhotoArticleLoader(source);
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public IArticleLoader[] newArray(int size) {
            return new TextArticleHtmlLoader[0];
        }
    };

    /**
     * Raw photo info json responser.
     */
    class PhotoInfoResponse implements Response.Listener<JSONObject> {
        /**
         * {@inheritDoc}
         */
        @Override
        public void onResponse(JSONObject jsonObject) {
            try {
                JSONArray photoInfos = JsonUtils.getJsonArray("photos", jsonObject);
                HashMap<String, String> photos = new HashMap<>();
                for(int i = 0; i < photoInfos.length(); i++) {
                    JSONObject photoInfo = (JSONObject) photoInfos.get(i);
                    String imageUrl = JsonUtils.getString("imgurl", photoInfo);
                    String note = JsonUtils.getString("note", photoInfo);
                    photos.put(imageUrl, note);
                }
                mLoadListener.onLoaded(photos);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
