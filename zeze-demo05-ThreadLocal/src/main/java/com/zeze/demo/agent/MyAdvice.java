package com.zeze.demo.agent;

import com.zeze.demo.agent.trace.TraceContext;
import com.zeze.demo.agent.trace.TraceManager;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

public class MyAdvice {
    /**
     * 表示这个方法会在，进入目标方法时调用，这个注解声明的方法必须是static。当目标的方法是constructor构造器时，@This只能写field，不能读field，或者调用方法
     * @param className
     * @param methodName
     */
    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {

        String traceId = TraceManager.getLocalSpan();
        if (null == traceId) {
            traceId = UUID.randomUUID().toString();
            TraceContext.setTraceId(traceId);
        }
        String entrySpan = TraceManager.createEntrySpan();
        System.out.println("链路追踪：" + entrySpan + " " + className + "." + methodName);

    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        TraceManager.getExitSpan();
    }
}
