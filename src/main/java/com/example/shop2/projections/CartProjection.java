package com.example.shop2.projections;
import java.util.*;

public class CartProjection {

    private final Map<String, Map<Long,Integer>> carts = new HashMap<>();

    public void onProductAdded(String cartId, Long productId, int qty) {
        carts.computeIfAbsent(cartId, id -> new HashMap<>()).merge(productId, qty, Integer::sum);
    }

    public void onProductRemoved(String cartId, Long productId) {
        Map<Long,Integer> map = carts.get(cartId);
        if (map != null) {
            map.remove(productId);
            if (map.isEmpty()) carts.remove(cartId);
        }
    }

    public Map<Long,Integer> getCartView(String cartId) {
        return carts.getOrDefault(cartId, Collections.emptyMap());
    }
}
