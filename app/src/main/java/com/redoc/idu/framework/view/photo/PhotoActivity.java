package com.redoc.idu.framework.view.photo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.redoc.idu.R;
import com.redoc.idu.framework.contract.photo.IPhotoContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Photo activity.
 */
public class PhotoActivity extends AppCompatActivity implements IPhotoContract.IPhotoView {

    @BindView(R.id.photos_view_pager)
    ViewPager mPhotoViewPager;

    private IPhotoContract.IPhotoPresenter mPhotoPresenter;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        // mPhotoPresenter = new Photo
        // mPhotoViewPager.setAdapter(new PhotoViewAdapter());
    }

    @Override
    public void setPresenter(IPhotoContract.IPhotoPresenter presenter) {

    }

    @Override
    public IPhotoContract.IPhotoPresenter getPresenter() {
        return null;
    }
}
