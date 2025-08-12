package com.example.shop2.domain;
import com.example.shop2.domain.events.ProductAddedToCartEvent;
import com.example.shop2.domain.events.ProductRemovedFromCartEvent;
import java.util.*;

public class CartAggregate {

    private final String id;
    private final String ownerId;
    private final Map<Long, Integer> items = new HashMap<>(); // productId -> qty

    public CartAggregate(String id, String ownerId) {
        this.id = id;
        this.ownerId = ownerId;
    }

    public static CartAggregate fromEvents(String id, String ownerId, List<com.example.shop2.es.Event> events) {
        CartAggregate cart = new CartAggregate(id, ownerId);
        for (com.example.shop2.es.Event e : events) {
            cart.apply(e);
        }
        return cart;
    }

    public void apply(com.example.shop2.es.Event e) {
        if (e instanceof ProductAddedToCartEvent) {
            ProductAddedToCartEvent ev = (ProductAddedToCartEvent)e;
            items.merge(ev.getProductId(), ev.getQuantity(), Integer::sum);
        } else if (e instanceof ProductRemovedFromCartEvent) {
            ProductRemovedFromCartEvent ev = (ProductRemovedFromCartEvent)e;
            items.remove(ev.getProductId());
        }
    }

    public ProductAddedToCartEvent addProduct(Long productId, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Qty must > 0");
        return new ProductAddedToCartEvent(this.id, productId, qty, this.ownerId);
    }

    public ProductRemovedFromCartEvent removeProduct(Long productId) {
        if (!items.containsKey(productId)) throw new IllegalStateException("Product not in cart");
        return new ProductRemovedFromCartEvent(this.id, productId, this.ownerId);
    }

    public Map<Long,Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
