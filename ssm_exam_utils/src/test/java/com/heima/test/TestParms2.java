package com.heima.test;

public class TestParms2 {

    int a =10;

    private static int num;

    public static void main(String[] args) {
        int a = 20;
        num=a;
        new Thread(new Runnable() {
            int a =30;
            @Override
            public void run() {
                int a =40;
                System.out.println(new TestParms2().a);
                System.out.println(TestParms2.num);
                System.out.println(this.a);
                System.out.println(a);
            }
        }).start();
    }
}
