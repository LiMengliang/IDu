package com.redoc.idu.view.multiChannel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.redoc.idu.R;
import com.redoc.idu.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.contract.multichannel.IMultiChannelsCategoryContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Multi channels category fragment.
 */
public class MultiChannelsCategoryFragment extends Fragment {
    private IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter mMultiChannelsCategoryPresenter;

    @BindView(R.id.channel_selectors)
    TabHead mChannelSelectors;
    @BindView(R.id.show_channels_manager)
    ImageView mShowChannelsManagerButton;
    @BindView(R.id.hide_channels_manager)
    ImageView mHideChannelsManagerButton;
    @BindView(R.id.multi_channel_manager)
    MultiChannelsManagerView mMultiChannelsManagerView;
    @BindView(R.id.digest_view_pager)
    ViewPager mChannelsViewPager;

    /**
     * Construct a MultiChannelsCategoryFragment
     */
    public MultiChannelsCategoryFragment() {
        // Required empty public constructor
    }

    public void setPresenter(IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter presenter) {
        mMultiChannelsCategoryPresenter = presenter;
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
        View rootView = inflater.inflate(R.layout.fragment_multi_channels_category, container, false);
        ButterKnife.bind(this, rootView);
        setupChannelSelectors();
        setupMultiChannelsManager();
        return rootView;
    }

    private void setupMultiChannelsManager() {
        mMultiChannelsManagerView.setPresenter(mMultiChannelsCategoryPresenter.getMultiChannelManager());
    }

    /**
     * {@inheritDoc}
     */
    @OnClick(R.id.show_channels_manager)
    public void showChannelManager(View view) {
        mMultiChannelsCategoryPresenter.startManage();
    }

    public void showChannelManager() {
        mHideChannelsManagerButton.setVisibility(View.VISIBLE);
        mShowChannelsManagerButton.setVisibility(View.INVISIBLE);
        mChannelsViewPager.setVisibility(View.INVISIBLE);
        mMultiChannelsManagerView.setVisibility(View.VISIBLE);
    }

    /**
     * {@inheritDoc}
     */
    @OnClick(R.id.hide_channels_manager)
    public void hideChannelManager(View view) {
        mMultiChannelsCategoryPresenter.confirmOrCancelManage(true);
    }

    public void hideChannelsManager(boolean saveOrCancel) {
        mShowChannelsManagerButton.setVisibility(View.VISIBLE);
        mHideChannelsManagerButton.setVisibility(View.INVISIBLE);
        mChannelsViewPager.setVisibility(View.VISIBLE);
        mMultiChannelsManagerView.setVisibility(View.INVISIBLE);
        setupChannelSelectors();
    }

    /**
     * Setup channel selectors.
     */
    private void setupChannelSelectors() {
        if(mMultiChannelsCategoryPresenter.getAllChannels() != null) {
            List<String> names = new ArrayList<>();
            for(IMultiChannelContract.IMultiChannelPresenter channel : mMultiChannelsCategoryPresenter.getAllChannels()) {
                if(channel.isFollowed()) {
                   names.add(channel.getChannelName());
                }
            }
            mChannelSelectors.setTabItems(names);
            mChannelSelectors.onTabItemSelected(0);
        }
    }
}
