package com.heima.test;

import org.junit.Test;

import java.util.UUID;

public class TestParms {

    int a = 10;
    private static int num;

    @Test
    public void test1() {
        int a = 20;
        this.num = a;
        new Thread(new Runnable() {
            int a = 30;
            @Override
            public void run() {
                int a = 40;
                System.out.println(new TestParms().a);
                System.out.println(TestParms.num);
                System.out.println(this.a);
                System.out.println(a);
            }
        }).start();
    }

    @Test
    public void test2(){
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}
