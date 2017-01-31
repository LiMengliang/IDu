package com.redoc.idu.news.view;

import android.content.Intent;
import android.view.View;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.news.presenter.NewsDigestPresenter;
import com.redoc.idu.framework.view.article.ArticleActivity;
import com.redoc.idu.news.presenter.article.TextArticleHtmlLoader;

/**
 * Text article digest click listener.
 * Created by Mengliang Li on 1/25/2017.
 */

public class TextArticleDigestClickListener implements View.OnClickListener {

    private NewsDigestPresenter mNewsDigestPresenter;

    /**
     * Construct a {@link TextArticleDigestClickListener}
     * @param newsDigestPresenter News digest presenter.
     */
    public TextArticleDigestClickListener(NewsDigestPresenter newsDigestPresenter) {
        mNewsDigestPresenter = newsDigestPresenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(IDuApplication.Context, ArticleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ArticleLoader", new TextArticleHtmlLoader(mNewsDigestPresenter.getArticleUrl()));
        IDuApplication.Context.startActivity(intent);
    }
}
