package com.es.core.order;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {

    }

    public OutOfStockException(String message) {
        super(message);
    }
}
