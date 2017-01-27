package com.redoc.idu.news.view;

import android.content.Intent;
import android.view.View;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.news.presenter.NewsDigestPresenter;
import com.redoc.idu.framework.view.photo.PhotoActivity;

/**
 * Created by Mengliang Li on 1/25/2017.
 */

public class ImagesDigestClickListener implements View.OnClickListener {

    private NewsDigestPresenter mNewsDigestPresenter;


    public ImagesDigestClickListener(NewsDigestPresenter newsDigestPresenter) {
        mNewsDigestPresenter = newsDigestPresenter;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(IDuApplication.Context, PhotoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Url", mNewsDigestPresenter.getArticleUrl());
        IDuApplication.Context.startActivity(intent);
    }
}
