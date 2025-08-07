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

@WebServlet({"/product"})
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        List<Product> products = (List)context.getAttribute("products");
        String id = req.getParameter("id");
        Product product = (Product)products.stream().filter((item) -> item.getId().equals(Long.valueOf(id))).findFirst().orElseThrow();
        req.setAttribute("product", product);
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }
}
