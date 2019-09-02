package com.ifspace.advertising.http;

import android.util.Log;

import com.ifspace.advertising.model.AdsData;

import io.reactivex.Observable;

/**
 * Created by sheng on 19/9/1.
 */
public class AdsFetcher {

    private static final String TAG = "AdsFetcher";

    private AdsAPI mTranslatorApi;

    public AdsFetcher(AdsAPI api) {
        this.mTranslatorApi = api;
    }

    public Observable<AdsData> fetchObservable(String imei, int showType) {

        Log.d(HttpClient.TAG, "请求参数:" + " showType:" + showType);
        Log.d(HttpClient.TAG, "请求参数:" + " imei:" + imei);
        Observable<AdsData> observable = mTranslatorApi.getIfspaceAdsObservable(imei, showType > 0 && showType <= 4 ? String.valueOf(showType):null);
        return observable;
    }
}
