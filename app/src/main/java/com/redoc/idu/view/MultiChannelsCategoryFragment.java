package com.redoc.idu.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.redoc.idu.R;
import com.redoc.idu.contract.IChannelContract;
import com.redoc.idu.presenter.adapters.ChannelsGridAdapter;
import com.redoc.idu.view.multiChannel.MultiChannelsManagerView;
import com.redoc.idu.view.multiChannel.TabHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Multi channels category fragment.
 */
public class MultiChannelsCategoryFragment extends Fragment {

    private List<IChannelContract.IChannelPresenter> mChannels;

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
        View rootView = inflater.inflate(R.layout.fragment_multi_channels_category, container, false);
        ButterKnife.bind(this, rootView);
        setupChannelSelectors();
        setupChannelsManager();
        return rootView;
    }

    /**
     * Set channels. This method should be called before onCreteView method.
     * @param channels The channels.
     */
    public void setChannels(List<IChannelContract.IChannelPresenter> channels) {
        mChannels = channels;
    }

    /**
     * {@inheritDoc}
     */
    @OnClick(R.id.show_channels_manager)
    public void showChannelManager(View view) {
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
        if(mChannels != null) {
            List<String> names = new ArrayList<>();
            for(IChannelContract.IChannelPresenter channel : mChannels) {
                if(channel.isFollowed()) {
                   names.add(channel.getChannelName());
                }
            }
            mChannelSelectors.setTabItems(names);
            mChannelSelectors.onTabItemSelected(0);
        }
    }

    /**
     * Setup channels manager.
     */
    private void setupChannelsManager() {
        if(mChannels != null) {
            mMultiChannelsManagerView.setChannelsAdapter(new ChannelsGridAdapter(mChannels));
        }
    }
}
