package com.zeze.demo.agent.trace;

import java.util.Stack;

public class TraceManager {
    private static final ThreadLocal<Stack<String>> trace = new ThreadLocal<>();

    private static String createSpan() {
        Stack<String> stack = trace.get();
        if (stack == null) {
            stack = new Stack<>();
            trace.set(stack);
        }
        String traceId;
        if (stack.isEmpty()) {
            traceId = TraceContext.getTraceId();
            if (traceId == null) {
                traceId = "nil";
                TraceContext.setTraceId(traceId);
            }
        } else {
            traceId = stack.peek();
            TraceContext.setTraceId(traceId);
        }
        return traceId;
    }

    public static String createEntrySpan() {
        String span = createSpan();
        Stack<String> stack = trace.get();
        stack.push(span);
        return span;
    }


    public static String getExitSpan() {
        Stack<String> stack = trace.get();
        if (stack == null || stack.isEmpty()) {
            TraceContext.clear();
            return null;
        }
        return stack.pop();
    }

    public static String getLocalSpan() {
        Stack<String> stack = trace.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

}
