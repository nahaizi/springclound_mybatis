package com.lili.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class SpringAopTest {

    public static void main(String[] args) {
        //1、定义切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");

        //2、定义通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                return methodInvocation.proceed();
            }
        };

        //3、定义切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        //4、创建代理
        Target1 target1 = new Target1();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target1);
        proxyFactory.addAdvisor(advisor);
        // proxyTargetClass = false; 目标实现了接口，采用JDK代理实现；
        // proxyTargetClass = false; 目标没有实现接口，采用cglib实现；
        // proxyTargetClass = true; 总是使用cglib实现；
        proxyFactory.setProxyTargetClass(false);

        F1 proxy = (F1) proxyFactory.getProxy();
        //查看代理类的类型
        System.out.println(proxy.getClass());
        proxy.foo();
        proxy.bar();

    }

    interface F1 {
        void foo();

        void bar();
    }

    static class Target1 implements F1 {
        @Override
        public void foo() {
            System.out.println("Target1 foo() ...");
        }

        @Override
        public void bar() {
            System.out.println("Target1 bar() ...");
        }
    }

    static class Target2 implements F1 {
        @Override
        public void foo() {
            System.out.println("Target2 foo() ...");
        }

        @Override
        public void bar() {
            System.out.println("Target2 bar() ...");
        }
    }
}


