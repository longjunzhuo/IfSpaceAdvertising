package com.ifspace.advertising.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ifspace.advertising.utils.Constants;
import com.ifspace.advertising.R;

/**
 * Created by sheng on 19/9/1.
 */
public class AdsTypeSelectActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.advertising_show_type_1).setOnClickListener(this);
        findViewById(R.id.advertising_show_type_2).setOnClickListener(this);
        findViewById(R.id.advertising_show_type_3).setOnClickListener(this);
        findViewById(R.id.advertising_show_type_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int showType = Constants.ShowType.SINGLE_IMG;
        switch (v.getId()) {
            case R.id.advertising_show_type_1:
                showType = Constants.ShowType.SINGLE_IMG;
                break;
            case R.id.advertising_show_type_2:
                showType = Constants.ShowType.TWO_IMG;
                break;
            case R.id.advertising_show_type_3:
                showType = Constants.ShowType.THREE_IMG;
                break;
            case R.id.advertising_show_type_4:
                showType = Constants.ShowType.FOUR_IMG;
                break;
        }
        startAdvertisingActivity(showType);
    }


    private void startAdvertisingActivity(int type) {
        Intent intent = new Intent(this, ShowAdsActivity.class);
        intent.putExtra(Constants.IntentExtra.SHOW_AD_SELECT_TYPE, type);
        startActivity(intent);
    }
}
