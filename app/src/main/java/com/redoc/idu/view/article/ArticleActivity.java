package com.redoc.idu.view.article;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.contract.article.IArticleContract;
import com.redoc.idu.presenter.article.TextArticlePresenter;
import com.redoc.idu.view.video.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Article activity.
 */
public class ArticleActivity extends AppCompatActivity implements IArticleContract.IArticleView {

    @BindView(R.id.web_view)
    WebView mWebView;
    private IArticleContract.IArticlePresenter mArticlePresenter;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("Url");
        setPresenter(new TextArticlePresenter(url, this));
        updateArticle(mArticlePresenter);
    }

    /**
     * {@inheritDoc}
     */
    @OnClick(R.id.back_button)
    public void onClick()
    {
        this.finish();
        mArticlePresenter.onDetached();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateArticle(IArticleContract.IArticlePresenter presenter) {
        this.mWebView.getSettings().setSupportZoom(false);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                handler.proceed();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(IDuApplication.Context, VideoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("VideoUrl", url);
                IDuApplication.Context.startActivity(intent);
                return true;
            }
        });
        mWebView.loadDataWithBaseURL("about:blank", (String)(presenter.getData()), "text/html", "utf-8", null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPresenter(IArticleContract.IArticlePresenter presenter) {
        mArticlePresenter = presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IArticleContract.IArticlePresenter getPresenter() {
        return mArticlePresenter;
    }
}
