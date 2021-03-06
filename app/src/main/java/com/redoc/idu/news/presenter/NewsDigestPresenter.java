package com.redoc.idu.news.presenter;

import com.redoc.idu.IView;
import com.redoc.idu.framework.contract.IDigest;
import com.redoc.idu.framework.presenter.digest.DigestsWithImagePresenter;
import com.redoc.idu.news.model.NewsDigest;
import com.redoc.idu.news.model.NewsDigestType;

import java.util.List;

/**
 * News digest presenter.
 * Created by limen on 2016/11/5.
 */
public class NewsDigestPresenter extends DigestsWithImagePresenter {
    private NewsDigest mDigestModel;
    private IDigest.IDigestView mDigestView;

    /**
     * Construct a instance.
     * @param newsDigest News digest model.
     */
    public NewsDigestPresenter(NewsDigest newsDigest) {
        mDigestModel = newsDigest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return mDigestModel.getDigestTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDigest() {
        return mDigestModel.getDigest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSource() {
        return mDigestModel.getSource();
    }

    @Override
    public String getArticleUrl() {
        return mDigestModel.getNewsUrl();
    }

    @Override
    public void loadImages() {
        mDigestView.loadDigestImages();
    }

    /**
     * Get digest images' source.
     * @return Source of images.
     */
    public List<String> getDigestImageSources() {
        return mDigestModel.getDigestImages();
    }


    /**
     * Get digest type.
     * @return Digest type.
     */
    public NewsDigestType getDigestType() {
        if(mDigestModel.getPhotoSetId() != null && !mDigestModel.getPhotoSetId().isEmpty() && mDigestModel.getDigestImages().size() == 3) {
            return NewsDigestType.Images;
        }
        else if(mDigestModel.getDigestImages().size() == 1) {
                return NewsDigestType.SingleImageArticle;
        }
        else {
            return NewsDigestType.MultiImagesArticle;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttached(IView view) {
        mDigestView = (IDigest.IDigestView)view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetached() {

    }
}
