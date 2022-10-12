package com.company;

//https://leetcode.com/problems/peak-index-in-a-mountain-array/

public class peakIndexMountainarray {
    public static void main(String[] args) {
        int[] arr = {0,2,1,0};
        int ans = PeakElement (arr);
        System.out.println (ans);
    }
    public static int PeakElement(int[] arr){
        int start = 0;
        int end = arr.length-1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if(arr[mid]>arr[mid+1]){
                end = mid;
            } else{
                start = mid +1;
            }
        }
      return  start;
    }
}
