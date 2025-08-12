package com.example.shop2.domain.events;
import com.example.shop2.es.Event;

public class ProductAddedToCartEvent extends Event {

    private final String cartId;
    private final Long productId;
    private final int quantity;
    private final String userId;

    public ProductAddedToCartEvent(String cartId, Long productId, int quantity, String userId) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.userId = userId;
    }

    public String aggregateId() {
        return cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "ProductAddedToCartEvent{" +
                "cartId='" + cartId + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", userId='" + userId + '\'' +
                '}';
    }
}
