package com.redoc.idu.view.category.multiChannel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
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

    /**
     * Multi channel category presenter.
     */
    private IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter mMultiChannelsCategoryPresenter;

    /**
     * Multi channel adapter.
     */
    private MultiChannelsAdapter mMultiChannelsAdapter;

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
    @BindView(R.id.channel_selector_bar)
    HorizontalScrollView mSelectorBarScrollView;

    /**
     * Display matrics.
     */
    private DisplayMetrics mDisplayMetrics;

    /**
     * Construct a MultiChannelsCategoryFragment
     */
    public MultiChannelsCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Set presenter.
     * @param presenter The Multi channel category presenter.
     */
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
        mDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);

        initializeChannelsSelector();
        initializeChannelsViewPager();
        initializeMultiChannelsManager();
        updateChannels();
        return rootView;
    }

    /**
     * Initialize channels selector.
     */
    private void initializeChannelsSelector() {
        mChannelSelectors.setOnTabItemSelectListener(new OnSelectTabListener() {
            @Override
            public void onSelect(int tabIndex) {
                mMultiChannelsCategoryPresenter.selectChannel(tabIndex);
            }
        });
    }

    /**
     * Initialize multi channels manager.
     */
    private void initializeMultiChannelsManager() {
        mMultiChannelsManagerView.setPresenter(mMultiChannelsCategoryPresenter.getMultiChannelManager());
    }

    /**
     * Switch to nth channel.
     * @param position The position of the channel.
     */
    public void switchToChannel(int position) {
        if(mChannelSelectors != null) {
            smoothScrollToChannelSelector(position);
            mChannelSelectors.onTabItemSelected(position);
            mChannelsViewPager.setCurrentItem(position, false);
        }
    }

    /**
     * Smooth scroll channels selector to expected position.
     * @param index Index of the channel.
     */
    private void smoothScrollToChannelSelector(int index) {
        View channelItem = mChannelSelectors.getChildAt(index);
        int width = channelItem.getMeasuredWidth();
        int left = channelItem.getLeft();
        if(width > 0) {
            int xDestination = left + width / 2 - mDisplayMetrics.widthPixels / 2;
            mSelectorBarScrollView.smoothScrollTo(xDestination, 0);
        }
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

    /**
     * Hide channel manager.
     * @param saveOrCancel True means the management will be confirmed, otherwise it's cancelled.
     */
    public void hideChannelsManager(boolean saveOrCancel) {
        mShowChannelsManagerButton.setVisibility(View.VISIBLE);
        mHideChannelsManagerButton.setVisibility(View.INVISIBLE);
        mChannelsViewPager.setVisibility(View.VISIBLE);
        mMultiChannelsManagerView.setVisibility(View.INVISIBLE);
        updateChannels();
    }

    /**
     * Setup channel selectors.
     */
    private void updateChannels() {
        if(mMultiChannelsCategoryPresenter.getAllChannels() != null) {
            List<String> names = new ArrayList<>();
            for(IMultiChannelContract.IMultiChannelPresenter channel : mMultiChannelsCategoryPresenter.getAllChannels()) {
                if(channel.isFollowed()) {
                   names.add(channel.getChannelName());
                }
            }
            mChannelSelectors.setTabItems(names);
        }
        mMultiChannelsAdapter.notifyDataSetChanged();
        mMultiChannelsCategoryPresenter.updateChannelSelection();
    }

    /**
     * Initialize channels view pager.
     */
    private void initializeChannelsViewPager() {
        if(mMultiChannelsAdapter == null) {
            mMultiChannelsAdapter = new MultiChannelsAdapter(getFragmentManager(), mMultiChannelsCategoryPresenter);
        }
        mChannelsViewPager.setAdapter(mMultiChannelsAdapter);
        mChannelsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private double lastPositionOffset;

            /**
             * {@inheritDoc}
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(lastPositionOffset == -1) {
                    lastPositionOffset = positionOffset;
                    return;
                }
                if(lastPositionOffset < positionOffset && positionOffset > 0.999) {
                    mMultiChannelsCategoryPresenter.selectChannel(position + 1);
                    lastPositionOffset = -1;
                    return;
                }
                else if(lastPositionOffset > positionOffset && positionOffset < 0.001) {
                    mMultiChannelsCategoryPresenter.selectChannel(position);
                    lastPositionOffset = -1;
                    return;
                }
                lastPositionOffset = positionOffset;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onPageSelected(int position) {
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mChannelsViewPager.setOffscreenPageLimit(1);
        mChannelsViewPager.setCurrentItem(0);
    }
}
