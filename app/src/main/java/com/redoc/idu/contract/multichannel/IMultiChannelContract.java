package com.redoc.idu.contract.multichannel;

import com.redoc.idu.IView;
import com.redoc.idu.contract.IChannel;

/**
 * Created by limen on 2016/9/19.
 * Contract between IMultiChannelItemView and IMultiChannelPresenter
 */
public interface IMultiChannelContract {
    interface IMultiChannelItemView extends IView<IMultiChannelPresenter> {
        /**
         * Set channel item style.
         *
         * @param followed If the channel is followed.
         */
        void setChannelItemStyle(boolean followed);
    }

    interface IMultiChannelPresenter extends IChannel.IChannelPresenter {

        /**
         * Is followed.
         * @return If is followed.
         */
        boolean isFollowed();

        /**
         * Follow the channel.
         */
        void follow();

        /**
         * Unfollow the channel.
         */
        void unfollow();

        /**
         * Get owning category.
         * @return Owning category.
         */
        IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter getCategory();
    }
}
