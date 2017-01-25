package com.redoc.idu.framework.view.video;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.redoc.idu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Video activity.
 */
public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.video_view)
    VideoView mVideoView;

    private MediaController mMediaController;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("VideoUrl");
        ButterKnife.bind(this);

        mMediaController = new VideoMediaController(this);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoURI(Uri.parse(url));
        mVideoView.setOnPreparedListener(new VideoViewPreparedListener());
        mVideoView.setOnCompletionListener(new VideoViewCompletionListener(this));
        mVideoView.start();

    }

    /**
     * Video view prepared listener.
     */
    class VideoViewPreparedListener implements MediaPlayer.OnPreparedListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
        }
    }

    /**
     * Video view completion listener.
     */
    class VideoViewCompletionListener implements MediaPlayer.OnCompletionListener {
        Activity mActivity;

        public VideoViewCompletionListener(Activity activity) {
            mActivity = activity;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mActivity.finish();
        }
    }

}
