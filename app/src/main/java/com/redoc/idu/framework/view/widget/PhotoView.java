package com.redoc.idu.framework.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.utils.image.BitmapUtils;
import com.redoc.idu.utils.layout.LayoutUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Photo view.
 * Created by limen on 2017/1/30.
 */

public class PhotoView extends RelativeLayout {
    @BindView(R.id.photo_note)
    TextView mPhotoNote;

    @BindView(R.id.photo_view)
    ImageView mPhotoView;

    @BindView(R.id.page_count)
    TextView mPageCount;

    @BindView(R.id.message)
    ScrollView mMessageView;

    @BindView(R.id.photo_area)
    RelativeLayout mPhotoContainer;

    /**
     * Constructor.
     * @param context Context.
     */
    public PhotoView(Context context) {
        super(context);
        LayoutUtils.inflateLayout(IDuApplication.Context, R.layout.view_photo, this, true);
        ButterKnife.bind(this);
    }

    /**
     * Set bitmap to viewer.
     * @param bitmap The bitmap.
     */
    public void setPhoto(Bitmap bitmap) {
        mPhotoView.setImageBitmap(bitmap);
    }

    /**
     * Set note to display.
     * @param note Photo note.
     */
    public void setNote(String note) {
        mPhotoNote.setText(note);
    }

    /**
     * Set page count.
     * @param currentPage
     * @param totalPage
     */
    public void setPageCount(int currentPage, int totalPage) {
        mPageCount.setText(String.format("%d/%d", currentPage, totalPage));
    }

    @OnClick(R.id.photo_view)
    public void onPhotoViewClick() {
        if(mMessageView.getVisibility() == VISIBLE) {
            mMessageView.setVisibility(View.INVISIBLE);
        }
        else {
            mMessageView.setVisibility(View.VISIBLE);
        }
    }
}
