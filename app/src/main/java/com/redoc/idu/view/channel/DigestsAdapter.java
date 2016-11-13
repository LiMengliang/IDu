package com.redoc.idu.view.channel;

import android.support.v7.widget.RecyclerView;

import com.redoc.idu.contract.IChannel;

/**
 * Digests adapter.
 * Created by limen on 2016/10/25.
 */
public abstract class DigestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IChannel.IChannelPresenter mChannelPresenter;

    /**
     * {@inheritDoc}
     */
    public void setPresenter(IChannel.IChannelPresenter channelPresenter) {
        mChannelPresenter = channelPresenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}


