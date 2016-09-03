package com.redoc.idu.audios.presenter;

import com.redoc.idu.R;
import com.redoc.idu.audios.model.AudioCategory;
import com.redoc.idu.presenter.SingleChannelCategoryPresenter;

/**
 *  Audio Category Presenter
 * Created by limen on 2016/8/27.
 */
public class AudioCategoryPresenter extends SingleChannelCategoryPresenter {

    /**
     * Audio category presenter.
     */
    private AudioCategory mAudioCategory;

    /**
     * Construct a {@link AudioCategoryPresenter} instance.
     * @param audioCategory Audio category model.
     */
    public AudioCategoryPresenter(AudioCategory audioCategory) {
        mAudioCategory = audioCategory;
    }

    /**
     * Get category name.
     *
     * @return Name of the category.
     */
    @Override
    public String getCategoryName() {
        return mAudioCategory.getmCategoryName();
    }

    /**
     * Get category icon resource id.
     *
     * @return Id of icon.
     */
    @Override
    public int getIconResourceId() {
        return R.drawable.category_audio;
    }
}
