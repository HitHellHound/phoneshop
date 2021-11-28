package com.es.core.model.phone.stock;

import com.es.core.model.phone.PhoneDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StockRowMapper implements RowMapper<Stock> {
    @Resource
    private PhoneDao phoneDao;

    @Override
    public Stock mapRow(ResultSet resultSet, int i) throws SQLException {
        Stock stock = new Stock();
        stock.setPhone(phoneDao.get(resultSet.getLong("PHONEID")).orElse(null));
        stock.setStock(resultSet.getInt("STOCK"));
        stock.setReserved(resultSet.getInt("RESERVED"));
        return stock;
    }
}
