package com.redoc.idu.framework.presenter.article;

import android.os.Parcelable;

import com.redoc.idu.news.presenter.article.IArticleLoadListener;

/**
 * Article loader interface.
 * Created by Mengliang Li on 1/26/2017.
 */

public interface IArticleLoader extends Parcelable {
    /**
     * Load article.
     * @param loadListener Load complete listener.
     */
    void load(IArticleLoadListener loadListener);
}
