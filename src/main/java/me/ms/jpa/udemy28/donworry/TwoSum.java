package me.ms.jpa.udemy28.donworry;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0, length = nums.length ; i < length ; i++){
            for(int j = i + 1 ; j < nums.length ; j++){
                if( nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{-1,-2,-3,-4,-5}, -8)));
    }
}
