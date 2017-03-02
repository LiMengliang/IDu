package com.redoc.idu.settings.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.settings.contract.IAlbumImageContract;
import com.redoc.idu.settings.contract.ISelectImageContract;
import com.redoc.idu.settings.presenter.SelectImagePresenter;
import com.redoc.idu.utils.layout.LayoutUtils;
import com.redoc.widget.PullToLoadMoreRecyclerView;

import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

/**
 * Activity of selecting icon
 */
public class SelectImageActivity extends AppCompatActivity implements ISelectImageContract.ISelectImageView {



    @BindView(R.id.images_view)
    PullToLoadMoreRecyclerView mImagesView;

    private ISelectImageContract.ISelectImagePresenter mSelectImagePresenter;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_select_image_view);
        setPresenter(new SelectImagePresenter());
        ButterKnife.bind(this);
        initializeView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPresenter(ISelectImageContract.ISelectImagePresenter presenter) {
        mSelectImagePresenter = presenter;
        presenter.onAttached(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelectImageContract.ISelectImagePresenter getPresenter() {
        return mSelectImagePresenter;
    }

    private void initializeView() {
        GridLayoutManager layoutManager = new GridLayoutManager(IDuApplication.Context, 4);
        mImagesView.setLayoutManager(layoutManager);
        mImagesView.setAdapter(new ImagesViewAdapter(mSelectImagePresenter, mImagesView, LayoutUtils.getScreenSizeInPixel(IDuApplication.Context).x/4));
        mImagesView.setOnScrollListener(new ImagesViewScrollListener());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadImage() {

    }

    /**
     * Recycler view scroll listener.
     */
    class ImagesViewScrollListener extends RecyclerView.OnScrollListener {
        /**
         * On scroll status changed.
         * @param recyclerView Recycle view.
         * @param newState New state.
         */
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState == SCROLL_STATE_IDLE) {
                ((ImagesViewAdapter)recyclerView.getAdapter()).setIsFirstScreen(false);
                loadVisibleImages((PullToLoadMoreRecyclerView) recyclerView);
            }
        }

        private void loadVisibleImages(PullToLoadMoreRecyclerView recyclerView) {
            ((ImagesViewAdapter)recyclerView.getAdapter()).setIsFirstScreen(false);
            int firstVisiblePosition = recyclerView.getFirstVisiblePosition();
            int lastVisiblePosition = recyclerView.getLastVisiblePosition();
            HashSet<Integer> visiblePositions = new HashSet<>();
            if(firstVisiblePosition >= 0 && lastVisiblePosition >= 0 && firstVisiblePosition <= lastVisiblePosition) {
                for(int i = firstVisiblePosition; i < lastVisiblePosition; i++) {
                    visiblePositions.add(i);
                    if(!mSelectImagePresenter.getLastVisiblePositions().contains(i)) {
                        IAlbumImageContract.IAlbumImagePresenter presenter = mSelectImagePresenter.getAlbumImagePresenter(i);
                        presenter.loadImage();
                    }
                }
            }
            mSelectImagePresenter.getLastVisiblePositions().clear();
        }

    }
}
