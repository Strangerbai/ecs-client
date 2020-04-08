package com.bee.sample.ecs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWordController {

    protected static final Logger logger = LoggerFactory.getLogger(HelloWordController.class);

    @RequestMapping("/say.html")
    public @ResponseBody
    String say(){
        logger.info("start");
        return "hello spring boot";
    }
}
