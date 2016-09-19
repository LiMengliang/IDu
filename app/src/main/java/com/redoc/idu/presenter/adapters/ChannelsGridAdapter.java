package com.redoc.idu.presenter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redoc.idu.contract.IChannelContract;
import com.redoc.idu.view.multiChannel.ChannelItemView;

import java.util.List;

/**
 * Adapter to MultiChannelsManagerView
 * Created by limen on 2016/9/17.
 */
public class ChannelsGridAdapter extends RecyclerView.Adapter<ChannelsGridAdapter.ChannelsItemViewHolder> {

    /**
     * Presenters to channels.
     */
    private List<IChannelContract.IChannelPresenter> mChannels;

    /**
     * Construct a {@link ChannelsGridAdapter}
     * @param channels Presenters to channels.
     */
    public ChannelsGridAdapter(List<IChannelContract.IChannelPresenter> channels) {
        this.mChannels = channels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChannelsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChannelsItemViewHolder(new ChannelItemView(parent.getContext()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(ChannelsItemViewHolder holder, int position) {
        holder.updateChannel(mChannels.get(position));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mChannels.size();
    }

    /**
     * View holder of chanel item view.
     */
    class ChannelsItemViewHolder extends RecyclerView.ViewHolder {
        /**
         * Construct a {@link ChannelsItemViewHolder} instance.
         * @param itemView The Channel Item view.
         */
        public ChannelsItemViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * Update Channel Item View according to channel.
         * @param channel The channel.
         */
        public void updateChannel(IChannelContract.IChannelPresenter channel) {
            ((TextView)itemView).setText(channel.getChannelName());
            ((ChannelItemView)itemView).setPresenter(channel);
        }
    }
}