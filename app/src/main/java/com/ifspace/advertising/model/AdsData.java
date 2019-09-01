package com.ifspace.advertising.model;

import com.ifspace.advertising.http.AdsAPI;
import java.util.List;

/**
 * Created by sheng on 19/9/1.
 */
public class AdsData {

   /* {
        "code": 1,
            "msg": "ok",
            "data": {
            "type":2,
                "ads":[
                         {
                            "position":1,
                             "img":"http://www.baidu.com",
                             "img_title":"测试图片",
                             "rotary_content":"这是轮播文案。。。"
                           },
                         {
                            "position":2
                            "img":"http://www.baidu.com",
                             "img_title":"测试图片",
                            "rotary_content":"这是轮播文案。。。"
                        }
                     ]
            }
    }

    失败：
    {
        "code": 0,
            "msg": "获取信息失败"
    }
*/
    private int code = AdsAPI.RESULT_CODE_SUCCESS;
    private String msg;
    private AdsList data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AdsList getData() {
        return data;
    }

    public void setData(AdsList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AdsData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class AdsList {
        private String type;
        private List<Ads> ads;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Ads> getAds() {
            return ads;
        }

        public void setAds(List<Ads> ads) {
            this.ads = ads;
        }

        @Override
        public String toString() {
            return "AdsList{" +
                    "type='" + type + '\'' +
                    ", ads=" + ads +
                    '}';
        }
    }

    public static class Ads {

        private String img;
        private String img_title;
        private String rotary_content;
        private int position;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg_title() {
            return img_title;
        }

        public void setImg_title(String img_title) {
            this.img_title = img_title;
        }

        public String getRotary_content() {
            return rotary_content;
        }

        public void setRotary_content(String rotary_content) {
            this.rotary_content = rotary_content;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "AdsBean{" +
                    "img='" + img + '\'' +
                    ", img_title='" + img_title + '\'' +
                    ", rotary_content='" + rotary_content + '\'' +
                    ", position='" + position + '\'' +
                    '}';
        }
    }

}
