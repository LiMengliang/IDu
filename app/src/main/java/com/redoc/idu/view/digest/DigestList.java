package com.redoc.idu.view.digest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redoc.idu.R;
import com.redoc.idu.contract.IChannelContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DigestList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DigestList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DigestList extends Fragment {

    @BindView(R.id.channel_name)
    TextView mTextView;

    private OnFragmentInteractionListener mListener;

    private IChannelContract.IChannelPresenter mChannelPresenter;

    public DigestList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DigestList.
     */
    // TODO: Rename and change types and number of parameters
    public static DigestList newInstance() {
        DigestList fragment = new DigestList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_digest_list, container, false);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    public void setPresenter(IChannelContract.IChannelPresenter channelPresenter) {
        mChannelPresenter = channelPresenter;
    }

    private void initializeView() {
        mTextView.setText(mChannelPresenter.getChannelName());
    }
}
