package com.example.shop2.aspects;

import com.example.shop2.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CartLoggingAspect {

    @Before("execution(* com.example.shop2.servlets.CartServlet.doPost(..))")
    public void logAddProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        HttpServletResponse response = (HttpServletResponse) args[1];

        HttpSession session = request.getSession(false);
        String user = session != null ? (String) session.getAttribute("user") : "anonymous";
        String productId = request.getParameter("id");
        System.out.println("[LOG] User '" + user + "' добавляет товар с id=" + productId + " в корзину.");
    }

    @Before("execution(* com.example.shop2.servlets.CartServlet.doDelete(..))")
    public void logRemoveProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        HttpServletResponse response = (HttpServletResponse) args[1];

        HttpSession session = request.getSession(false);
        String user = session != null ? (String) session.getAttribute("user") : "anonymous";
        String productId = request.getParameter("id");
        System.out.println("[LOG] User '" + user + "' удаляет товар с id=" + productId + " из корзины.");
    }

    private HttpSession getSession(JoinPoint joinPoint) {
        return null;
    }

}
