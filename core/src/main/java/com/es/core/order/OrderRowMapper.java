package com.es.core.order;

import com.es.core.model.order.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderRowMapper implements RowMapper<Order> {
    @Resource
    private OrderItemDao jdbcOrderItemDao;

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setSecureID(resultSet.getString("secureID"));
        order.setSubtotal(resultSet.getBigDecimal("subtotal"));
        order.setDeliveryPrice(resultSet.getBigDecimal("deliveryPrice"));
        order.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
        order.setFirstName(resultSet.getString("firstName"));
        order.setLastName(resultSet.getString("lastName"));
        order.setDeliveryAddress(resultSet.getString("deliveryAddress"));
        order.setContactPhoneNo(resultSet.getString("contactPhoneNo"));
        order.setAdditionalInformation(resultSet.getString("additionalInformation"));
        order.setOrderItems(jdbcOrderItemDao.getOrderItems(order.getId()));
        return order;
    }
}
