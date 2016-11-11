package com.redoc.idu.utils.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A http client.
 * Created by limen on 2016/10/31.
 */
public class HttpClient {
    private RequestQueue mRequestQueue;

    /**
     * Construct a http client.
     * @param context Context.
     */
    public HttpClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Add json object request
     * @param url Url
     * @param jsonRequest An json request.
     * @param listener On received listener.
     * @param errorListener Error listener.
     */
    public void addJsonObjectRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        mRequestQueue.add(new JsonObjectRequest(url, jsonRequest, listener, errorListener));
    }

    /**
     * Add json array request.
     * @param url Url
     * @param listener Json array response listener.
     * @param errorListener Error listener.
     */
    public void addJsonArrayRequest(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        mRequestQueue.add(new JsonArrayRequest(url, listener, errorListener));
    }
}
