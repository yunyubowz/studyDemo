package com.example.demo.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 */
public class FourNumSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
           return null;
    }

    public static List<List<Integer>> getSum(List<List<Integer>> lists,List<Integer> list,int[] nums,int target){
        if(list.size()>4){
            if(sumList(list)==target) lists.add(list);
            list = new ArrayList<>();
        }else{
            for(int i:nums){
                list.add(i);
            }
        }

        return null;
    }

    public static int sumList(List<Integer> list){
        int num = 0;
        for(Integer integer:list){
            num +=integer;
        }
        return num;
    }
}
