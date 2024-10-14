package com.blacksw;

import com.blacksw.dao.CountryDao;
import com.blacksw.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountriesDatabaseTest {
    private CountryDao countryDao = new CountryDao();
    private CountriesLoader countriesLoader = new CountriesLoader();

    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        TableManager.createTable();
        initExpectedCountryLists();
        countriesLoader.loadCountries();
    }

    @Test
    public void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    public void testCountryListStartsWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }

    @AfterEach
    public void tearDown() {
        TableManager.dropTable();
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < CountriesLoader.COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = CountriesLoader.COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }

}
