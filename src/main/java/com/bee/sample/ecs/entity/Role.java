package com.bee.sample.ecs.entity;

public enum Role {
    ADMIN(0,"admin"),
    EDITOR(1,"editor"),
    VISITOR(2,"visitor")
    ;

    String value;
    Integer code;

    Role(Integer code, String value){
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
