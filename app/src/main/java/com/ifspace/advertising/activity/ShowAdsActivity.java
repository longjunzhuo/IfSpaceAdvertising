package com.ifspace.advertising.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ifspace.advertising.utils.Constants;
import com.ifspace.advertising.R;
import com.ifspace.advertising.common.AdsManager;
import com.ifspace.advertising.model.AdsData;
import com.ifspace.advertising.model.AdsError;
import com.ifspace.advertising.utils.PhoneUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by sheng on 19/9/1.
 */
public class ShowAdsActivity extends Activity {

    private static final String TAG = "ShowAdsActivity";
    private ProgressBar mProgressBar;
    private View mShowContainer;
    private TextView mTitleView;
    private TextView mScrollTextView;
    private ImageView mImageView;
    private AdsManager mAdsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_advertising);
        initView();
        EventBus.getDefault().register(this);
        mAdsManager = AdsManager.getInstance();
        Intent intent = getIntent();
        if (intent != null) {
            int adsType = intent.getIntExtra(Constants.IntentExtra.SHOW_AD_SELECT_TYPE, 1);
            requestAds(adsType);
        }
    }

    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.show_ad_progressbar);
        mShowContainer = findViewById(R.id.show_ad_container);
        mTitleView = (TextView) findViewById(R.id.show_ad_title);
        mScrollTextView = (TextView) findViewById(R.id.show_ad_scrolltext);
        mImageView = (ImageView) findViewById(R.id.show_ad_iv);
        mProgressBar.setVisibility(View.VISIBLE);
        mShowContainer.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(AdsData data) {
        if (data != null) {
            Log.d(TAG, "adsData:" + data.toString());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showError(AdsError error) {
        Log.d(TAG, "ads request error:" + error.toString());
    }


    public void requestAds(int adsType) {
        String imei = PhoneUtils.getIMEI(this);
        if (!TextUtils.isEmpty(imei)) {
            mAdsManager.requestAds(imei, adsType);
        }
    }


}
