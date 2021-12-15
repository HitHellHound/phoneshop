package com.es.core.model.order;

import java.util.Arrays;

public enum OrderStatus {
    NEW, DELIVERED, REJECTED;

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "NEW";
            case DELIVERED:
                return "DELIVERED";
            case REJECTED:
                return "REJECTED";
            default:
                return "UNKNOWN";
        }
    }

    public static OrderStatus getValue(String name) {
        return Arrays.stream(OrderStatus.values())
                .filter(value -> value.name().toLowerCase().equals(name.toLowerCase()))
                .findAny()
                .orElse(null);
    }
}
