package com.es.core.model.phone;

import com.es.core.model.phone.color.Color;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/context/testApplicationContext.xml")
public class JdbcPhoneDaoTest {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private PhoneDao phoneDao;

    @Test
    public void testSavePhoneInDB() {
        Phone testPhone = new Phone();
        testPhone.setId(1L);
        testPhone.setBrand("testBrand1");
        testPhone.setModel("testModel1");
        Color color = new Color();
        color.setCode("Black");
        HashSet<Color> testColors = new HashSet<>();
        testColors.add(color);
        testPhone.setColors(testColors);
        phoneDao.save(testPhone);

        Phone phone = jdbcTemplate.query("select ph.*, colors.code as color " +
                        "from (select phones.* from phones where phones.id = 1) ph " +
                        "left join phone2color on ph.id = phone2color.phoneId " +
                        "left join colors on phone2color.colorId = colors.id",
                new PhoneRowMapper()).stream().findAny().orElse(null);
        assertNotNull(phone);

        List<Color> colors = phone.getColors().stream().collect(Collectors.toList());
        assertTrue(colors.get(0).getCode().equals("Black"));
    }

    @Test
    public void testGet() {
        Phone testPhone = new Phone();
        testPhone.setId(2L);
        testPhone.setBrand("testBrand2");
        testPhone.setModel("testModel2");
        Color color = new Color();
        color.setCode("White");
        HashSet<Color> testColors = new HashSet<>();
        testColors.add(color);
        testPhone.setColors(testColors);
        phoneDao.save(testPhone);

        Phone phone = phoneDao.get(2L).orElse(null);
        assertNotNull(phone);
        List<Color> colors = phone.getColors().stream().collect(Collectors.toList());
        assertTrue(colors.get(0).getCode().equals("White"));
    }
}