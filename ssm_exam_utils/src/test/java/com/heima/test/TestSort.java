package com.heima.test;

import java.util.Arrays;

public class TestSort implements Runnable{
    private int num;

    public TestSort(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        int[] nums = {15,128,64,586,18};
        for (int num : nums) {
            new Thread(new TestSort(num)).start();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.num);
            System.out.println(this.num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
