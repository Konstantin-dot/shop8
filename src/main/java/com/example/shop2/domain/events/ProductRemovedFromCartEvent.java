package com.example.shop2.domain.events;
import com.example.shop2.es.Event;

public class ProductRemovedFromCartEvent extends Event {

    private final String cartId;
    private final Long productId;
    private final String userId;

    public ProductRemovedFromCartEvent(String cartId, Long productId, String userId) {
        this.cartId = cartId;
        this.productId = productId;
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

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "ProductRemovedFromCartEvent{" +
                "cartId='" + cartId + '\'' +
                ", productId=" + productId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
