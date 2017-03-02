package com.redoc.idu.settings.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.settings.presenter.SelectImagePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of select image.
 * Created by Mengliang Li on 2/26/2017.
 */

public class SelectImageModel {
    private String selectedImage;

    /**
     * Get selected image.
     * @return
     */
    public String getSelectedImage() {
        return selectedImage;
    }

    /**
     * Set selected image.
     * @param value Selected image url.
     */
    public void setSelectedImage(String value) {
        selectedImage = value;
    }
    private List<String> mAllImageUrls;

    /**
     * Constructor.
     */
    public SelectImageModel() {
        mAllImageUrls = getImages();
    }

    private List<String> getImages() {
        Uri externalImages = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = IDuApplication.Context.getContentResolver();
        List<String> imagePaths = new ArrayList<>();
        Cursor mCursor = contentResolver.query(externalImages, null, MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?", new String[] { "image/jpeg", "image/png" },
                MediaStore.Images.Media.DATE_MODIFIED);
        if (mCursor == null) {
            return imagePaths;
        }
        while(mCursor.moveToNext()) {
            String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
            imagePaths.add(path);
        }
        return imagePaths;
    }

    /**
     * Get all url of images.
     * @return All url of images.
     */
    public List<String> getAllImageUrls() {
        return mAllImageUrls;
    }

    /**
     * Get image url of certain position.
     * @param index Index.
     * @return
     */
    public String getImageUri(int index) {
        return mAllImageUrls.get(index);
    }

    /**
     * Get count of images.
     * @return Count of images.
     */
    public int getImagesCount() {
        return mAllImageUrls.size();
    }
}
