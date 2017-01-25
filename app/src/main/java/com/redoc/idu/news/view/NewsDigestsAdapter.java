package com.redoc.idu.news.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redoc.idu.R;
import com.redoc.idu.framework.contract.IChannel;
import com.redoc.idu.framework.view.digest.ImagesDigestView;
import com.redoc.idu.framework.view.digest.MultiImagesArticleDigestView;
import com.redoc.idu.framework.view.digest.SingleImageArticleDigestView;
import com.redoc.idu.news.model.NewsDigestType;
import com.redoc.idu.news.presenter.NewsDigestPresenter;
import com.redoc.idu.framework.view.channel.DigestsAdapter;

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
        NewsDigestType digestType = NewsDigestType.values()[viewType];
        switch (digestType) {
            case SingleImageArticle:
                SingleImageArticleDigestView singleImageArticleDigestView = new SingleImageArticleDigestView();
                return new SingleImageArticleDigestViewHolder(singleImageArticleDigestView);
            case MultiImagesArticle:
                MultiImagesArticleDigestView multiImagesArticleDigestView = new MultiImagesArticleDigestView();
                return new MultiImagesArticleDigestViewHolder(multiImagesArticleDigestView);
            case Images:
                ImagesDigestView imagesDigests = new ImagesDigestView();
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
                singleImageArticleDigestViewHolder.mSingleImageArticleDigestView.setClickListener(new TextArticleDigestClickListener(newsDigestPresenter));
                if(mIsFirstScreen) {
                    singleImageArticleDigestViewHolder.loadImages();
                }
                break;
            case MultiImagesArticle:
                MultiImagesArticleDigestViewHolder multiImagesArticleDigestViewHolder = (MultiImagesArticleDigestViewHolder)holder;
                multiImagesArticleDigestViewHolder.setDataBinding(newsDigestPresenter);
                multiImagesArticleDigestViewHolder.mMultiImagesArticleDigestView.setClickListener(new TextArticleDigestClickListener(newsDigestPresenter));
                if(mIsFirstScreen) {
                    multiImagesArticleDigestViewHolder.loadImages();
                }
                break;
            case Images:
                ImagesDigestViewHolder imagesDigestViewHolder = (ImagesDigestViewHolder)holder;
                imagesDigestViewHolder.setDataBinding(newsDigestPresenter);
                imagesDigestViewHolder.mImagesDigestView.setClickListener(new ImagesDigestClickListener(newsDigestPresenter));
                if (mIsFirstScreen) {
                    imagesDigestViewHolder.loadImages();
                }
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

    private boolean mIsFirstScreen = true;
    /**
     * {@inheritDoc /}
     */
    @Override
    public void setIsFirstScreen(boolean isFirstScreen) {
        mIsFirstScreen = isFirstScreen;
    }

    /**
     * Digest view holder.
     */
    static class SingleImageArticleDigestViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout digestView;
        SingleImageArticleDigestView mSingleImageArticleDigestView;
        TextView mDigestTitle;
        TextView mDigestSource;
        TextView mDigestContent;
        /**
         * Construct a digest view holder.
         * @param singleImageArticleDigestView The view to be hold.
         */
        public SingleImageArticleDigestViewHolder(SingleImageArticleDigestView singleImageArticleDigestView) {
            super(singleImageArticleDigestView.getView());
            mSingleImageArticleDigestView = singleImageArticleDigestView;
            digestView = (RelativeLayout) itemView;
            mDigestTitle = (TextView)digestView.findViewById(R.id.single_image_digest_title);
            mDigestSource = (TextView)digestView.findViewById(R.id.single_image_digest_source);
            mDigestContent = (TextView)digestView.findViewById(R.id.single_image_digest_digest);
        }

        public void setDataBinding(NewsDigestPresenter digestPresenter) {
            mSingleImageArticleDigestView.setPresenter(digestPresenter);
            mDigestTitle.setText(digestPresenter.getTitle());
            mDigestSource.setText(digestPresenter.getSource());
            mDigestContent.setText(digestPresenter.getDigest());
        }

        public void loadImages() {
            mSingleImageArticleDigestView.loadDigestImages();
        }
    }

    /**
     * Multi images article digest view.
     */
    static class MultiImagesArticleDigestViewHolder extends RecyclerView.ViewHolder {
        TextView mDigestTitle;
        TextView mDigestSource;
        MultiImagesArticleDigestView mMultiImagesArticleDigestView;

        /**
         * Construct a instance.
         * @param multiImagesArticleDigestView Inner view.
         */
        public MultiImagesArticleDigestViewHolder(MultiImagesArticleDigestView multiImagesArticleDigestView) {
            super(multiImagesArticleDigestView.getView());
            mMultiImagesArticleDigestView = multiImagesArticleDigestView;
            mDigestTitle = (TextView)itemView.findViewById(R.id.multi_image_digest_title);
            mDigestSource = (TextView)itemView.findViewById(R.id.multi_image_digest_source);
        }

        /**
         * Set data binding.
         * @param newsDigestPresenter News digest presenter.
         */
        public void setDataBinding(NewsDigestPresenter newsDigestPresenter) {
            mMultiImagesArticleDigestView.setPresenter(newsDigestPresenter);
            mDigestTitle.setText(newsDigestPresenter.getTitle());
            mDigestSource.setText(newsDigestPresenter.getSource());
        }

        public void loadImages() {
            mMultiImagesArticleDigestView.loadDigestImages();
        }
    }

    /**
     * Image digest view hodler.
     */
    static class ImagesDigestViewHolder extends RecyclerView.ViewHolder {
        TextView mDigestTitle;
        TextView mDigestSource;
        ImagesDigestView mImagesDigestView;

        /**
         * Construct a instance.
         * @param imagesDigestView Inner view.
         */
        public ImagesDigestViewHolder(ImagesDigestView imagesDigestView) {
            super(imagesDigestView.getView());
            mImagesDigestView = imagesDigestView;
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
            mImagesDigestView.setPresenter(newsDigestPresenter);
        }

        /**
         * Load images.
         */
        public void loadImages() {
            mImagesDigestView.loadDigestImages();
        }
    }
}
