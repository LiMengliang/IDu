package com.redoc.idu.contract.article;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

/**
 * Created by Mengliang Li on 12/31/2016.
 */

public interface IArticleContract {
    interface IArticleView extends IView<IArticlePresenter> {
        void updateArticle(IArticleContract.IArticlePresenter presenter);
    }

    interface IArticlePresenter extends IPresenter {
        void loadArticle();
        void onArticleLoaded(Object object);
        Object getData();
    }

}
