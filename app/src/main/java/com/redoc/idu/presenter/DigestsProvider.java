package com.redoc.idu.presenter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.redoc.idu.IDuApplication;
import com.redoc.idu.contract.IChannel;
import com.redoc.idu.utils.network.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Digests provider.
 * Created by limen on 2016/11/2.
 */
public class DigestsProvider {

    private IChannel.IChannelPresenter mChannelPresenter;
    private ResponseListener mOnLatestResponseListener;
    private ResponseListener mOnMoreResponseListener;

    /**
     * Construct a digests provider instance.
     * @param channelPresenter Channel presenter.
     */
    public DigestsProvider(IChannel.IChannelPresenter channelPresenter) {
        mChannelPresenter = channelPresenter;
        mOnLatestResponseListener = new ResponseListener(true);
        mOnMoreResponseListener = new ResponseListener(false);
    }

    /**
     * Fetch latest.
     */
    public void fetchLatest() {
        IDuApplication.HttpClient.addJsonObjectRequest(mChannelPresenter.getChannelDigestsLink(0), null, mOnLatestResponseListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int a = 0;
                    }
                });
    }

    /**
     * Fetch more.
     * @param index Frame index.
     */
    public void fetchMore(int index) {
        IDuApplication.HttpClient.addJsonObjectRequest(mChannelPresenter.getChannelDigestsLink(index), null, mOnMoreResponseListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
    }

    /**
     * Response listener.
     */
    class ResponseListener implements Response.Listener<JSONObject> {
        private boolean mLatest;

        /**
         * Construct a response listener.
         * @param latest If to get latest digests.
         */
        public ResponseListener(boolean latest) {
            mLatest = latest;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onResponse(JSONObject jsonObject) {
            try {
                if(mLatest) {
                    mChannelPresenter.onReceiveLatestDigests(jsonObject);
                }
                else {
                    mChannelPresenter.onReceiveMoreDigests(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
