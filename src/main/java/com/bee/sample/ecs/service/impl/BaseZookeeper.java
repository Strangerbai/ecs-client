package com.bee.sample.ecs.service.impl;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class BaseZookeeper implements Watcher {


    private static ZooKeeper zookeeper;
    private static final int SESSION_TIME_OUT = 2000;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("Watch received event");
            countDownLatch.countDown();
        }
    }

    public void connectZookeeper(String host) throws Exception{
        zookeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
        countDownLatch.await();
        System.out.println("zookeeper connection success");
    }



    public String createNode(String path,String data, CreateMode createMode) throws Exception{
        return zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
    }

    public List<String> getChildren(String path) throws KeeperException, InterruptedException{
        List<String> children = zookeeper.getChildren(path, false);
        return children;
    }


    public String getData(String path) throws KeeperException, InterruptedException{
        byte[] data = zookeeper.getData(path, false, null);
        if (data == null) {
            return "";
            }
        return new String(data);
    }

    public Stat checkExist(String path) throws KeeperException, InterruptedException {
        return zookeeper.exists(path, false);
    }



    public Stat setData(String path, String data) throws KeeperException, InterruptedException{
        Stat stat = zookeeper.setData(path, data.getBytes(), -1);
        return stat;
    }


    public void deleteNode(String path) throws InterruptedException, KeeperException{
        zookeeper.delete(path, -1);
    }

}
