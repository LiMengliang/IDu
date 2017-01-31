package com.redoc.idu.framework.view.photo;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.redoc.idu.IDuApplication;
import com.redoc.idu.framework.contract.article.IArticleContract;
import com.redoc.idu.framework.presenter.article.PhotoArticlePresenter;
import com.redoc.idu.framework.view.widget.PhotoView;

/**
 * Photo view adapter.
 * Created by Mengliang Li on 1/25/2017.
 */

public class PhotoViewAdapter extends PagerAdapter {

    private IArticleContract.IArticlePresenter mPhotoArticlePresenter;
    private PhotoArticlePresenter.PhotosArticleInfo mPhotoInfos;

    /**
     * Photo view adapter.
     * @param photoPresenter
     */
    public PhotoViewAdapter(IArticleContract.IArticlePresenter photoPresenter) {
        mPhotoArticlePresenter = photoPresenter;
        mPhotoInfos = (PhotoArticlePresenter.PhotosArticleInfo)mPhotoArticlePresenter.getData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return mPhotoInfos.getPhotoInfos().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(IDuApplication.Context);
        String url = mPhotoInfos.getPhotoInfos().get(position).first;
        String note = mPhotoInfos.getPhotoInfos().get(position).second;
        IDuApplication.HttpClient.addImageRequest(url, new BitmapResponseListener(photoView), 2000, 2000, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        photoView.setNote(note);
        photoView.setPageCount(position + 1, getCount());
        container.addView(photoView);
        return photoView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    /**
     * Bitmap response listener.
     */
    static class BitmapResponseListener implements Response.Listener<Bitmap> {

        private PhotoView mPhotoView;

        /**
         * Construct a {@link BitmapResponseListener}
         * @param photoView Photo view.
         */
        public BitmapResponseListener(PhotoView photoView) {
            mPhotoView = photoView;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onResponse(Bitmap bitmap) {
            mPhotoView.setPhoto(bitmap);
        }
    }
}
