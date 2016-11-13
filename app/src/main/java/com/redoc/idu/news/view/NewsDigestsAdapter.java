package com.redoc.idu.news.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.contract.IChannel;
import com.redoc.idu.news.model.NewsDigestType;
import com.redoc.idu.news.presenter.NewsDigestPresenter;
import com.redoc.idu.view.channel.DigestsAdapter;

/**
 * Adapter for news digests.
 * Created by meli on 2016/11/12.
 */

public class NewsDigestsAdapter extends DigestsAdapter {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(IDuApplication.Context);
        NewsDigestType digestType = NewsDigestType.values()[viewType];
        switch (digestType) {
            case SingleImageArticle:
                RelativeLayout singleImageArticleDigestView = (RelativeLayout) LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_single_image_article_digest, null);
                return new SingleImageArticleDigestViewHolder(singleImageArticleDigestView);
            case MultiImagesArticle:
                RelativeLayout multiImagesArticleDigestView = (RelativeLayout)LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_multi_images_article_digest, null);
                return new MultiImagesArticleDigestViewHolder(multiImagesArticleDigestView);
            case Images:
                RelativeLayout imagesDigests = (RelativeLayout)LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_images_digest, null);
                return new ImagesDigestViewHolder(imagesDigests);
        }
        return null;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        NewsDigestType digestType = NewsDigestType.values()[type];
        NewsDigestPresenter newsDigestPresenter = (NewsDigestPresenter) mChannelPresenter.allCachedDigests().get(position);
        switch (digestType) {
            case SingleImageArticle:
                SingleImageArticleDigestViewHolder singleImageArticleDigestViewHolder = (SingleImageArticleDigestViewHolder)holder;
                singleImageArticleDigestViewHolder.setDataBinding(newsDigestPresenter);
                break;
            case MultiImagesArticle:
                MultiImagesArticleDigestViewHolder multiImagesArticleDigestViewHolder = (MultiImagesArticleDigestViewHolder)holder;
                multiImagesArticleDigestViewHolder.setDataBinding(newsDigestPresenter);
                break;
            case Images:
                ImagesDigestViewHolder imagesDigestViewHolder = (ImagesDigestViewHolder)holder;
                imagesDigestViewHolder.setDataBinding(newsDigestPresenter);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemViewType(int position) {
        NewsDigestPresenter digest = (NewsDigestPresenter) mChannelPresenter.allCachedDigests().get(position);
        return digest.getDigestType().ordinal();
    }

    /**
     * Digest view holder.
     */
    static class SingleImageArticleDigestViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout digestView;
        TextView mDigestTitle;
        TextView mDigestSource;
        TextView mDigestContent;
        /**
         * Construct a digest view holder.
         * @param itemView The view to be hold.
         */
        public SingleImageArticleDigestViewHolder(View itemView) {
            super(itemView);
            digestView = (RelativeLayout) itemView;
            mDigestTitle = (TextView)digestView.findViewById(R.id.single_image_digest_title);
            mDigestSource = (TextView)digestView.findViewById(R.id.single_image_digest_source);
            mDigestContent = (TextView)digestView.findViewById(R.id.single_image_digest_digest);
        }

        public void setDataBinding(NewsDigestPresenter digestPresenter) {
            mDigestTitle.setText(digestPresenter.getTitle());
            mDigestSource.setText(digestPresenter.getSource());
            mDigestContent.setText(digestPresenter.getDigest());
        }
    }

    /**
     * Multi images article digest view.
     */
    static class MultiImagesArticleDigestViewHolder extends RecyclerView.ViewHolder {
        TextView mDigestTitle;
        TextView mDigestSource;

        /**
         * Construct a instance.
         * @param itemView Inner view.
         */
        public MultiImagesArticleDigestViewHolder(View itemView) {
            super(itemView);
            mDigestTitle = (TextView)itemView.findViewById(R.id.multi_image_digest_title);
            mDigestSource = (TextView)itemView.findViewById(R.id.multi_image_digest_source);
        }

        /**
         * Set data binding.
         * @param newsDigestPresenter News digest presenter.
         */
        public void setDataBinding(NewsDigestPresenter newsDigestPresenter) {
            mDigestTitle.setText(newsDigestPresenter.getTitle());
            mDigestSource.setText(newsDigestPresenter.getSource());
        }
    }

    /**
     * Image digest view hodler.
     */
    static class ImagesDigestViewHolder extends RecyclerView.ViewHolder {
        TextView mDigestTitle;
        TextView mDigestSource;

        /**
         * Construct a instance.
         * @param itemView Inner view.
         */
        public ImagesDigestViewHolder(View itemView) {
            super(itemView);
            mDigestTitle = (TextView)itemView.findViewById(R.id.photo_set_digest_title);
            mDigestSource = (TextView)itemView.findViewById(R.id.photo_set_digest_source);
        }

        /**
         * Set data binding.
         * @param newsDigestPresenter News digest presenter.
         */
        public void setDataBinding(NewsDigestPresenter newsDigestPresenter) {
            mDigestTitle.setText(newsDigestPresenter.getTitle());
            mDigestSource.setText(newsDigestPresenter.getSource());
        }
    }
}
