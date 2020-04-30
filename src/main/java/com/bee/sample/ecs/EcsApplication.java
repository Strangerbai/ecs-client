package com.bee.sample.ecs;

import com.bee.sample.ecs.server.RpcServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class EcsApplication implements CommandLineRunner {

    @Resource
    RpcServer rpcServer;

    public static void main(String[] args) {
        SpringApplication.run(EcsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        rpcServer.start();
    }
}
