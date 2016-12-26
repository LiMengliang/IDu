package com.redoc.idu.view.channel;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.contract.IChannel;
import com.redoc.idu.contract.IDigest;
import com.redoc.idu.view.widget.IDelayLoadImageView;
import com.redoc.widget.PullToLoadMoreRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_FLING;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChannelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChannelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelFragment extends Fragment {

    @BindView(R.id.digests_view)
    PullToLoadMoreRecyclerView mDigestsView;

    private OnFragmentInteractionListener mListener;

    private IChannel.IChannelPresenter mChannelPresenter;

    private DigestsAdapter mDigestsAdapter;

    private int iteration = 0;

    public ChannelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DigestList.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelFragment newInstance() {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_channel, container, false);
        ButterKnife.bind(this, rootView);
        initializeView();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Update digests in the fragment.
     */
    public void updateDigests() {
        mDigestsView.forceRefresh();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Set presenter to the fragment.
     * @param channelPresenter Channel presenter.
     */
    public void setPresenter(IChannel.IChannelPresenter channelPresenter) {
        mChannelPresenter = channelPresenter;
        mChannelPresenter.getLatestDigests();
        mDigestsAdapter = channelPresenter.createDigestsAdapter();
    }

    private void initializeView() {
        mDigestsView.setLayoutManager(new LinearLayoutManager(IDuApplication.Context));
        TextView footer = new TextView(IDuApplication.Context);
        footer.setText("Get more");
        footer.setTextSize(15);
        footer.setTextColor(Color.BLACK);
        mDigestsView.setFooterView(footer);
        mDigestsView.setInnerAdapter(mDigestsAdapter);
        mDigestsView.addOnScrollListener(new DigestViewScrollListener());
    }

    /**
     * Digest view scroll listern.
     */
    class DigestViewScrollListener extends RecyclerView.OnScrollListener {
        /**
         * {@inheritDoc}
         */
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            mDigestsAdapter.setIsFirstScreen(false);
            if(newState == SCROLL_STATE_IDLE) {
                LoadVisibleImages((PullToLoadMoreRecyclerView) recyclerView);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int lastVisiblePosition = mDigestsView.getLastVisiblePosition();
            if(lastVisiblePosition == mDigestsAdapter.getItemCount()) {
                mChannelPresenter.getMoreDigests(++iteration * 20);
            }
        }
    }

    private void LoadVisibleImages(PullToLoadMoreRecyclerView recyclerView) {
        int firstVisiblePosition = recyclerView.getFirstVisiblePosition();
        int lastVisiblePosition = recyclerView.getLastVisiblePosition();
        if(firstVisiblePosition >= 0 && lastVisiblePosition >= 0 && firstVisiblePosition <= lastVisiblePosition) {
            for(int i = firstVisiblePosition; i <= lastVisiblePosition; i++) {
                if(i < mChannelPresenter.allCachedDigests().size()) {
                    IDigest.IDigestPresenter digestPresenter = mChannelPresenter.allCachedDigests().get(i);
                    digestPresenter.loadImages();
                }
            }
        }
    }
}