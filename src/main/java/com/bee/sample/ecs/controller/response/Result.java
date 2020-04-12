package com.bee.sample.ecs.controller.response;

import com.bee.sample.ecs.entity.ResultCode;

import java.io.Serializable;

/**
 * @author xuwei
 * @date 2019/6/14 5:54 PM
 */
public class Result<T> {

    /**
     * 返回数据
     */
    T data;

    /**
     * 返回结果码
     */
    Integer code;

    /**
     * 返回结果消息
     */
    String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result() {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMsg();
    }

    public static <T> Result success(T data) {
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setCode(ResultCode.SUCCESS.getCode());
        return result;
    }

    public static Result fail(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result fail(ResultCode respStatus) {
        Result result = new Result();
        result.setCode(respStatus.getCode());
        result.setMessage(respStatus.getMsg());
        return result;
    }
}
