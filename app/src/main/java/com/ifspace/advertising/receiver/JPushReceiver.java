package com.ifspace.advertising.receiver;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.ifspace.advertising.model.AdsStatus;
import com.ifspace.advertising.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * @author jiangxusheng
 */
public class JPushReceiver extends JPushMessageReceiver {

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        Log.d(Constants.TAG, "CustomMessage:" + customMessage.toString());
        String message = customMessage.message;
        if (!TextUtils.isEmpty(message)) {
            try {
                JSONObject jsonObject = new JSONObject(message);
                if (jsonObject.has("updateAds")) {
                    int updateAds = jsonObject.getInt("updateAds");
                    if (updateAds == 1) {
                        AdsStatus adsStatus = new AdsStatus(AdsStatus.STATE_REQUEST_UPDATE);
                        EventBus.getDefault().post(adsStatus);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
