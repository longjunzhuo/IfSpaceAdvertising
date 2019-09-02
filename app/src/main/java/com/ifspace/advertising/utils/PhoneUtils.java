package com.ifspace.advertising.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by sheng on 19/9/1.
 */
public class PhoneUtils {
    private static final String TAG = "PhoneUtils";
    private static String sIMEI;
    private static String sSN;

    /*
 *  获取设备imei；如果开机过程中去获取，可能会获取不到
 */
    public static String getIMEI(Context context) {
        if (TextUtils.isEmpty(sIMEI)) {
            // 这个处理是非必须的，因为METHOD_GET_DEVICE_ID本身做了这个处理；为了运行在其它手机平台，这里兼容处理
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                sIMEI = tm.getDeviceId();
            } catch (Exception ignore) {
                Log.w(TAG, "Exception: " + ignore.toString() + " - Cause: " + ignore.getCause());
            }
        }
        return sIMEI;
    }

  /*  *//*
     *  获取设备sn；
     *//*
    public static String getSN() {
//        return readTestIds()[1];
        if (!TextUtils.isEmpty(sSN)) {
            return sSN;
        }
        String sn = SystemProperties.get("ro.serialno");
        if (!TextUtils.isEmpty(sn)) {
            sSN = sn;
        }
        if (TextUtils.isEmpty(sSN) && Build.VERSION.SDK_INT >= 26) {
            try {
                sSN = (String) (ReflectHelper.invokeStatic("android.os.Build", "getSerial", new Object[]{}));
            } catch (Exception e) {

            }
        }
        return sSN;
    }*/
}
