package com.redoc.idu.framework.presenter.article;

import android.util.Pair;

import com.redoc.idu.IView;
import com.redoc.idu.framework.contract.article.IArticleContract;
import com.redoc.idu.framework.view.photo.PhotoActivity;
import com.redoc.idu.news.presenter.article.IArticleLoadListener;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Photo article presenter.
 * Created by limen on 2017/1/28.
 */

public class PhotoArticlePresenter implements IArticleContract.IArticlePresenter, IArticleLoadListener {

    private IArticleContract.IArticleView mArticleView;
    private IArticleLoader mArticleLoader;
    private PhotosArticleInfo mPhotoArticleInfo;

    /**
     * Construct a {@link PhotoArticlePresenter}
     * @param articleView Article viewer.
     * @param articleLoader Article load complete listener.
     */
    public PhotoArticlePresenter(IArticleContract.IArticleView articleView, IArticleLoader articleLoader) {
        mArticleView = articleView;
        mArticleLoader = articleLoader;
        mPhotoArticleInfo = new PhotosArticleInfo();
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
        HashMap<String, String> photos = (HashMap<String, String>)object;
        for(HashMap.Entry<String, String> photo : photos.entrySet()) {
            mPhotoArticleInfo.addPhotoInfo(photo.getKey(), photo.getValue());
        }
        mArticleView.updateArticle(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getData() {
        return mPhotoArticleInfo;
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

    }

    /**
     * Photo article information.
     */
    public static class PhotosArticleInfo {
        private List<Pair<String, String>> photoInfos = new ArrayList<>();
        public  List<Pair<String, String>> getPhotoInfos() {
            return photoInfos;
        }

        /**
         * Add a photo information.
         * @param url Photo url.
         * @param note Photo note.
         */
        public void addPhotoInfo(String url, String note) {
            photoInfos.add(new Pair<>(url, note));
        }
    }
}
