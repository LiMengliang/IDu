package com.redoc.idu.settings.presenter;

import com.redoc.idu.IView;
import com.redoc.idu.settings.contract.IAlbumImageContract;
import com.redoc.idu.settings.contract.ISelectImageContract;

/**
 * Albumn image presenter.
 * Created by Mengliang Li on 2/28/2017.
 */

public class AlbumImagePresenter implements IAlbumImageContract.IAlbumImagePresenter {

    private IAlbumImageContract.IAlbumImageView mAlbumImageView;
    private String mImageUrl;
    private SelectImagePresenter mSelectImagesPresenter;

    /**
     * Constructor.
     * @param url Url of the image.
     */
    public AlbumImagePresenter(String url, SelectImagePresenter selectImagePresenter) {
        mImageUrl = url;
        mSelectImagesPresenter = selectImagePresenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlbumImageView(IAlbumImageContract.IAlbumImageView view) {
        onAttached(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadImage() {
        if(!mImageUrl.isEmpty() && !(mImageUrl == null)){
            mAlbumImageView.loadImage(mImageUrl);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttached(IView view) {
        mAlbumImageView = (IAlbumImageContract.IAlbumImageView)view;
        view.setPresenter(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetached() {

    }
}
