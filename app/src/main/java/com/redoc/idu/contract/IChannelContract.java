package com.redoc.idu.contract;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

/**
 * Contract interface of channel view and presenter.
 * Created by limen on 2016/8/25.
 */
public interface IChannelContract {

    /**
     * Channel view.
     */
    interface IChannelView extends IView<IChannelPresenter> {

    }

    /**
     * Channel presenter.
     */
    interface IChannelPresenter extends IPresenter {
        String getChannelName();
    }
}
