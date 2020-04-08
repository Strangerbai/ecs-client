package com.bee.sample.ecs.entity;

public enum ResultCode {

    SUCCESS(200),
    FAIL(400),
    INTERNAL_SERVER_ERROR(500)
    ;



    public int code;
    ResultCode(int code){
        this.code = code;
    }

}
