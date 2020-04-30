package com.bee.sample.ecs.controller;

import com.bee.sample.ecs.client.RpcClient;
import com.bee.sample.ecs.controller.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rpc")
public class RpcController {

    @RequestMapping(value = "/send/{msg}")
    public Result<String> sendMsg(@PathVariable String msg) throws Exception {
        RpcClient rpcClient = new RpcClient();
        String result = rpcClient.send(msg);
        return Result.success(result);
    }



}
