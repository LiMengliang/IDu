package com.redoc.idu.framework.view.digest;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.framework.contract.IDigest;
import com.redoc.idu.framework.presenter.digest.DigestsWithImagePresenter;
import com.redoc.idu.news.presenter.NewsDigestPresenter;
import com.redoc.idu.utils.image.DrawableUtils;

/**
 * Single images article digest view.
 * Created by meli on 2016/11/13.
 */

public class SingleImageArticleDigestView implements IDigest.IDigestView {

    private RelativeLayout mRelativeLayout;
    private ImageView mImageView;
    private DigestsWithImagePresenter mdigestPresenter;

    /**
     * Construct a SingleImageArticleDigestView.
     */
    public SingleImageArticleDigestView() {
        mRelativeLayout = (RelativeLayout) LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_single_image_article_digest, null);
        mImageView = (ImageView)mRelativeLayout.findViewById(R.id.single_image_digest_image);
    }

    public void setClickListener(View.OnClickListener clickListener) {
        mRelativeLayout.setOnClickListener(clickListener);
    }

    /**
     * {@inheritDoc /}
     */
    @Override
    public void loadDigestImages() {
        IDuApplication.HttpClient.displayImage(mdigestPresenter.getDigestImageSources().get(0), mImageView);
    }

    /**
     * Get root view.
     * @return
     */
    public RelativeLayout getView() {
        return mRelativeLayout;
    }

    /**
     * {@inheritDoc /}
     */
    @Override
    public void setPresenter(IDigest.IDigestPresenter presenter) {
        mdigestPresenter = (NewsDigestPresenter)presenter;
        mdigestPresenter.onAttached(this);
        mImageView.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDigest.IDigestPresenter getPresenter() {
        return mdigestPresenter;
    }
}
