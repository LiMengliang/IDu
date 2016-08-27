package com.redoc.idu.contract;

import com.redoc.idu.IView;

import java.util.List;

/**
 * Contract of multi-channel category view and presenter.
 * Created by limen on 2016/8/25.
 */
public interface IMultiChannelsCategoryContract extends IView<IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter> {
    /**
     * Multi-channels category view.
     */
    interface IMultiChannelsCategoryView extends ICategoryContract.ICategoryView {
        /**
         * Set channels.
         * @param channelPresenters A list of presenter of channel.
         */
        void setChannels(List<IChannelContract.IChannelPresenter> channelPresenters);
    }

    /**
     * Multi-channels category presenter.
     */
    interface IMultiChannelsCategoryPresenter extends ICategoryContract.ICategoryPresenter {
        /**
         * On a channel is selected.
         * @param channelPresenter Presenter of selected channel.
         */
        void onSelectAChannel(IChannelContract.IChannelPresenter channelPresenter);

        /**
         * On follow a channel.
         * @param followedChannel Presenter of the followed channel.
         */
        void onFollowAChannel(IChannelContract.IChannelPresenter followedChannel);

        /**
         * On unfollow a channel.
         * @param unfollowedChannel Presenter of the unfollowed channel.
         */
        void onUnfollowAChannel(IChannelContract.IChannelPresenter unfollowedChannel);

        /**
         * Get all channels in this category.
         * @return All channels of this category.
         */
        List<IChannelContract.IChannelPresenter> getAllChannels();

        /**
         * Get all followed channels.
         * @return Presenters of all followed channels.
         */
        List<IChannelContract.IChannelPresenter> getAllFollowedChannels();

        /**
         * Get selected channel.
         * @return Presenter of the selected channel.
         */
        IChannelContract.IChannelPresenter getSelectedChannel();
    }
}
