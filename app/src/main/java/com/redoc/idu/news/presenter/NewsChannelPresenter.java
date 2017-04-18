package com.redoc.idu.news.presenter;

import android.widget.Toast;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.framework.contract.multichannel.IMultiChannelsCategoryContract;
import com.redoc.idu.framework.model.bean.Channel;
import com.redoc.idu.news.model.NewsDigest;
import com.redoc.idu.news.view.NewsDigestsAdapter;
import com.redoc.idu.framework.presenter.DigestsProvider;
import com.redoc.idu.framework.presenter.multichannel.MultiChannelPresenter;
import com.redoc.idu.framework.view.channel.DigestsAdapter;

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
        getOrCreateChannelView().updateDigests(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onReceiveLatestDigests(JSONObject jsonObject) throws JSONException {
        List<NewsDigest> digests = NewsDigestsJsonParser.parseJsonToNewsDigest(jsonObject, getChannelId());
        if(allCachedDigests().size() == 0 || !(digests.get(0).getDigestTitle().equals(allCachedDigests().get(0).getTitle()))) {
            List<NewsDigestPresenter> tempDigestsPresenter = new ArrayList<>();
            for(NewsDigest digest : digests) {
                tempDigestsPresenter.add(new NewsDigestPresenter(digest));
            }
            addDigestsToCachedDigestsHead(tempDigestsPresenter);
            getOrCreateChannelView().updateDigests(true);
            return;
        }
        Toast toast = Toast.makeText(IDuApplication.Context, "已是最新", Toast.LENGTH_SHORT);
        toast.show();
        getOrCreateChannelView().updateDigests(false);
    }
}
