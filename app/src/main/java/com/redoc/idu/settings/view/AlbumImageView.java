package com.redoc.idu.settings.view;

import android.content.Context;
import android.util.AttributeSet;

import com.redoc.idu.R;
import com.redoc.idu.framework.view.widget.AsyncImageView;
import com.redoc.idu.settings.contract.IAlbumImageContract;
import com.redoc.idu.utils.image.DrawableUtils;

/**
 * Album image view.
 * Created by Mengliang Li on 2/28/2017.
 */

public class AlbumImageView extends AsyncImageView implements IAlbumImageContract.IAlbumImageView{

    private IAlbumImageContract.IAlbumImagePresenter mAlbumImagePresenter;
    private int mPositionInAlbum;

    /**
     * Constructor of Album image view.
     * @param context Context.
     */
    public AlbumImageView(Context context) {
        super(context);
    }

    /**
     * Constructor of Album image view.
     * @param context Context.
     * @param attrs Attributes.
     */
    public AlbumImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor of Albumn image view.
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyleAttr Default attributes.
     */
    public AlbumImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPositionInAlbum(int position) {
        mPositionInAlbum = position;
    }

    public int getPositionInAlbum() {
        return mPositionInAlbum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadImage(String url) {
        loadImageAsync(url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearImage() {
        setImageDrawable(DrawableUtils.colorIdToDrawable(R.color.light_gray));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPresenter(IAlbumImageContract.IAlbumImagePresenter presenter) {
        mAlbumImagePresenter = presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAlbumImageContract.IAlbumImagePresenter getPresenter() {
        return mAlbumImagePresenter;
    }
}
