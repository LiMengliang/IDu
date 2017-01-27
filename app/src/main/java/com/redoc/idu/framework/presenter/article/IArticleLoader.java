package com.redoc.idu.framework.presenter.article;

import android.os.Parcelable;

import com.redoc.idu.framework.contract.article.IArticleContract;

/**
 * Created by Mengliang Li on 1/26/2017.
 */

public interface IArticleLoader extends Parcelable {
    void parse(String url, IArticleContract.IArticlePresenter articlePresenter);
}
