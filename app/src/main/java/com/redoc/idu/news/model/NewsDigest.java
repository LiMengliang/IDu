package com.redoc.idu.news.model;

import java.util.ArrayList;
import java.util.List;

/**
 * News digest model.
 * Created by limen on 2016/11/5.
 */
public class NewsDigest {
    /**
     * Id.
     */
    private String docId;
    public String getDocId() {
        return docId;
    }
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * Title.
     */
    private String digestTitle;
    public String getDigestTitle() {
        return digestTitle;
    }
    public void setDigestTitle(String title) {
        digestTitle = title;
    }

    /**
     * Time.
     */
    private String time;
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Digest.
     */
    private String digest;
    public String getDigest() {
        return digest;
    }
    public void setDigset(String digest) {
        this.digest = digest;
    }

    /**
     * Tag.
     */
    private String tag;
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Image links.
     */
    private List<String> mDigestImages = new ArrayList<String>();
    public List<String> getDigestImages() {
        return mDigestImages;
    }
    public void addDigestImage(String uri) {
        mDigestImages.add(uri);
    }

    /**
     * Links to the news article.
     */
    private String newsUrl;
    public String getNewsUrl() {
        return newsUrl;
    }
    public void setNewsUrl(String url) {
        newsUrl = url;
    }

    /**
     * Photo set id.
     */
    private String photoSetId;
    public String getPhotoSetId() {
        return photoSetId;
    }
    public void setPhotoSetId(String photoSetId) {
        this.photoSetId = photoSetId;
    }

    /**
     * Source of the news.
     */
    private String source;
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
}
