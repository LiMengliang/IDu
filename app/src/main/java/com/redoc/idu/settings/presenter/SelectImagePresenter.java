package com.redoc.idu.settings.presenter;

import com.redoc.idu.IView;
import com.redoc.idu.settings.contract.IAlbumImageContract;
import com.redoc.idu.settings.contract.ISelectImageContract;
import com.redoc.idu.settings.model.SelectImageModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Select image presenter.
 * Created by Mengliang Li on 2/26/2017.
 */

public class SelectImagePresenter implements ISelectImageContract.ISelectImagePresenter {
    private ISelectImageContract.ISelectImageView mSelectImageView;
    private SelectImageModel mSelectImageModel;
    private List<IAlbumImageContract.IAlbumImagePresenter> mAlbumImagePresenters;
    private HashSet<Integer> mLastVisiblePositions = new HashSet<>();

    /**
     * Constructor.
     */
    public SelectImagePresenter() {
        mSelectImageModel = new SelectImageModel();
        mAlbumImagePresenters = new ArrayList<>();
        for(String url : mSelectImageModel.getAllImageUrls()) {
            mAlbumImagePresenters.add(new AlbumImagePresenter(url, this));
        }
    }

    /**
     * Visible positions while last screen idle status.
     * @return
     */
    public HashSet<Integer> getLastVisiblePositions() {
        return mLastVisiblePositions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getImagesCount() {
        return mSelectImageModel.getImagesCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAlbumImageContract.IAlbumImagePresenter getAlbumImagePresenter(int position) {
        return mAlbumImagePresenters.get(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImageUri(int position) {
        return mSelectImageModel.getImageUri(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadImage() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectAImage() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void confirm() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancel() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttached(IView view) {
        mSelectImageView = (ISelectImageContract.ISelectImageView)mSelectImageView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetached() {

    }
}
