package com.bee.sample.ecs.entity;

public enum ResultCode {

    SUCCESS(200, "处理成功"),
    FAIL(400, "处理失败"),
    INTERNAL_SERVER_ERROR(500, "系统内部错误"),
    ;



    public Integer code;
    public String msg;
    ResultCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
