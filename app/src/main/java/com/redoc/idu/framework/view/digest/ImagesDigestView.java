package com.redoc.idu.framework.view.digest;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.framework.contract.IDigest;
import com.redoc.idu.framework.presenter.digest.DigestsWithImagePresenter;
import com.redoc.idu.utils.image.DrawableUtils;

/**
 * View of images digest.
 *
 * Created by meli on 2016/11/14.
 */

public class ImagesDigestView implements IDigest.IDigestView {
    private RelativeLayout mRootView;
    private ImageView mImageViewA;
    private ImageView mImageViewB;
    private ImageView mImageViewC;
    private DigestsWithImagePresenter mdigestPresenter;

    /**
     * Construct a view of images digest.
     */
    public ImagesDigestView() {
        mRootView = (RelativeLayout) LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_images_digest, null);
        mImageViewA = (ImageView)mRootView.findViewById(R.id.photo_set_digest_main_image);
        mImageViewB = (ImageView)mRootView.findViewById(R.id.photo_set_digest_right_image_a);
        mImageViewC = (ImageView)mRootView.findViewById(R.id.photo_set_digest_right_image_b);
    }

    /**
     * Set click listener.
     * @param clickListener Click listener.
     */
    public void setClickListener(View.OnClickListener clickListener) {
        mRootView.setOnClickListener(clickListener);
    }

    /**
     * {@inheritDoc /}
     */
    @Override
    public void setPresenter(IDigest.IDigestPresenter presenter) {
        mdigestPresenter = (DigestsWithImagePresenter) presenter;
        mdigestPresenter.onAttached(this);

        mImageViewA.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
        mImageViewB.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
        mImageViewC.setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDigest.IDigestPresenter getPresenter() {
        return mdigestPresenter;
    }

    /**
     * {@inheritDoc /}
     */
    @Override
    public void loadDigestImages() {
        IDuApplication.HttpClient.displayImage(mdigestPresenter.getDigestImageSources().get(0), mImageViewA);
        IDuApplication.HttpClient.displayImage(mdigestPresenter.getDigestImageSources().get(1), mImageViewB);
        IDuApplication.HttpClient.displayImage(mdigestPresenter.getDigestImageSources().get(2), mImageViewC);
    }

    /**
     * Get root view.
     * @return Root view.
     */
    public RelativeLayout getView() {
        return mRootView;
    }
}
