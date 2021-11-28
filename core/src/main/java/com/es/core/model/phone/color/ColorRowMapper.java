package com.es.core.model.phone.color;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ColorRowMapper implements RowMapper<Color> {
    @Override
    public Color mapRow(ResultSet resultSet, int i) throws SQLException {
        Color color = new Color();
        color.setId(resultSet.getLong("ID"));
        color.setCode(resultSet.getString("CODE"));
        return color;
    }
}
