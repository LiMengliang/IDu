package com.redoc.idu.settings.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.framework.view.widget.AsyncImageView;
import com.redoc.idu.settings.contract.IAlbumImageContract;
import com.redoc.idu.settings.contract.ISelectImageContract;
import com.redoc.widget.PullToLoadMoreRecyclerView;

import java.util.HashSet;

/**
 * Images view adatper.
 * Created by Mengliang Li on 2/26/2017.
 */

public class ImagesViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PullToLoadMoreRecyclerView mRecyclerView;
    private ISelectImageContract.ISelectImagePresenter mSelectImagePresenter;
    private HashSet<AsyncImageView> mDelayLoadImagesView = new HashSet<>();
    private int mSize;
    private boolean mIsFirstScreen = true;

    /**
     * Constructor.
     * @param selectImagePresenter Presenter of select image view.
     * @param recyclerView The recycler view that host all the images.
     * @param size Expected size.
     */
    public ImagesViewAdapter(ISelectImageContract.ISelectImagePresenter selectImagePresenter, PullToLoadMoreRecyclerView recyclerView, int size) {
        mSelectImagePresenter = selectImagePresenter;
        mSize = size;
        mRecyclerView = recyclerView;
    }

    /**
     * If is first screen
     * @param isFirstScreen If firs screen.
     */
    public void setIsFirstScreen(boolean isFirstScreen) {
        mIsFirstScreen = isFirstScreen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final AlbumImageView imageView = new AlbumImageView(IDuApplication.Context);
        imageView.setDelayLoadImageListener(new AsyncImageView.IDelayLoadImageListener() {
            @Override
            public void onLoaded() {
                mSelectImagePresenter.getLastVisiblePositions().add(imageView.getPositionInAlbum());
            }
        });
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GridLayoutManager.LayoutParams layoutParams = new GridLayoutManager.LayoutParams(mSize, mSize);
        layoutParams.setMargins(2, 2, 2, 2);
        imageView.setLayoutParams(layoutParams);
        return new SelectableImageViewHolder(imageView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumImageView imageView = (AlbumImageView)holder.itemView;
        imageView.setPositionInAlbum(position);
        IAlbumImageContract.IAlbumImagePresenter imagePresenter = mSelectImagePresenter.getAlbumImagePresenter(position);
        imagePresenter.setAlbumImageView((AlbumImageView)holder.itemView);
        imagePresenter.clearImage();
        if(mIsFirstScreen) {
            imagePresenter.loadImage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        ((ImageView)(viewHolder.itemView)).setImageBitmap(null);
        mDelayLoadImagesView.remove(viewHolder.itemView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mSelectImagePresenter.getImagesCount();
    }

    /**
     * View holder.
     */
    class SelectableImageViewHolder extends RecyclerView.ViewHolder {

        /**
         * Constructor.
         * @param imageView Image view.
         */
        public SelectableImageViewHolder(AlbumImageView imageView) {
            super(imageView);
        }
    }
}
