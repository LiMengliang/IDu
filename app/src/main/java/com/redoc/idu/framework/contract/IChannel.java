package com.redoc.idu.framework.contract;

import android.support.v4.app.Fragment;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;
import com.redoc.idu.framework.view.channel.DigestsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Contract interface of channel view and presenter.
 * Created by limen on 2016/8/25.
 */
public interface IChannel {

    /**
     * Channel view.
     */
    interface IChannelView extends IView<IChannelPresenter> {

        /**
         * Get the fragment contained in the channel view.
         * @return Root fragment.
         */
        Fragment getRootFragment();

        /**
         * Update digests in this channel.
         * @param needToUpdateContent If need to update content.
         */
        void updateDigests(boolean needToUpdateContent);

        /**
         * Start get latest.
         */
        void startGetLatest();
    }

    /**
     * Channel presenter.
     */
    interface IChannelPresenter extends IPresenter {
        /**
         * Get Channel Id.
         * @return Channel Id.
         */
        String getChannelId();

        /**
         * Get digests link.
         * @param index Index of the digests frame.
         * @return Link to the digests of specific frame.
         */
        String getChannelDigestsLink(int index);

        /**
         * Get Channel name.
         * @return Channel name.
         */
        String getChannelName();

        /**
         * Get or create channel view.
         * @return A channel view.
         */
        IChannelView getOrCreateChannelView();

        /**
         * Get all cached digests.
         * @return All cached digests.
         */
        List<IDigest.IDigestPresenter> allCachedDigests();

        /**
         * Get more digests.
         * @param index Index of frame.
         */
        void getMoreDigests(int index);

        /**
         * Get latest digests.
         */
        void getLatestDigests();

        /**
         * Create adapter for digests view.
         * @return Digests adapter.
         */
        DigestsAdapter createDigestsAdapter();

        /**
         * Action to be taken when receive more digests.
         * @param jsonObject The json object received.
         * @throws JSONException Throw json exception.
         */
        void onReceiveMoreDigests(JSONObject jsonObject) throws JSONException;

        /**
         * Action to be taken when receive latest digests.
         * @param jsonObject The json object received.
         * @throws JSONException Throw json exception.
         */
        void onReceiveLatestDigests(JSONObject jsonObject) throws JSONException;
    }
}
