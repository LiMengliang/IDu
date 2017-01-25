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
import com.redoc.idu.utils.DrawableUtils;

/**
 * Multi images article digest view.
 * Created by meli on 2016/11/14.
 */

public class MultiImagesArticleDigestView implements IDigest.IDigestView {

    private RelativeLayout mRootView;
    private ImageView mImageViewA;
    private ImageView mImageViewB;
    private ImageView mImageViewC;
    private DigestsWithImagePresenter mdigestPresenter;

    /**
     * Construct a multi images article digest view.
     */
    public MultiImagesArticleDigestView() {
        mRootView = (RelativeLayout) LayoutInflater.from(IDuApplication.Context).inflate(R.layout.view_multi_images_article_digest, null);
        mImageViewA = (ImageView)mRootView.findViewById(R.id.multi_image_digest_image_a);
        mImageViewB = (ImageView)mRootView.findViewById(R.id.multi_image_digest_image_b);
        mImageViewC = (ImageView)mRootView.findViewById(R.id.multi_image_digest_image_c);
        // mRootView.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         Intent intent = new Intent(IDuApplication.Context, ArticleActivity.class);
        //         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //         intent.putExtra("Url", mNewsDigestPresenter.getArticleUrl());
        //         IDuApplication.Context.startActivity(intent);
        //     }
        // });
    }

    public void setClickListener(View.OnClickListener clickListener) {
        if(mRootView != null) {
            mRootView.setOnClickListener(clickListener);
        }
    }

    /**
     * {@inheritDoc /}
     */
    @Override
    public void setPresenter(IDigest.IDigestPresenter presenter) {
        mdigestPresenter = (NewsDigestPresenter)presenter;
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
