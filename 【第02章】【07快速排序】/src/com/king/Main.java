package com.king;

import java.util.Random;

/**
 * Author: 王俊超
 * Date: 2015-01-02
 * Time: 13:11
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Integer[] array = new Integer[30];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }

        for (Integer i : array) {
            System.out.print(i + ", ");
        }
        System.out.println();

        QuickSort.quickSort(array);

        for (Integer anArray : array) {
            System.out.print(anArray + ", ");
        }
        System.out.println();
    }
}
