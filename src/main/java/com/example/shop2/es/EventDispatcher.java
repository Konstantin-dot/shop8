package com.example.shop2.es;
import com.example.shop2.domain.events.ProductAddedToCartEvent;
import com.example.shop2.domain.events.ProductRemovedFromCartEvent;
import com.example.shop2.projections.CartProjection;

public class EventDispatcher {

    private final CartProjection cartProjection;

    public EventDispatcher(CartProjection p) {
        this.cartProjection = p;
    }

    public void dispatch(Event e) {
        System.out.println("Dispatching " + e);
        if (e instanceof ProductAddedToCartEvent) {
            ProductAddedToCartEvent ev = (ProductAddedToCartEvent)e;
            cartProjection.onProductAdded(ev.getCartId(), ev.getProductId(), ev.getQuantity());
        } else if (e instanceof ProductRemovedFromCartEvent) {
            ProductRemovedFromCartEvent ev = (ProductRemovedFromCartEvent)e;
            cartProjection.onProductRemoved(ev.getCartId(), ev.getProductId());
        }
    }
}
