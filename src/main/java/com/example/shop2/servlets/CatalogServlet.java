package com.example.shop2.servlets;

import com.example.shop2.model.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/catalog"})
public class CatalogServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        List<Product> products = (List)context.getAttribute("products");
        req.setAttribute("products", products);
        req.getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }
}
