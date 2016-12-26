package com.redoc.idu.news.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.contract.IDigest;
import com.redoc.idu.news.presenter.NewsDigestPresenter;
import com.redoc.idu.utils.DrawableUtils;
import com.redoc.idu.utils.network.HttpClient;
import com.redoc.idu.view.widget.IDelayLoadImageView;

/**
 * Created by meli on 2016/11/13.
 */

public class SingleImageArticleDigestView implements IDigest.IDigestView {

    private RelativeLayout mRelativeLayout;
    private ImageView mImageView;
    private NewsDigestPresenter mNewsDigestPresenter;

    public SingleImageArticleDigestView() {
        mRelativeLayout = (RelativeLayout) LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_single_image_article_digest, null);
        mImageView = (ImageView)mRelativeLayout.findViewById(R.id.single_image_digest_image);
    }

    @Override
    public void loadDigestImages() {
        IDuApplication.HttpClient.displayImage(mNewsDigestPresenter.getDigestImageSources().get(0), mImageView);
    }

    public RelativeLayout getView() {
        return mRelativeLayout;
    }

    @Override
    public void setPresenter(IDigest.IDigestPresenter presenter) {
        mNewsDigestPresenter = (NewsDigestPresenter)presenter;
        mNewsDigestPresenter.onAttached(this);
        mImageView.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
    }
}
