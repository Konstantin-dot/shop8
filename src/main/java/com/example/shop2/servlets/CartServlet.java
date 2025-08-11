package com.example.shop2.servlets;

import com.example.shop2.model.Cart;
import com.example.shop2.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        if ("delete".equalsIgnoreCase(method)) {
            doDelete(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", "testUser");
        }

        if (session == null || session.getAttribute("user") == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Требуется вход в систему");
            return;
        }

        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect("cart");
            return;
        }
        long id = Long.parseLong(idStr);

        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (product == null) {
            resp.sendRedirect("cart");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addProduct(product);
        session.setAttribute("cart", cart);

        resp.sendRedirect("cart");
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("user", "testUser");
        }

        if (session == null || session.getAttribute("user") == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Требуется вход в систему");
            return;
        }

        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect("cart");
            return;
        }
        long id = Long.parseLong(idStr);

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            Product productToRemove = cart.getProducts().stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (productToRemove != null) {
                cart.removeProduct(productToRemove);
            }
            session.setAttribute("cart", cart);
        }
        resp.sendRedirect("cart");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

}
