package com.redoc.idu.framework.view.photo;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.redoc.idu.framework.contract.photo.IPhotoContract;

/**
 * Created by Mengliang Li on 1/25/2017.
 */

public class PhotoViewAdapter extends PagerAdapter {

    private IPhotoContract.IPhotoPresenter mPhotoPresenter;

    public PhotoViewAdapter(IPhotoContract.IPhotoPresenter photoPresenter) {
        mPhotoPresenter = photoPresenter;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
