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
import com.redoc.idu.utils.network.HttpClient;

import java.util.LinkedList;

/**
 * Photo view adapter.
 * Created by Mengliang Li on 1/25/2017.
 */

public class PhotoViewAdapter extends PagerAdapter {

    private IArticleContract.IArticlePresenter mPhotoArticlePresenter;
    private PhotoArticlePresenter.PhotosArticleInfo mPhotoInfos;
    private LinkedList<View> recycledViews = new LinkedList<>();

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
        PhotoView photoView = null;
        if(recycledViews != null & recycledViews.size() > 0) {
            photoView = (PhotoView)recycledViews.getFirst();
            recycledViews.removeFirst();
        }
        else {
            photoView = new PhotoView(IDuApplication.Context);
        }
        String url = mPhotoInfos.getPhotoInfos().get(position).first;
        String note = mPhotoInfos.getPhotoInfos().get(position).second;
        IDuApplication.HttpClient.addImageRequest(url, new HttpClient.BitmapResponseListener(photoView.getImageView()), 2000,
                2000, Bitmap.Config.RGB_565, new Response.ErrorListener() {
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
        container.removeView((View)object);
        if(object != null) {
            recycledViews.addLast((View)object);
        }
    }
}
