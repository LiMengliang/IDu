package com.redoc.idu.framework.presenter.article;

import com.redoc.idu.IView;
import com.redoc.idu.framework.contract.article.IArticleContract;
import com.redoc.idu.news.presenter.article.IArticleLoadListener;

/**
 * Text article prsetener.
 * Created by Mengliang Li on 12/31/2016.
 */

public class TextArticlePresenter implements IArticleContract.IArticlePresenter, IArticleLoadListener {

    private IArticleContract.IArticleView mArticleView;
    private String parsedHtml;
    private IArticleLoader mArticleLoader;

    /**
     * Construct a {@link TextArticlePresenter}
     * @param articleView
     * @param articleLoader
     */
    public TextArticlePresenter(IArticleContract.IArticleView articleView, IArticleLoader articleLoader) {
        mArticleView = articleView;
        mArticleLoader = articleLoader;
        // TODO: Need to deal with these special cases, some times NetEase does not provide standard url.
        loadArticle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadArticle() {
        mArticleLoader.load(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoaded(Object object) {
        parsedHtml = (String)object;
        mArticleView.updateArticle(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getData() {
        return parsedHtml;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttached(IView view) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetached() {
        mArticleView = null;
    }
}
