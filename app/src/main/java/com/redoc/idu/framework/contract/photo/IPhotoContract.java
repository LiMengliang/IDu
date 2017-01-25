package com.redoc.idu.framework.contract.photo;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

/**
 * Created by Mengliang Li on 1/25/2017.
 */

public interface IPhotoContract {
    interface IPhotoView extends IView<IPhotoPresenter> {

    }

    interface IPhotoPresenter extends IPresenter {
        void updatePhotoViewUrls();
    }
}
