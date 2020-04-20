package com.bee.sample.ecs.controller;

import com.bee.sample.ecs.controller.response.Result;
import com.bee.sample.ecs.entity.EcsConstant;
import com.bee.sample.ecs.service.impl.BaseZookeeper;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/zk")
public class ZkController {

    @Resource
    BaseZookeeper baseZookeeper;


    @RequestMapping(value = "/create")
    public Result<Boolean> createNode() throws Exception {
        baseZookeeper.connectZookeeper(EcsConstant.ZK_HOST);
        Stat stat = baseZookeeper.checkExist("/mydubbo");
        if(stat.getCzxid() == 0){
            String create = baseZookeeper.createNode("/mydubbo", "test", CreateMode.PERSISTENT);
            log.info("create : {}", create);
            String data = baseZookeeper.getData("/mydubbo");
            log.info("data : {}", data);
        }else{
            String data = baseZookeeper.getData("/mydubbo");
            log.info("data : {}", data);
        }
        return Result.success(Boolean.TRUE);
    }


    @RequestMapping(value = "/delete")
    public Result<Boolean> deleteNode() throws Exception {
        baseZookeeper.connectZookeeper(EcsConstant.ZK_HOST);
        baseZookeeper.deleteNode("/mydubbo");
        return Result.success(Boolean.TRUE);
    }


}
