package com.bee.sample.ecs.controller.response;

import com.bee.sample.ecs.entity.ResultCode;
import lombok.Data;

@Data
public class Result {
    private int code;
    private String message;
    private Object data;
    public Result setCode(ResultCode resultCode){
        this.code = resultCode.code;
        return this;
    }

}
