package com.ifspace.advertising.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ifspace.advertising.activity.AdsTypeSelectActivity;
import com.ifspace.advertising.utils.Constants;

/**
 * @author jiangxusheng@meizu.com
 */
public class AutoStartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d(Constants.TAG, "receiver boot complete");
            Intent adsSelectintent = new Intent(context, AdsTypeSelectActivity.class);
            context.startActivity(adsSelectintent);
        }
    }
}
