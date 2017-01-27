package com.redoc.idu.framework.presenter.article;

import com.redoc.idu.IView;
import com.redoc.idu.framework.contract.article.IArticleContract;
import com.redoc.idu.framework.presenter.article.IArticleLoader;

/**
 * Created by Mengliang Li on 12/31/2016.
 */

public class TextArticlePresenter implements IArticleContract.IArticlePresenter {

    private IArticleContract.IArticleView mArticleView;
    private String mUrl;
    private String parsedHtml;
    private IArticleLoader mArticleLoader;

    public TextArticlePresenter(String url, IArticleContract.IArticleView articleView, IArticleLoader articleLoader) {
        mUrl = url;
        mArticleView = articleView;
        mArticleLoader = articleLoader;
        // TODO: Need to deal with these special cases, some times NetEase does not provide standard url.
        if(mUrl != null && !mUrl.equals("null") &&!mUrl.isEmpty()) {
            loadArticle();
        }
    }

    @Override
    public void loadArticle() {
        mArticleLoader.parse(mUrl, this);
    }

    @Override
    public void onArticleLoaded(Object object) {
        parsedHtml = (String)object;
        mArticleView.updateArticle(this);
    }

    @Override
    public Object getData() {
        return parsedHtml;
    }

    @Override
    public void onAttached(IView view) {

    }

    @Override
    public void onDetached() {
        mArticleView = null;
    }
}
