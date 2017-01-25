package com.redoc.idu.news.presenter.article;

import com.redoc.idu.IView;
import com.redoc.idu.framework.contract.article.IArticleContract;

/**
 * Created by Mengliang Li on 12/31/2016.
 */

public class TextArticlePresenter implements IArticleContract.IArticlePresenter {

    private IArticleContract.IArticleView mArticleView;
    private String mUrl;
    private String parsedHtml;

    public TextArticlePresenter(String url, IArticleContract.IArticleView articleView) {
        mUrl = url;
        mArticleView = articleView;
        // TODO: Need to deal with these special cases, some times NetEase does not provide standard url.
        if(mUrl != null && !mUrl.equals("null") &&!mUrl.isEmpty()) {
            loadArticle();
        }
    }

    @Override
    public void loadArticle() {
        TextArticleHtmlParser parser = new TextArticleHtmlParser();
        String[] splits = mUrl.split(".html");
        // _0 can get full article regardless whether the article is divided into multi-pages.
        parser.parse(splits[0] + "_0.html", this);
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
