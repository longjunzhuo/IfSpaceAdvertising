package com.ifspace.advertising.model;

/**
 * Created by sheng on 19/9/1.
 */
public class AdsStatus {

    public static final int STATE_REQUEST_ERROR = 0;
    public static final int STATE_REQUEST_UPDATE = 1000;

    public int state;

    public AdsStatus(int state) {
        this.state = state;
    }
}
