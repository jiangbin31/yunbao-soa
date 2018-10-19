package org.dubbo.pojo.base;

import java.io.Serializable;

/**
 * Created by jiangbin on 2017/11/14.
 */
public class BaseResponse<T> implements Serializable{

    private String code ;
    private  T data;

    private String message;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
