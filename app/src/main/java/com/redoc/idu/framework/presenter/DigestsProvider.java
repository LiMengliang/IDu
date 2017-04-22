package com.redoc.idu.framework.presenter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.redoc.idu.IDuApplication;
import com.redoc.idu.framework.contract.IChannel;
import com.redoc.idu.utils.network.NetworkConnectionManager;
import com.redoc.idu.utils.notification.NotificationUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Digests provider.
 * Created by limen on 2016/11/2.
 */
public class DigestsProvider {

    private IChannel.IChannelPresenter mChannelPresenter;

    /**
     * Construct a digests provider instance.
     * @param channelPresenter Channel presenter.
     */
    public DigestsProvider(IChannel.IChannelPresenter channelPresenter) {
        mChannelPresenter = channelPresenter;
    }

    /**
     * Fetch latest.
     */
    public void fetchLatest() {

        String url = mChannelPresenter.getChannelDigestsLink(0);
        if(NetworkConnectionManager.isWifiAvailable(IDuApplication.Context)) {
            IDuApplication.HttpClient.addJsonObjectRequest(url, null, new ResponseListener(true, url),
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            int a = 0;
                        }
                    });
        }
        else {
            String digestsJsonString = IDuApplication.CacheManager.readString(url);
            try {
                mChannelPresenter.onReceiveLatestDigests(new JSONObject(digestsJsonString));
                NotificationUtils.showToast("无网络连接，显示历史结果", false, IDuApplication.Context);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Fetch more.
     * @param index Frame index.
     */
    public void fetchMore(int index) {
        String url = mChannelPresenter.getChannelDigestsLink(index);
        if(NetworkConnectionManager.isWifiAvailable(IDuApplication.Context)) {
            IDuApplication.HttpClient.addJsonObjectRequest(url, null, new ResponseListener(false, url),
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
        }
        else {
            String digestsJsonString = IDuApplication.CacheManager.readString(url);
            try {
                JSONObject digestsJson = new JSONObject(digestsJsonString);
                mChannelPresenter.onReceiveMoreDigests(digestsJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Response listener.
     */
    class ResponseListener implements Response.Listener<JSONObject> {
        private boolean mLatest;
        private String mUrl;

        /**
         * Construct a response listener.
         * @param latest If to get latest digests.
         */
        public ResponseListener(boolean latest, String url) {
            mLatest = latest;
            mUrl = url;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onResponse(JSONObject jsonObject) {
            IDuApplication.CacheManager.writeString(mUrl, jsonObject.toString());
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
