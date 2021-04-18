package com.zeze.demo.agent;



public class ApiTest {
    public static void main(String[] args) {

        new ApiTest().http1();


    }


    public void http1() {
        System.out.println("测试结果：hi1");
        http2();
    }

    public void http2() {
        System.out.println("测试结果：hi2");
        http3();
    }

    public void http3() {
        System.out.println("测试结果：hi3");
    }

}