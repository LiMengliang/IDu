package com.redoc.idu.contract.multichannel;

import com.redoc.idu.IView;
import com.redoc.idu.contract.ICategory;

import java.util.List;

/**
 * Contract of multi-channel category view and presenter.
 * Created by limen on 2016/8/25.
 */
public interface IMultiChannelsCategoryContract {
    /**
     * Multi-channels category view.
     */
    interface IMultiChannelsCategoryView extends ICategory.ICategoryView, IView<IMultiChannelsCategoryPresenter> {

        /**
         * Initialize a category view.
         */
        void initialize();

        /**
         * Switch to channel.
         * @param selectedChannel Selected channel.
         */
        void switchToChannel(IMultiChannelContract.IMultiChannelPresenter selectedChannel);

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
    interface IMultiChannelsCategoryPresenter extends ICategory.ICategoryPresenter {

        /**
         * Select the nth channel from all followed channels
         * @param index Index.
         */
        void selectChannel(int index);

        /**
         * Update channel selection
         */
        void updateChannelSelection();

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
