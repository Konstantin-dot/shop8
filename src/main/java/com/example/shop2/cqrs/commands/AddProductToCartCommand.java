package com.example.shop2.cqrs.commands;
import com.example.shop2.cqrs.Command;

public class AddProductToCartCommand implements Command {

    public final String cartId;
    public final Long productId;
    public final int qty;
    public final String userId;

    public AddProductToCartCommand(String cartId, Long productId, int qty, String userId) {
        this.cartId = cartId;
        this.productId = productId;
        this.qty = qty;
        this.userId = userId;
    }
}
