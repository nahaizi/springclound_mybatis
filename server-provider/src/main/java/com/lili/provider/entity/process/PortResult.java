/*
 * Copyright (c) 2020. 兆尹科技
 */
package com.lili.provider.entity.process;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口返回结果
 *
 * @author luofeilong
 * @date 2020/1/8
 */
public class PortResult {

    /**
     * 编码：成功
     */
    public static final int RESULT_SUCCESS = 0;

    /**
     * 编码：失败
     */
    public static final int RESULT_FAIL = 1;

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回对象
     */
    private Object data;

    public PortResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PortResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static PortResult success(String msg) {
        return success(msg, null);
    }

    public static PortResult success(String msg, Object data) {
        return new PortResult(RESULT_SUCCESS, msg, data);
    }

    public static PortResult fail(String msg) {
        return fail(RESULT_FAIL, msg, null);
    }

    public static PortResult fail(String msg, Object data) {
        return fail(RESULT_FAIL, msg, data);
    }

    public static PortResult fail(int code, String msg) {
        return fail(code, msg, null);
    }

    public static PortResult fail(int code, String msg, Object data) {
        return new PortResult(code, msg, data);
    }

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

    public <T> T getData(Class<T> classOfT) {
        if(data == null){
            return null;
        }else if (data instanceof String) {
            if (((String) data).trim().length() == 0) {
                return null;
            }
        }
        return JSONObject.parseObject(data.toString(), classOfT);
    }
    public <T> List<T> getDataList(Class<T> classOfT) {
        if(data == null){
            return new ArrayList<T>();
        } else if (data instanceof String) {
            if (((String) data).trim().length() == 0) {
                return new ArrayList<T>();
            }
        }

        if(data == null){
            return new ArrayList<T>();
        }
        if (data instanceof JSONArray) {
            // JSON数组则直接转化
            return JSONArray.parseArray(data.toString(),classOfT);
        }
        // 非json数组，先转化为单个实体
        T datanew = JSONObject.parseObject(data.toString(), classOfT);
        // 再添加到list中返回
        List<T> list = new ArrayList<T>();
        list.add(datanew);
        return  list;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
