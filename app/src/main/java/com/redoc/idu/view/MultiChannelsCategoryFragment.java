package com.redoc.idu.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redoc.idu.R;

/**
 * Multi channels category fragment.
 */
public class MultiChannelsCategoryFragment extends Fragment {

    /**
     * Construct a {@link MultiChannelsCategoryFragment}
     */
    public MultiChannelsCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * On create
     * @param savedInstanceState State bundle.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * On create view.
     * @param inflater The inflater.
     * @param container The container.
     * @param savedInstanceState The saved instance state.
     * @return The created view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multi_channels_category, container, false);
    }
}
