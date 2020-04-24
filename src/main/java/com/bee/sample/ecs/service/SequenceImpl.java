package com.bee.sample.ecs.service;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SequenceImpl {


    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> getSequence(List<Integer> originList, Integer target){
        if(CollectionUtils.isEmpty(originList) || target<0){
            return new ArrayList<>();
        }

        for(Integer x : originList){
            List<Integer> resultTemp = new ArrayList<>();
//            boolean res = dfs(x, originList, target,resultTemp);
        }


        return null;

    }
//
//    private boolean dfs(Integer x, List<Integer> tempX, Integer target, List<Integer> resultTemp){
//    }





}
