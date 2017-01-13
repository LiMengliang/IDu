package com.redoc.idu.news.view;

import android.content.Intent;
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

/**
 * Single images article digest view.
 * Created by meli on 2016/11/13.
 */

public class SingleImageArticleDigestView implements IDigest.IDigestView {

    private RelativeLayout mRelativeLayout;
    private ImageView mImageView;
    private NewsDigestPresenter mNewsDigestPresenter;

    /**
     * Construct a SingleImageArticleDigestView.
     */
    public SingleImageArticleDigestView() {
        mRelativeLayout = (RelativeLayout) LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_single_image_article_digest, null);
        mImageView = (ImageView)mRelativeLayout.findViewById(R.id.single_image_digest_image);
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
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
    public void loadDigestImages() {
        IDuApplication.HttpClient.displayImage(mNewsDigestPresenter.getDigestImageSources().get(0), mImageView);
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
        mNewsDigestPresenter = (NewsDigestPresenter)presenter;
        mNewsDigestPresenter.onAttached(this);
        mImageView.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDigest.IDigestPresenter getPresenter() {
        return mNewsDigestPresenter;
    }
}
