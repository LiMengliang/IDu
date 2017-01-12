package com.redoc.idu.news.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.contract.IDigest;
import com.redoc.idu.news.presenter.NewsDigestPresenter;
import com.redoc.idu.utils.DrawableUtils;
import com.redoc.idu.view.article.ArticleActivity;
import com.redoc.idu.view.widget.IDelayLoadImageView;

/**
 * Multi images article digest view.
 * Created by meli on 2016/11/14.
 */

public class MultiImagesArticleDigestView implements IDigest.IDigestView {

    private RelativeLayout mRootView;
    private ImageView mImageViewA;
    private ImageView mImageViewB;
    private ImageView mImageViewC;
    private NewsDigestPresenter mNewsDigestPresenter;

    /**
     * Construct a multi images article digest view.
     */
    public MultiImagesArticleDigestView() {
        mRootView = (RelativeLayout) LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_multi_images_article_digest, null);
        mImageViewA = (ImageView)mRootView.findViewById(R.id.multi_image_digest_image_a);
        mImageViewB = (ImageView)mRootView.findViewById(R.id.multi_image_digest_image_b);
        mImageViewC = (ImageView)mRootView.findViewById(R.id.multi_image_digest_image_c);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IDuApplication.Context, ArticleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Url", mNewsDigestPresenter.getArticleUrl());
                IDuApplication.Context.startActivity(intent);
            }
        });
    }

    /**
     * {@inheritDoc /}
     */
    @Override
    public void setPresenter(IDigest.IDigestPresenter presenter) {
        mNewsDigestPresenter = (NewsDigestPresenter)presenter;
        mNewsDigestPresenter.onAttached(this);

        mImageViewA.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
        mImageViewB.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
        mImageViewC.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
    }

    /**
     * {@inheritDoc /}
     */
    @Override
    public void loadDigestImages() {
        IDuApplication.HttpClient.displayImage(mNewsDigestPresenter.getDigestImageSources().get(0), mImageViewA);
        IDuApplication.HttpClient.displayImage(mNewsDigestPresenter.getDigestImageSources().get(1), mImageViewB);
        IDuApplication.HttpClient.displayImage(mNewsDigestPresenter.getDigestImageSources().get(2), mImageViewC);
    }

    /**
     * Get root view.
     * @return Root view.
     */
    public RelativeLayout getView() {
        return mRootView;
    }
}
