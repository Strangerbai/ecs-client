package com.bee.sample.ecs;

import org.example.rpc.server.engine.RpcEngine;
import org.example.rpc.server.registry.RegistryCenter;
import org.example.rpc.server.server.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class EcsApplication implements CommandLineRunner{

    @Resource
    RpcEngine rpcEngine;

    public static void main(String[] args) {
        SpringApplication.run(EcsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        rpcEngine.getRpcServer().start();
    }
}
