package com.redoc.idu.contract;

import com.redoc.idu.IView;

import java.util.List;

/**
 * Contract of multi-channel category view and presenter.
 * Created by limen on 2016/8/25.
 */
public interface IMultiChannelsCategoryContract {
    /**
     * Multi-channels category view.
     */
    interface IMultiChannelsCategoryView extends ICategoryContract.ICategoryView, IView<IMultiChannelsCategoryPresenter> {

        /**
         * Initialize a category view.
         */
        void initialize();

        /**
         * Switch to channel.
         * @param channelPresenter
         */
        void switchToChannel(IChannelContract.IChannelPresenter channelPresenter);
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
