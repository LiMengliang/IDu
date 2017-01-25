package com.redoc.idu.framework.contract.multichannel;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

import java.util.List;

/**
 * Created by limen on 2016/9/19.
 * Contract between IMultiChannelManagerView and IMultiChannelManagerPresenter;
 */
public interface IMultiChannelManagerContract {
    /**
     * IMultiChannelManager view.
     */
    interface IMultiChannelManagerView extends IView<IMultiChannelManagerPresenter> {
        /**
         * Follow a channel.
         */
        void followAChannel();

        /**
         * Unfollow a channel.
         */
        void unFollowAChannel();
    }

    /**
     * IMultiChannelManager presenter.
     */
    interface IMultiChannelManagerPresenter extends IPresenter{
        /**
         * Get all channels.
         * @return All channels in this category.
         */
        List<IMultiChannelContract.IMultiChannelPresenter> getAllChannels();

        /**
         * Follow a channel.
         * @param channel The channel followed.
         */
        void followAChannel(IMultiChannelContract.IMultiChannelPresenter channel);

        /**
         * Unfollow a channel.
         * @param channel The channel un-followed.
         */
        void unFollowAChannel(IMultiChannelContract.IMultiChannelPresenter channel);
    }
}
