package com.example.shop2.cqrs.handlers;
import com.example.shop2.cqrs.CommandHandler;
import com.example.shop2.cqrs.commands.AddProductToCartCommand;
import com.example.shop2.domain.CartAggregate;
import com.example.shop2.es.InMemoryEventStore;
import com.example.shop2.domain.events.ProductAddedToCartEvent;

import java.util.List;

public class CartCommandHandler implements CommandHandler<AddProductToCartCommand> {
    private final InMemoryEventStore eventStore;
    public CartCommandHandler(InMemoryEventStore store) {
        this.eventStore = store;
    }

    @Override
    public void handle(AddProductToCartCommand cmd) {

        List<com.example.shop2.es.Event> history = eventStore.load(cmd.cartId);
        CartAggregate cart = CartAggregate.fromEvents(cmd.cartId, cmd.userId, history);

        ProductAddedToCartEvent ev = cart.addProduct(cmd.productId, cmd.qty);

        eventStore.append(cmd.cartId, ev);
    }
}
