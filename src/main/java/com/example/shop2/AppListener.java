package com.example.shop2;

import com.example.shop2.model.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> map = new HashMap();
        map.put("user1", "user1");
        map.put("admin1", "admin1");
        List<Product> products = List.of(
                new Product(1L, "Banan", "BananDescription", (double)50.0F),
                new Product(2L, "Apple", "AppleDescription", (double)100.0F),
                new Product(3L, "Lemon", "LemonDescription", (double)200.0F),
                new Product(4L, "Cherry", "CherryDescription", (double)300.0F));
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("products", products);
        servletContext.setAttribute("users", map);
    }
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
