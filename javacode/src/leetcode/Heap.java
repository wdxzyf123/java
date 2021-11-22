package leetcode;

import java.util.Arrays;

//调整最重要，调整内部是从上往下的
//外部的调整也就是构建堆的时候是从下往上，从右到左的
public class Heap {
    public static void main(String[] args) {
        int[] arr = {10, 4, 5, 2, 1, 6, 3, 9, 7, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr) {
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjust(arr,i,arr.length);
        }
        for (int i = arr.length-1; i >0 ; i--) {
            swap(arr,0,i);
            adjust(arr,0,i);
        }
    }
    public static void adjust(int arr[], int i, int length) {
        int temp = arr[i];
        for(int k=2*i+1; k<length;k=2*k+1) {
            if(k+1 < length && arr[k+1] > arr[k])
                k++;
            if(arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            }
        }
        arr[i] = temp;
    }
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}