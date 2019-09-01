package com.ifspace.advertising.http;

import com.ifspace.advertising.model.AdsData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sheng on 19/9/1.
 */
public interface AdsAPI {

    String BASE_URL = "http://ifadmin5.baiqu.tech";
    int RESULT_CODE_SUCCESS = 1;
    int RESULT_CODE_ERROR = 0;


    @POST("api/advertising/get_machine_ads")
    @FormUrlEncoded
    Observable<AdsData> getIfspaceAdsObservable(@Field(value = "machine_imei") String imei, @Field(value = "show_type") String showType);

}
