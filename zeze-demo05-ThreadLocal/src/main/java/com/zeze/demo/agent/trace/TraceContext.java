package com.zeze.demo.agent.trace;

/**
 *
 */
public class TraceContext {

    private static final ThreadLocal<String> trackLocal = new ThreadLocal<String>();

    public static void clear(){
        trackLocal.remove();
    }

    public static String getTraceId(){
        return trackLocal.get();
    }

    public static void setTraceId(String linkId){
        trackLocal.set(linkId);
    }

}
