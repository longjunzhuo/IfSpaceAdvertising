package com.ifspace.advertising.model;

/**
 * Created by sheng on 19/9/1.
 */
public class AdsError {

    public static final int STATE_REQUEST_ERROR = 0;

    public int state;

    public AdsError(int state) {
        this.state = state;
    }
}
