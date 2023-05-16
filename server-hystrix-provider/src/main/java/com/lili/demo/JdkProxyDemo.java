package com.lili.demo;

import java.lang.reflect.Proxy;

public class JdkProxyDemo {

    interface Foo{
        void foo();
    }

    static class Target implements Foo{
        @Override
        public void foo() {
            System.out.println(" target foo");
        }
    }

    /**
     * jdk代理只能针对接口进行代理
     * 内部采用asm技术动态生成字节码（）
     * @param args
     */
    public static void main(String[] args) {

        Target target = new Target();

        ClassLoader classLoader = JdkProxyDemo.class.getClassLoader();
        //proxy 代理对象自身
        //method 正在执行的方法
        //方法参数
        Foo proxy = (Foo) Proxy.newProxyInstance(classLoader, new Class[]{Foo.class}, (proxy1, method, args1) -> {
            System.out.println("before");
            Object result = method.invoke(target, args1);
            //代理类返回的是目标方法执行的结果
            System.out.println("after");
            return result;
        });
        proxy.foo();
    }
}
