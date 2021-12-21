package com.es.core.cart;

import com.es.core.order.OutOfStockException;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {

    Cart getCart();

    void addPhone(Long phoneId, Long quantity) throws OutOfStockException;

    void addPhoneByModel(String model, Long quantity) throws PhoneNotFoundException;

    /**
     * @param items key: {@link com.es.core.model.phone.Phone#id}
     *              value: quantity
     */
    void update(Map<Long, Long> items);

    void remove(Long phoneId);

    void clear();

    long getTotalQuantity();

    BigDecimal getTotalCost();
}
