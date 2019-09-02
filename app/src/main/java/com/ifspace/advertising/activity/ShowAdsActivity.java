package com.ifspace.advertising.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.percent.PercentLayoutHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ifspace.advertising.utils.Constants;
import com.ifspace.advertising.R;
import com.ifspace.advertising.common.AdsManager;
import com.ifspace.advertising.model.AdsData;
import com.ifspace.advertising.model.AdsStatus;
import com.ifspace.advertising.utils.PhoneUtils;
import com.ifspace.advertising.widget.PercentLinearLayout;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by sheng on 19/9/1.
 */
public class ShowAdsActivity extends Activity {

    private static final String TAG = "ShowAdsActivity";
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

    private ProgressBar mProgressBar;
    private ViewGroup mShowContainer;
    private View mAdsGroupView;

    private View mBottomLeftItemView;
    private View mTopLeftItemView;
    private View mTopRightItemView;
    private View mBottomItemView;
    private View mBottomRightItemView;
    private ImageView mTopLeftIv;
    private ImageView mTopRightIv;
    private ImageView mBottomLeftIv;
    private ImageView mBottomRightIv;
    private TextView mTopLeftTitleTv;
    private TextView mTopRightTitleTv;
    private TextView mBottomLeftTitleTv;
    private TextView mBottomRightTitleTv;
    private TextView mTopLeftScrollTextTv;
    private TextView mTopRightScrollTextTv;
    private TextView mBottomLeftScrollTextTv;
    private TextView mBottomRightScrollTextTv;


    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.show_ad_progressbar);
        mShowContainer = (ViewGroup) findViewById(R.id.show_ad_container);

        initAdsGroup();

        mProgressBar.setVisibility(View.VISIBLE);
        mShowContainer.setVisibility(View.GONE);
    }

    private void initAdsGroup() {
        mAdsGroupView = LayoutInflater.from(this).inflate(R.layout.show_ads_group_percent_layout, null);
        mShowContainer.removeAllViews();
        mShowContainer.addView(mAdsGroupView);

        mTopLeftItemView = findViewById(R.id.show_ad_top_left_item);
        mTopRightItemView = findViewById(R.id.show_ad_top_right_item);
        mBottomItemView = findViewById(R.id.show_ad_bottom_item);
        mBottomLeftItemView = findViewById(R.id.show_ad_bottom_left_item);
        mBottomRightItemView = findViewById(R.id.show_ad_bottom_right_item);

        mTopLeftTitleTv = (TextView) findViewById(R.id.show_ad_top_left_title);
        mTopRightTitleTv = (TextView) findViewById(R.id.show_ad_top_right_title);
        mBottomLeftTitleTv = (TextView) findViewById(R.id.show_ad_bottom_left_title);
        mBottomRightTitleTv = (TextView) findViewById(R.id.show_ad_bottom_right_title);

        mTopLeftScrollTextTv = (TextView) findViewById(R.id.show_ad_top_left_scrolltext);
        mTopRightScrollTextTv = (TextView) findViewById(R.id.show_ad_top_right_scrolltext);
        mBottomLeftScrollTextTv = (TextView) findViewById(R.id.show_ad_bottom_left_scrolltext);
        mBottomRightScrollTextTv = (TextView) findViewById(R.id.show_ad_bottom_right_scrolltext);

        mTopLeftIv = (ImageView) findViewById(R.id.show_ad_top_left_iv);
        mTopRightIv = (ImageView) findViewById(R.id.show_ad_top_right_iv);
        mBottomLeftIv = (ImageView) findViewById(R.id.show_ad_bottom_left_iv);
        mBottomRightIv = (ImageView) findViewById(R.id.show_ad_bottom_right_iv);
    }

    public void requestAds(int adsType) {
        String imei = PhoneUtils.getIMEI(this);
        if (!TextUtils.isEmpty(imei)) {
            mAdsManager.requestAds(imei, adsType);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateAdsData(AdsData data) {
        if (data != null) {
            Log.d(TAG, "adsData:" + data.toString());
            updateView(data);
            mProgressBar.setVisibility(View.GONE);
            mShowContainer.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mShowContainer.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateAdsStatus(AdsStatus adsStatus) {
        Log.d(TAG, "ads request:" + adsStatus.toString());
        if (adsStatus.state == AdsStatus.STATE_REQUEST_UPDATE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mShowContainer.setVisibility(View.GONE);
            initAdsGroup();
            requestAds(-1);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mShowContainer.setVisibility(View.GONE);
        }
    }


    private void updateView(AdsData data) {
        AdsData.AdsList adsData = data.getData();
        if (adsData != null) {
            int showType = adsData.getType();
            List<AdsData.Ads> adList = adsData.getAds();
            switch (showType) {
                case Constants.ShowType.SINGLE_IMG:
                    mTopRightItemView.setVisibility(View.GONE);
                    mBottomItemView.setVisibility(View.GONE);
                    setWidthPercent(mTopLeftItemView, 1.0f);
                    updateItemView(mTopLeftIv, mTopLeftTitleTv, mTopLeftScrollTextTv, adList.get(0));
                    break;
                case Constants.ShowType.TWO_IMG:
                    mBottomItemView.setVisibility(View.GONE);
                    mTopRightItemView.setVisibility(View.VISIBLE);
                    updateItemView(mTopRightIv, mTopRightTitleTv, mTopRightScrollTextTv, adList.get(1));
                    updateItemView(mTopLeftIv, mTopLeftTitleTv, mTopLeftScrollTextTv, adList.get(0));
                    break;
                case Constants.ShowType.THREE_IMG:
                    mBottomRightItemView.setVisibility(View.GONE);
                    mTopRightItemView.setVisibility(View.VISIBLE);
                    mBottomItemView.setVisibility(View.VISIBLE);
                    setWidthPercent(mBottomLeftItemView, 1.0f);
                    updateItemView(mTopLeftIv, mTopLeftTitleTv, mTopLeftScrollTextTv, adList.get(0));
                    updateItemView(mTopRightIv, mTopRightTitleTv, mTopRightScrollTextTv, adList.get(1));
                    updateItemView(mBottomLeftIv, mBottomLeftTitleTv, mBottomLeftScrollTextTv, adList.get(2));
                    break;
                case Constants.ShowType.FOUR_IMG:
                    mTopRightItemView.setVisibility(View.VISIBLE);
                    mBottomItemView.setVisibility(View.VISIBLE);
                    mBottomRightItemView.setVisibility(View.VISIBLE);
                    updateItemView(mTopLeftIv, mTopLeftTitleTv, mTopLeftScrollTextTv, adList.get(0));
                    updateItemView(mTopRightIv, mTopRightTitleTv, mTopRightScrollTextTv, adList.get(1));
                    updateItemView(mBottomLeftIv, mBottomLeftTitleTv, mBottomLeftScrollTextTv, adList.get(2));
                    updateItemView(mBottomRightIv, mBottomRightTitleTv, mBottomRightScrollTextTv, adList.get(3));
                    break;
                default:
                    break;
            }
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mShowContainer.setVisibility(View.GONE);
        }
    }

    private void updateItemView(ImageView iv, TextView titleView, TextView scrollTextView, AdsData.Ads ads) {
        if (ads != null) {
            String title = ads.getImg_title();
            String scrollText = ads.getRotary_content();
            if (!TextUtils.isEmpty(title)) {
                titleView.setVisibility(View.VISIBLE);
                titleView.setText(title);
            } else {
                titleView.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(scrollText)) {
                scrollTextView.setVisibility(View.VISIBLE);
                scrollTextView.setText(scrollText);
                scrollTextView.setSelected(true);
            } else {
                scrollTextView.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(ads.getImg())) {
                Picasso.with(this)
                        .load(ads.getImg())
                        .fit()
                        .into(iv);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static void setWidthPercent(View view, float width) {
        PercentLayoutHelper.PercentLayoutParams params = (PercentLayoutHelper.PercentLayoutParams) view.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
        info.widthPercent = width;
    }
}
