package com.ifspace.advertising.model;

/**
 * Created by sheng on 19/9/1.
 */
public class BaseEntity<T> {

    public static int SUCCESS_CODE = 1;//成功的code

    /**
     * code : 0
     * message : success
     * data : {}
     */
    private int code = -1;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "BaseEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
