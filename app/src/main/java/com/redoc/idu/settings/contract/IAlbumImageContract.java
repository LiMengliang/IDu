package com.redoc.idu.settings.contract;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

/**
 * Contract of album image view and presenter.
 * Created by Mengliang Li on 2/28/2017.
 */

public interface IAlbumImageContract {

    /**
     * Album image view.
     */
    interface IAlbumImageView extends IView<IAlbumImagePresenter> {
        /**
         * Load image
         * @param url Url of image.
         */
        void loadImage(String url);

        /**
         * Clear image.
         */
        void clearImage();
    }

    /**
     * Album image presenter.
     */
    interface IAlbumImagePresenter extends IPresenter {

        /**
         * Set album image view.
         * @param view Album image view.
         */
        void setAlbumImageView(IAlbumImageView view);

        /**
         * Load image.
         */
        void loadImage();

        /**
         * Clear image.
         */
        void clearImage();
    }
}
