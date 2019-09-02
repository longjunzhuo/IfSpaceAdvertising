package com.ifspace.advertising.common;

import android.util.Log;

import com.ifspace.advertising.http.AdsAPI;
import com.ifspace.advertising.http.AdsFetcher;
import com.ifspace.advertising.http.HttpRequestFactor;
import com.ifspace.advertising.model.AdsData;
import com.ifspace.advertising.model.AdsStatus;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sheng on 19/9/1.
 */
public class AdsManager {

    private static final String TAG = "AdsManager";
    private static AdsManager sInstance;

    private AdsAPI mAdsApi;
    private AdsFetcher mFetcher;

    public static AdsManager getInstance() {
        if (sInstance == null) {
            synchronized (AdsManager.class) {
                if (sInstance == null) {
                    sInstance = new AdsManager();
                }
            }
        }
        return sInstance;
    }


    private AdsManager() {
        mAdsApi = HttpRequestFactor.getInstance().createService(AdsAPI.class, AdsAPI.BASE_URL);
        mFetcher = new AdsFetcher(mAdsApi);
    }

    public void requestAds(final String imei, int showType) {
        Observable<AdsData> observable = mFetcher.fetchObservable(imei, showType);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AdsData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AdsData adsData) {
                        Log.d(TAG, "onNext：" + adsData.toString());
                        if (adsData.getCode() == AdsAPI.RESULT_CODE_SUCCESS && adsData.getData() != null) {
                            notifyAdsData(adsData);
                        } else {
                            notifyRequestError(adsData.getCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError:" + e.getMessage());
                        notifyRequestError(AdsStatus.STATE_REQUEST_ERROR);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }

    private void notifyAdsData(AdsData adsData) {
        EventBus.getDefault().post(adsData);
    }

    private void notifyRequestError(int type) {
        AdsStatus adsStatus = new AdsStatus(type);
        EventBus.getDefault().post(adsStatus);
    }
}
