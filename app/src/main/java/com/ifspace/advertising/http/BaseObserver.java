package com.ifspace.advertising.http;

import android.accounts.NetworkErrorException;
import android.util.Log;


import com.ifspace.advertising.model.BaseEntity;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by sheng on 19/9/1.
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    private static final String TAG = "BaseObserver";

    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
        Log.d(TAG, "request start");
    }

    @Override
    public void onNext(BaseEntity<T> value) {
        Log.d(TAG, "onNext");

        if (value.getCode() == getSuccessCode()) {
            try {
                onSuccees(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError " + e.getMessage());
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void onComplete() {
        onRequestEnd();
        Log.d(TAG, "onComplete");
    }

    protected void onRequestStart() {
    }

    protected void onRequestEnd() {
    }

    protected int getSuccessCode() {
        return BaseEntity.SUCCESS_CODE;
    }


    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseEntity<T> t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseEntity<T> t) throws Exception {
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

}
