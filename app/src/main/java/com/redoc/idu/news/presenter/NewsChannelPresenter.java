package com.redoc.idu.news.presenter;

import com.redoc.idu.contract.multichannel.IMultiChannelsCategoryContract;
import com.redoc.idu.model.bean.Channel;
import com.redoc.idu.news.model.NewsDigest;
import com.redoc.idu.news.view.NewsDigestsAdapter;
import com.redoc.idu.presenter.DigestsProvider;
import com.redoc.idu.presenter.multichannel.MultiChannelPresenter;
import com.redoc.idu.view.channel.DigestsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * News channel presenter.
 * Created by limen on 2016/11/2.
 */
public class NewsChannelPresenter extends MultiChannelPresenter {
    private DigestsProvider mDigestsProvider;

    /**
     * Construct a instance.
     * @param channel Channel model.
     * @param category Category model.
     */
    public NewsChannelPresenter(Channel channel, IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter category) {
        super(channel, category);
        mDigestsProvider = new DigestsProvider(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getMoreDigests(int index) {
        mDigestsProvider.fetchMore(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getLatestDigests() {
        mDigestsProvider.fetchLatest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DigestsAdapter createDigestsAdapter() {
        NewsDigestsAdapter digestsAdapter = new NewsDigestsAdapter();
        digestsAdapter.setPresenter(this);
        return digestsAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onReceiveMoreDigests(JSONObject jsonObject) throws JSONException {
        List<NewsDigest> digests = NewsDigestsJsonParser.parseJsonToNewsDigest(jsonObject, getChannelId());
        List<NewsDigestPresenter> tempDigestsPresenter = new ArrayList<>();
        for(NewsDigest digest : digests) {
            tempDigestsPresenter.add(new NewsDigestPresenter(digest));
        }
        addDigestsToCachedDigestsEnd(tempDigestsPresenter);
        getOrCreateChannelView().updateDigests();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onReceiveLatestDigests(JSONObject jsonObject) throws JSONException {
        List<NewsDigest> digests = NewsDigestsJsonParser.parseJsonToNewsDigest(jsonObject, getChannelId());
        List<NewsDigestPresenter> tempDigestsPresenter = new ArrayList<>();
        for(NewsDigest digest : digests) {
            tempDigestsPresenter.add(new NewsDigestPresenter(digest));
        }
        addDigestsToCachedDigestsHead(tempDigestsPresenter);
        getOrCreateChannelView().updateDigests();
    }
}
