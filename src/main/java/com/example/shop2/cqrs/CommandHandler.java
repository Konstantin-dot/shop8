package com.example.shop2.cqrs;
public interface CommandHandler<C extends Command> {
    void handle(C command) throws Exception;
}
