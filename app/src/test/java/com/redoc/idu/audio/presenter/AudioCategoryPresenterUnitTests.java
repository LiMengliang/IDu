package com.redoc.idu.audio.presenter;

import com.redoc.idu.R;
import com.redoc.idu.audios.model.AudioCategory;
import com.redoc.idu.audios.presenter.AudioCategoryPresenter;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test of {@link com.redoc.idu.audios.presenter.AudioCategoryPresenter}
 * Created by limen on 2016/8/28.
 */
public class AudioCategoryPresenterUnitTests {
    @Test
    public void AudioCategoryPresenter_getCategoryName_meetExpected() {
        AudioCategoryPresenter audioCategoryPresenter = new AudioCategoryPresenter(new AudioCategory());
        Assert.assertEquals("音频", audioCategoryPresenter.getCategoryName());
    }

    @Test
    public void AudioCategoryPresenter_getIconResourceId_meetExpected() {
        AudioCategoryPresenter audioCategoryPresenter = new AudioCategoryPresenter(new AudioCategory());
        Assert.assertEquals(R.drawable.category_audio, audioCategoryPresenter.getIconResourceId());
    }
}
