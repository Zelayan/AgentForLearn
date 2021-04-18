package com.zeze.demo.agent;


/**
 * -javaagent:G:\Java\AgentForLearn\zeze-demo02-agent02\target\zeze-demo002-agent02-1.0-SNAPSHOT.jar=testargs
 */
public class ApiTest {
    public static void main(String[] args) {
        ApiTest apiTest = new ApiTest();
        apiTest.echoHi();
    }

    private void echoHi(){
        System.out.println("hi agent");
    }
}