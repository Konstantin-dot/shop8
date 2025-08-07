package com.example.shop2.servlets;

import com.example.shop2.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || !"admin1".equals(session.getAttribute("user"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Доступ запрещен");
            return;
        }

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));

        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        if (products == null) {
            products = new ArrayList<>();
        } else {
            products = new ArrayList<>(products);
        }

        long maxId = products.stream()
                .mapToLong(Product::getId)
                .max()
                .orElse(0);
        long newId = maxId + 1;

        Product newProduct = new Product(newId, name, description, price);
        products.add(newProduct);

        getServletContext().setAttribute("products", products);

        resp.sendRedirect("catalog");
    }
}