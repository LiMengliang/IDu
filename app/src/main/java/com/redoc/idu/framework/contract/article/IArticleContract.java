package com.redoc.idu.framework.contract.article;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

/**
 * Contract of article.
 *
 * Created by Mengliang Li on 12/31/2016.
 */

public interface IArticleContract {

    /**
     * View of article.
     */
    interface IArticleView extends IView<IArticlePresenter> {
        /**
         * Update article.
         *
         * @param presenter The presenter.
         */
        void updateArticle(IArticleContract.IArticlePresenter presenter);
    }

    /**
     * Presenter of article.
     */
    interface IArticlePresenter extends IPresenter {
        /**
         * Load article.
         */
        void loadArticle();

        /**
         * On article is loaded.
         * @param object The object to be loaded.
         */
        void onArticleLoaded(Object object);

        /**
         * Get data of article to show.
         * @return Data of article.
         */
        Object getData();
    }

}
