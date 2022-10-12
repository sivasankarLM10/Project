package com.company;

public class OrderAgnosticBS {
    public static void main(String[] args) {
        int [] arr = {-5,-4,-2,0,2,4,10,20,30,40,50,60,70,80};
        int target = 10;
        int ans = orderBS (arr, target);
        System.out.println (ans);
    }

    static int orderBS(int[] arr, int target){
        int start = 0;
        int end = arr.length - 1;

        boolean isAsc;
        if(arr[start] < arr[end]){
            isAsc = true;
        }
        else{
            isAsc = false;
        }

        while(start <= end){
            int mid = start + (end - start) / 2;

            if(arr[mid] == target){
                return mid;
            }

            if(isAsc){
                if(target < arr[mid]){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }

            else{
                if(target > arr[mid]){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }
        }
        return  -1;
    }
}
