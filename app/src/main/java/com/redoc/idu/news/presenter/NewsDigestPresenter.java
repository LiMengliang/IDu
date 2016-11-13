package com.redoc.idu.news.presenter;

import com.redoc.idu.IView;
import com.redoc.idu.contract.IDigest;
import com.redoc.idu.news.model.NewsDigest;
import com.redoc.idu.news.model.NewsDigestType;

/**
 * News digest presenter.
 * Created by limen on 2016/11/5.
 */
public class NewsDigestPresenter implements IDigest.IDigestPresenter {
    private NewsDigest mDigestModel;

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


    /**
     * Get digest type.
     * @return Digest type.
     */
    public NewsDigestType getDigestType() {
        if(mDigestModel.getPhotoSetId() != null && !mDigestModel.getPhotoSetId().isEmpty()) {
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

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetached() {

    }
}
