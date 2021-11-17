package com.es.core.model.phone;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcPhoneDao implements PhoneDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private PhoneRowMapper phoneRowMapper;

    private static final String GET_QUERY = "SELECT ph.*, colors.code AS color " +
            "FROM (SELECT phones.* FROM phones WHERE phones.id = ?) AS ph " +
            "LEFT JOIN phone2color ON ph.id = phone2color.phoneId " +
            "LEFT JOIN colors ON phone2color.colorId = colors.id";
    private static final String SAVE_INSERT_QUERY = "INSERT INTO phones (Id, Brand, Model, Price, DisplaySizeInches, " +
            "WeightGr, LengthMm, WidthMm, HeightMm, Announced, DeviceType, Os, DisplayResolution, PixelDensity, " +
            "DisplayTechnology, BackCameraMegapixels, FrontCameraMegapixels, RamGb, InternalStorageGb, BatteryCapacityMah, " +
            "TalkTimeHours, StandByTimeHours, Bluetooth, Positioning, ImageUrl, Description) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "select ph.*, colors.code as color " +
            "from (select phones.* from phones offset ? limit ?) ph " +
            "left join phone2color on ph.id = phone2color.phoneId " +
            "left join colors on phone2color.colorId = colors.id";
    private static final String SELECT_COLOR_BY_CODE_QUERY = "SELECT colors.id FROM colors WHERE colors.code = ?";
    private static final String INSERT_NEW_COLOR_DEPENDENCE_QUERY = "INSERT INTO phone2color (phoneId, colorId) VALUES (?, ?)";

    public Optional<Phone> get(final Long key) {
        return jdbcTemplate.query(GET_QUERY, phoneRowMapper, key).stream().findAny();
    }

    public void save(final Phone phone) {
        jdbcTemplate.update(SAVE_INSERT_QUERY,
                phone.getId(), phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(),
                phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(),
                phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(),
                phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(),
                phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(),
                phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(),
                phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription());

        phone.getColors().stream()
                .forEach(color -> {
                    Long colorId = jdbcTemplate.queryForObject(SELECT_COLOR_BY_CODE_QUERY, Long.class, color.getCode());
                    jdbcTemplate.update(INSERT_NEW_COLOR_DEPENDENCE_QUERY, phone.getId(), colorId);
                });
    }

    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query(FIND_ALL_QUERY, phoneRowMapper, offset, limit);
    }
}
