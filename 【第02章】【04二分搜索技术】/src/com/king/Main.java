package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-02
 * Time: 08:25
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        System.out.println(BinarySearch.binarySearch(5, array));
        System.out.println(BinarySearch.binarySearch(11, array));
    }
}
