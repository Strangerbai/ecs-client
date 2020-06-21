package com.bee.sample.ecs.controller.response;

import lombok.Data;

import java.util.Date;

@Data
public class BlogVo {

    private Long id;

    private String name;

    private String title;

    private String description;

    private Byte status;

    private String gmtCreate;

    private String content;

}
