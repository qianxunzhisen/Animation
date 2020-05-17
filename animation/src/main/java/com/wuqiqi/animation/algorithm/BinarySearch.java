package com.wuqiqi.animation.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        System.out.println("请输入一组数字");
        //Scanner scanner = new Scanner(System.in);
        int[] datas = new int[]{5,19,3,9,2,1,101,998,78,45};
       /* int i=0;
        while(i<datas.length){
            datas[i]=scanner.nextInt();
            i++;
        }*/
        printDatas("未排序",datas);
        printDatas("冒泡排序",getBubbleSort(datas));
        printDatas("快速排序",getQuickSort(datas));
        printDatas("选择排序",getSelectSort(datas));

        printDatas("直接插入排序",getInsertSort(datas));
        printDatas("希尔排序",getSheelSort(datas));

        //scanner.close();
    }

    /**
     * 冒泡排序，时间复杂度O(n^2),空间复杂度O(n)
     * 所谓冒泡排序就是从前到后遍历选出最大值放到最后一个，然后在遍历剩下的找出剩
     * 下中最大的放到倒数第二个，依次直至遍历到最后一个，也就是最小的放到第一个，
     * 使其有序。
     * @param datas
     * @return
     */
    public static int[] getBubbleSort(int[] datas){
        int[] result = new int[10];
        for(int i=0;i<result.length;i++){
            result[i] = datas[i];
        }
        for(int i=0;i<result.length;i++){
            for(int j=0;j<result.length-1-i;j++){
                if(result[j]>result[j+1]){
                    int temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                }
            }
        }
        return result;
    }

    /**
     * 快速排序
     * @param datas
     * @return
     */
    public static int[] getQuickSort(int[] datas){
        int[] result = new int[10];
        for(int i=0;i<result.length;i++){
            result[i] = datas[i];
        }
        quickSort(result,0,result.length-1);
        return result;
    }

    /**
     * 选择第一个数为p，小于p的数放在左边，大于p的数放在右边。
     * 递归的将p左边和右边的数都按照第一步进行，直到不能递归。
     * @param datas
     * @param start
     * @param end
     */
    public static void quickSort(int[] datas,int start,int end){
        if(start<end){
            int baseNum = datas[start];//选基准值
            int temp;//
            int i=start;
            int j=end;
            do{
                while((datas[i]<baseNum) && i<end){
                    i++;
                }
                while((datas[j]>baseNum)&&j>start){
                    j--;
                }
                if(i<=j){
                    temp = datas[i];
                    datas[i]= datas[j];
                    datas[j] = temp;
                    i++;
                    j--;
                }
            }while(i<=j);
            if(start<j){
                quickSort(datas,start,j);
            }
            if(end>i){
                quickSort(datas,i,end);
            }
        }
    }


    /**
     * 选择排序：选择排序就是从第一趟开始，用第一个元素和剩下中的每一个元素比较，如果比第一
     * 个小，就和第一个元素交换值，最后使得第一个元素中的值最小，第二趟选择出第二
     * 小的放到第二元素，依次，使得数组有序
     * @param datas
     * @return
     */

    public static int[] getSelectSort(int[] datas){
        int[] result = new int[10];
        for(int i=0;i<result.length;i++){
            result[i] = datas[i];
        }
        int minIndex;
        int temp;
        for(int i=0;i<result.length;i++){
            minIndex = i;
            for(int j=i+1;j<result.length;j++){
                if(result[minIndex]>result[j]){
                    temp = result[minIndex];
                    result[minIndex] = result[j];
                    result[j]=temp;
                }
            }
        }
        return result;
    }

    /**
     * 堆排序
     * @param datas
     * @return
     */

    public static int[] getHeapSort(int[] datas){
        int[] result = new int[10];
        for(int i=0;i<result.length;i++){
            result[i] = datas[i];
        }
        int arrayLength=result.length;
        //循环建堆
        for(int i=0;i<arrayLength-1;i++){
            //建堆

            buildMaxHeap(result,arrayLength-1-i);
            //交换堆顶和最后一个元素
            swap(result,0,arrayLength-1-i);
        }
        return result;
    }

    private static void swap(int[] data, int i, int j) {
        // TODO Auto-generated method stub
        int tmp=data[i];
        data[i]=data[j];
        data[j]=tmp;
    }
    //对data数组从0到lastIndex建大顶堆
    public static void buildMaxHeap(int[] data, int lastIndex) {
        // TODO Auto-generated method stub
        //从lastIndex处节点（最后一个节点）的父节点开始
        for(int i=(lastIndex-1)/2;i>=0;i--){
            //k保存正在判断的节点
            int k=i;
            //如果当前k节点的子节点存在
            while(k*2+1<=lastIndex){
                //k节点的左子节点的索引
                int biggerIndex=2*k+1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if(biggerIndex<lastIndex){
                    //若果右子节点的值较大
                    if(data[biggerIndex]<data[biggerIndex+1]){
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if(data[k]<data[biggerIndex]){
                    //交换他们
                    swap(data,k,biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k=biggerIndex;
                }else{
                    break;
                }
            }
        }
    }

    /**
     * 直接插入排序：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置，直到全部插入排序完为止
     * @param datas
     * @return
     */
    public static int[] getInsertSort(int[] datas){
        int[] result = new int[10];
        for(int i=0;i<result.length;i++){
            result[i] = datas[i];
        }
        for(int i=1;i<result.length;i++){
            int temp = result[i];
            int j;
            for(j=i-1;j>=0;j--){
                if(result[j]>temp){
                    result[j+1]=result[j];
                }else{
                    break;
                }
            }
            result[j+1] = temp;
        }
        return result;
    }

    /**
     * 插入排序
     * @param datas
     * @return
     */
    public  static int[] getSheelSort(int[] datas){
        int[] result = new int[10];
        for(int i=0;i<result.length;i++){
            result[i] = datas[i];
        }
        int d  = result.length;
        while (d!=0) {
            d=d/2;
            for (int x = 0; x < d; x++) {//分的组数
                for (int i = x + d; i < result.length; i += d) {//组中的元素，从第二个数开始
                    int j = i - d;//j为有序序列最后一位的位数
                    int temp = result[i];//要插入的元素
                    for (; j >= 0 && temp < result[j]; j -= d) {//从后往前遍历。
                        result[j + d] = result[j];//向后移动d位
                    }
                    result[j + d] = temp;
                }
            }
        }
        return result;
    }
    /**
     * 归并排序
     * 选择相邻两个数组成一个有序序列。
     * 选择相邻的两个有序序列组成一个有序序列。
     * 重复第二步，直到全部组成一个有序序列。
     */
    public static void mergeSort(int[] numbers, int left, int right) {
        int t = 1;// 每组元素个数
        int size = right - left + 1;
        while (t < size) {
            int s = t;// 本次循环每组元素个数
            t = 2 * s;
            int i = left;
            while (i + (t - 1) < size) {
                merge(numbers, i, i + (s - 1), i + (t - 1));
                i += t;
            }
            if (i + (s - 1) < right)
                merge(numbers, i, i + (s - 1), right);
        }
    }


    private static void merge(int[] data, int p, int q, int r) {
        int[] B = new int[data.length];
        int s = p;
        int t = q + 1;
        int k = p;
        while (s <= q && t <= r) {
            if (data[s] <= data[t]) {
                B[k] = data[s];
                s++;
            } else {
                B[k] = data[t];
                t++;
            }
            k++;
        }
        if (s == q + 1)
            B[k++] = data[t++];
        else
            B[k++] = data[s++];
        for (int i = p; i <= r; i++)
            data[i] = B[i];
    }

    /**
     * 基数排序
     * @param array
     */
    public void baseSort(int[] array) {
        //首先确定排序的趟数;
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int time = 0;
        //判断位数;
        while (max > 0) {
            max /= 10;
            time++;
        }
        //建立10个队列;
        List<ArrayList> queue = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }
        //进行time次分配和收集;
        for (int i = 0; i < time; i++) {
            //分配数组元素;
            for (int j = 0; j < array.length; j++) {
                //得到数字的第time+1位数;
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }
            int count = 0;//元素计数器;
            //收集队列元素;
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
    }

    /**
     * 二分查找，有序
     * @param datas
     * @param value
     * @param n
     * @return
     */
    public static int bindarySearch(int datas[],int value,int n){
        int low,high,mid;
        low = 0;
        high = n-1;
        while(low<=high){
            mid = (low+high)/2;
            if(datas[mid] == value)
                return mid;
            if(datas[mid]>value){
                high = mid-1;
            }
            if(datas[mid]<value){
                low = mid+1;
            }
        }
        return -1;
    }

   /* //二分查找，递归版本
    public static  int BinarySearch2(int a[], int value, int low, int high)
    {
        if(low>high)
           return -1;
        int mid = low+(high-low)/2;
        if(a[mid]==value)
            return mid;
        if(a[mid]>value)
            return BinarySearch2(a, value, low, mid-1);
        if(a[mid]<value)
            return BinarySearch2(a, value, mid+1, high);
    }*/

    public static void printDatas(String tips,int[] datas){
        System.out.print(tips+"数据为：");
        for (int data:datas) {
            System.out.print(data+",");
        }
        System.out.println("");
    }


}
