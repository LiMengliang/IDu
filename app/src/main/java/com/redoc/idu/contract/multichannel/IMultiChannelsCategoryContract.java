package com.redoc.idu.contract.multichannel;

import com.redoc.idu.IView;
import com.redoc.idu.contract.ICategoryContract;
import com.redoc.idu.contract.IChannelContract;

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

        /**
         * Show channel manager.
         */
        void showChannelsManager();

        /**
         * Hide a channel manager.
         */
        void hideChannelsManager();
    }

    /**
     * Multi-channels category presenter.
     */
    interface IMultiChannelsCategoryPresenter extends ICategoryContract.ICategoryPresenter {
        /**
         * On a channel is selected.
         * @param channelPresenter Presenter of selected channel.
         */
        void onSelectAChannel(IMultiChannelContract.IMultiChannelPresenter channelPresenter);

        /**
         * Get all channels in this category.
         * @return All channels of this category.
         */
        List<IMultiChannelContract.IMultiChannelPresenter> getAllChannels();

        /**
         * Get all followed channels.
         * @return Presenters of all followed channels.
         */
        List<IMultiChannelContract.IMultiChannelPresenter> getAllFollowedChannels();

        /**
         * Get selected channel.
         * @return Presenter of the selected channel.
         */
        IMultiChannelContract.IMultiChannelPresenter getSelectedChannel();

        /**
         * Get multiple channel manager.
         * @return
         */
        IMultiChannelManagerContract.IMultiChannelManagerPresenter getMultiChannelManager();

        /**
         * Start manage channel.
         */
        void startManage();

        /**
         * confirm or cancel management.
         * @param confirmOrCancel True means confirm, false means cancel.
         */
        void confirmOrCancelManage(boolean confirmOrCancel);
    }
}
