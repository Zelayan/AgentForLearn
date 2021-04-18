package com.zeze;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class test {

    @Test
    public void test01() throws IllegalAccessException, InstantiationException {
        String s = new ByteBuddy()
                .subclass(Object.class) //为Object.class构建一个子类
                .name("example.Type")   // 子类的名称
                .method(ElementMatchers.named("toString")) // 匹配Object.class的toString方法
                .intercept(FixedValue.value("Hello World!")) // 实现一个toString方法
                .make()
                .load(getClass().getClassLoader()) // 获取类加载器
                .getLoaded()  // 加载
                .newInstance() //构建实例
                .toString(); // 调用实例的toString方法

        System.out.println(s);
    }

    /**
     * 创建子类 不指定名字类名就是example.Object
     */
    @Test
    public void makeSonClass() {
        DynamicType.Unloaded<Object> make = new ByteBuddy()
                .subclass(Object.class)
                .make();
    }

    @Test
    public void makeSonClassWithPrefix() {
        DynamicType.Unloaded<Object> make = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription typeDescription) {
                        return "com.zeze" + typeDescription.getSimpleName();
                    }
                })
                .subclass(Object.class)
                .make();


    }
}
