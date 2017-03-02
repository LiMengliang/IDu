package com.redoc.idu.settings.contract;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

import java.util.HashSet;

/**
 * Created by Mengliang Li on 2/26/2017.
 */

public interface ISelectImageContract {
    interface ISelectImageView extends IView<ISelectImagePresenter> {
        void loadImage();
    }

    interface ISelectImagePresenter extends IPresenter {
        int getImagesCount();
        IAlbumImageContract.IAlbumImagePresenter getAlbumImagePresenter(int position);
        HashSet<Integer> getLastVisiblePositions();
        String getImageUri(int position);
        void loadImage();
        void selectAImage();
        void confirm();
        void cancel();
    }
}
