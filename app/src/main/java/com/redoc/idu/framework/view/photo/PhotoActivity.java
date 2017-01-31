package com.redoc.idu.framework.view.photo;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.redoc.idu.R;
import com.redoc.idu.framework.contract.article.IArticleContract;
import com.redoc.idu.framework.presenter.article.IArticleLoader;
import com.redoc.idu.framework.presenter.article.PhotoArticlePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Photo activity.
 */
public class PhotoActivity extends AppCompatActivity implements IArticleContract.IArticleView {

    private IArticleContract.IArticlePresenter mPhotoArticlePresenter;
    @BindView(R.id.photos_view_pager)
    ViewPager mPhotoViewPager;

    @BindView(R.id.back_button)
    TextView mBack;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        IArticleLoader articleLoader = bundle.getParcelable("ClickListener");
        setPresenter(new PhotoArticlePresenter(this, articleLoader));
        mPhotoViewPager.setAdapter(new PhotoViewAdapter(mPhotoArticlePresenter));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPresenter(IArticleContract.IArticlePresenter presenter) {
        mPhotoArticlePresenter = presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IArticleContract.IArticlePresenter getPresenter() {
        return mPhotoArticlePresenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateArticle(IArticleContract.IArticlePresenter presenter) {
        mPhotoViewPager.getAdapter().notifyDataSetChanged();
    }

    @OnClick(R.id.back_button)
    void BackClicked() {
        this.finish();
    }
}
