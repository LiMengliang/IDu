package com.redoc.idu.view.channel;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.contract.IChannel;
import com.redoc.idu.contract.IDigest;

/**
 * Digests adapter.
 * Created by limen on 2016/10/25.
 */
public class DigestsAdapter extends RecyclerView.Adapter<DigestsAdapter.DigestViewHolder> {
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
    public DigestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(IDuApplication.Context);
        return new DigestViewHolder(textView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mChannelPresenter.allCachedDigests().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(DigestViewHolder holder, int position) {
        holder.textView.setText(mChannelPresenter.allCachedDigests().get(position).getTitle());
    }

    /**
     * Digest view hodler.
     */
    class DigestViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        /**
         * Construct a digest view holder.
         * @param itemView The view to be hold.
         */
        public DigestViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView;
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(14);
            textView.setHeight(150);
        }
    }
}


