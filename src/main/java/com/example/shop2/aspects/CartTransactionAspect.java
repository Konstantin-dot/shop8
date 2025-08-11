package com.example.shop2.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class CartTransactionAspect {

    @Around("execution(* com.example.shop2.servlets.CartServlet.doPost(..)) || execution(* com.example.shop2.servlets.CartServlet.doDelete(..))")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("[TRANSACTION] Начало транзакции");
            Object result = joinPoint.proceed();
            System.out.println("[TRANSACTION] Коммит транзакции");
            return result;
        } catch (Exception e) {
            System.out.println("[TRANSACTION] Откат транзакции из-за ошибки: " + e.getMessage());
            throw e;
        }
    }
}
