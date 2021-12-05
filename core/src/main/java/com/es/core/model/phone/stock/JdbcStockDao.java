package com.es.core.model.phone.stock;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JdbcStockDao implements StockDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private StockRowMapper stockRowMapper;

    private static final String GET_STOCK_BY_ID = "SELECT * FROM stocks WHERE phoneId = ?";

    @Override
    public Integer availableStock(Long phoneId) {
        Stock stock = jdbcTemplate.queryForObject(GET_STOCK_BY_ID, stockRowMapper, phoneId);
        return stock.getStock() - stock.getReserved();
    }
}
