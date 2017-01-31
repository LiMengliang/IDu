package com.redoc.idu.news.presenter.article;

/**
 * Article load complete listener.
 * Created by limen on 2017/1/28.
 */

public interface IArticleLoadListener {
    /**
     * On load complete.
     * @param loadedObject The object that is loaded.
     */
    void onLoaded(Object loadedObject);
}
