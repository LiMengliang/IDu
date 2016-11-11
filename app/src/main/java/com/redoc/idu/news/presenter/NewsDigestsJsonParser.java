package com.redoc.idu.news.presenter;

import com.redoc.idu.news.model.NewsDigest;
import com.redoc.idu.utils.json.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Json parser for digests.
 * Created by limen on 2016/11/5.
 */
public class NewsDigestsJsonParser {
    private static final String title = "title";
    private static final String digest = "digest";
    private static final String docid = "docid";
    private static final String source = "source";
    private static final String ptime = "ptime";
    private static final String imgextra = "imgextra";
    private static final String imgsrc = "imgsrc";
    private static final String url = "url";
    private static final String photoSetId = "photosetID";

    /**
     * Parse json to news digests.
     * @param jsonObject Json object
     * @param channelId Channel id.
     * @return News digests.
     * @throws JSONException Throws json exception.
     */
    public static List<NewsDigest> parseJsonToNewsDigest(JSONObject jsonObject,
                                                        String channelId) throws JSONException {
        List<NewsDigest> newsDigests = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray(channelId);
        for(int i = 0; i < jsonArray.length(); i++) {
            NewsDigest newsDigest = new NewsDigest();
            JSONObject digestJSONObject = jsonArray.getJSONObject(i);
            newsDigest.setDigestTitle(JsonUtils.getString(title, digestJSONObject));
            newsDigest.setDigset(JsonUtils.getString(digest, digestJSONObject));
            newsDigest.setDocId(JsonUtils.getString(docid, digestJSONObject));
            newsDigest.setSource(JsonUtils.getString(source, digestJSONObject));
            newsDigest.setTime(JsonUtils.getString(ptime, digestJSONObject));
            if(digestJSONObject.has(imgextra)) {
                JSONArray imageSourceJSONArray = digestJSONObject.getJSONArray(imgextra);
                for(int j = 0; j < imageSourceJSONArray.length(); j++) {
                    newsDigest.addDigestImage(JsonUtils.getString(imgsrc, imageSourceJSONArray.getJSONObject(j)));
                }
            }
            newsDigest.addDigestImage(JsonUtils.getString(imgsrc, digestJSONObject));
            if(digestJSONObject.has(url)) {
                newsDigest.setNewsUrl(JsonUtils.getString(url, digestJSONObject));
            }
            if(digestJSONObject.has(photoSetId)) {
                newsDigest.setPhotoSetId(JsonUtils.getString(photoSetId, digestJSONObject));
            }
            newsDigests.add(newsDigest);
        }
        return newsDigests;
    }
}
