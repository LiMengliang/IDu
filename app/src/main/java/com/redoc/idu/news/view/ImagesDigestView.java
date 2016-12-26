package com.redoc.idu.news.view;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.contract.IDigest;
import com.redoc.idu.news.presenter.NewsDigestPresenter;
import com.redoc.idu.utils.DrawableUtils;
import com.redoc.idu.view.widget.IDelayLoadImageView;

/**
 * Created by meli on 2016/11/14.
 */

public class ImagesDigestView implements IDigest.IDigestView {
    private RelativeLayout mRootView;
    private ImageView mImageViewA;
    private ImageView mImageViewB;
    private ImageView mImageViewC;
    private NewsDigestPresenter mNewsDigestPresenter;
    public ImagesDigestView() {
        mRootView = (RelativeLayout) LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_images_digest, null);
        mImageViewA = (ImageView)mRootView.findViewById(R.id.photo_set_digest_main_image);
        mImageViewB = (ImageView)mRootView.findViewById(R.id.photo_set_digest_right_image_a);
        mImageViewC = (ImageView)mRootView.findViewById(R.id.photo_set_digest_right_image_b);
    }
    @Override
    public void setPresenter(IDigest.IDigestPresenter presenter) {
        mNewsDigestPresenter = (NewsDigestPresenter)presenter;
        mNewsDigestPresenter.onAttached(this);

        mImageViewA.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
        mImageViewB.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
        mImageViewC.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
    }

    @Override
    public void loadDigestImages() {
        IDuApplication.HttpClient.displayImage(mNewsDigestPresenter.getDigestImageSources().get(0), mImageViewA);
        IDuApplication.HttpClient.displayImage(mNewsDigestPresenter.getDigestImageSources().get(1), mImageViewB);
        IDuApplication.HttpClient.displayImage(mNewsDigestPresenter.getDigestImageSources().get(2), mImageViewC);
    }

    public RelativeLayout getView() {
        return mRootView;
    }
}
