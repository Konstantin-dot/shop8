package com.example.shop2.servlets;

import com.example.shop2.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Требуется вход в систему");
            return;
        }

        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect("catalog");
            return;
        }
        long id = Long.parseLong(idStr);

        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (product == null) {
            resp.sendRedirect("catalog");
            return;
        }

        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(product);
        session.setAttribute("cart", cart);

        resp.sendRedirect("cart");
    }
}
