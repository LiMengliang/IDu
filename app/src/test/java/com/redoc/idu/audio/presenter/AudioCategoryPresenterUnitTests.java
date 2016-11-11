package com.redoc.idu.audio.presenter;

import com.redoc.idu.R;
import com.redoc.idu.radio.model.AudioCategory;
import com.redoc.idu.radio.presenter.AudioCategoryPresenter;
import com.redoc.idu.model.bean.Category;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test of {@link com.redoc.idu.radio.presenter.AudioCategoryPresenter}
 * Created by limen on 2016/8/28.
 */
public class AudioCategoryPresenterUnitTests {
    @Test
    public void AudioCategoryPresenter_getCategoryName_meetExpected() {
        AudioCategoryPresenter audioCategoryPresenter = new AudioCategoryPresenter(new AudioCategory(new Category(2L, "电台")));
        Assert.assertEquals("电台", audioCategoryPresenter.getCategoryName());
    }

    @Test
    public void AudioCategoryPresenter_getIconResourceId_meetExpected() {
        AudioCategoryPresenter audioCategoryPresenter = new AudioCategoryPresenter(new AudioCategory(new Category(2L, "电台")));
        Assert.assertEquals(R.drawable.category_audio, audioCategoryPresenter.getIconResourceId());
    }
}
