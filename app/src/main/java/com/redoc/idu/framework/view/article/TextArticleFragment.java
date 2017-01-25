package com.redoc.idu.framework.view.article;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.redoc.idu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextArticleFragment extends Fragment {

    @BindView(R.id.webView)
    WebView mWebView;

    public TextArticleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TextArticleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TextArticleFragment newInstance() {
        TextArticleFragment fragment = new TextArticleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_text_article, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }
}
