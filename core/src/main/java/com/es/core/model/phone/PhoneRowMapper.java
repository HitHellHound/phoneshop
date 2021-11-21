package com.es.core.model.phone;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

@Component
public class PhoneRowMapper implements RowMapper<Phone> {
    @Override
    public Phone mapRow(ResultSet resultSet, int i) throws SQLException {
        Phone phone = new Phone();
        phone.setId(resultSet.getLong("ID"));
        phone.setBrand(resultSet.getString("BRAND"));
        phone.setModel(resultSet.getString("MODEL"));
        phone.setPrice(resultSet.getBigDecimal("PRICE"));
        phone.setDisplaySizeInches(resultSet.getBigDecimal("DISPLAYSIZEINCHES"));
        phone.setWeightGr(resultSet.getInt("WEIGHTGR"));
        phone.setLengthMm(resultSet.getBigDecimal("LENGTHMM"));
        phone.setWidthMm(resultSet.getBigDecimal("WIDTHMM"));
        phone.setHeightMm(resultSet.getBigDecimal("HEIGHTMM"));
        phone.setAnnounced(resultSet.getDate("ANNOUNCED"));
        phone.setDeviceType(resultSet.getString("DEVICETYPE"));
        phone.setOs(resultSet.getString("OS"));
        phone.setDisplayResolution(resultSet.getString("DISPLAYRESOLUTION"));
        phone.setPixelDensity(resultSet.getInt("PIXELDENSITY"));
        phone.setDisplayTechnology(resultSet.getString("DISPLAYTECHNOLOGY"));
        phone.setBackCameraMegapixels(resultSet.getBigDecimal("BACKCAMERAMEGAPIXELS"));
        phone.setFrontCameraMegapixels(resultSet.getBigDecimal("FRONTCAMERAMEGAPIXELS"));
        phone.setRamGb(resultSet.getBigDecimal("RAMGB"));
        phone.setInternalStorageGb(resultSet.getBigDecimal("INTERNALSTORAGEGB"));
        phone.setBatteryCapacityMah(resultSet.getInt("BATTERYCAPACITYMAH"));
        phone.setTalkTimeHours(resultSet.getBigDecimal("TALKTIMEHOURS"));
        phone.setStandByTimeHours(resultSet.getBigDecimal("STANDBYTIMEHOURS"));
        phone.setBluetooth(resultSet.getString("BLUETOOTH"));
        phone.setPositioning(resultSet.getString("POSITIONING"));
        phone.setImageUrl(resultSet.getString("IMAGEURL"));
        phone.setDescription(resultSet.getString("DESCRIPTION"));

        Color color = new Color();
        color.setCode(resultSet.getString("COLOR"));
        HashSet<Color> colors = new HashSet<>();
        colors.add(color);
        phone.setColors(colors);

        return phone;
    }
}
