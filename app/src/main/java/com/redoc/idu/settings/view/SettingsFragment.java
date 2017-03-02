package com.redoc.idu.settings.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.framework.view.widget.LabeledIconView;
import com.redoc.widget.ShapedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Setting fragment.
 */
public class SettingsFragment extends Fragment {

    @BindView(R.id.saved)
    LabeledIconView mSavedButton;

    @BindView(R.id.history)
    LabeledIconView mHistoryButton;

    @BindView(R.id.night_mode)
    LabeledIconView mNightModeButton;

    @BindView(R.id.icon)
    ShapedImageView mIcon;

    /**
     * Construct a setting fragment.
     */
    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        mSavedButton.setIconResourceId(R.drawable.category_main_unselected, R.drawable.category_main);
        mSavedButton.setName(IDuApplication.Context.getString(R.string.saved));
        mHistoryButton.setIconResourceId(R.drawable.category_main_unselected, R.drawable.category_main);
        mHistoryButton.setName(IDuApplication.Context.getString(R.string.history));
        mNightModeButton.setIconResourceId(R.drawable.category_main_unselected, R.drawable.category_main);
        mNightModeButton.setName(IDuApplication.Context.getString(R.string.night_mode));

        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.icon)
    void showSelectIconView() {
        Intent intent = new Intent(IDuApplication.Context, SelectImageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        IDuApplication.Context.startActivity(intent);
    }
}
