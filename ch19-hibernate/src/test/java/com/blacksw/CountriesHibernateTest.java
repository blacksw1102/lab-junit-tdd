package com.blacksw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountriesHibernateTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    public static final String[][] COUNTRY_INIT_DATA = {
            {"Australia", "AU"}, {"Canada", "CA"}, {"France", "FR"},
            {"Germany", "DE"}, {"Italy", "IT"}, {"Japan", "JP"},
            {"Romania", "RO"}, {"Russian Federation", "RU"}, {"Spain", "ES"},
            {"Switzerland", "CH"}, {"United Kingdom", "UK"}, {"United States", "US"}
    };

    @BeforeEach
    public void setUp() {
        // expected 데이터 초기화
        initExpectedCountryLists();

        emf = Persistence.createEntityManagerFactory("blacksw.hibernate");
        em = emf.createEntityManager();

        // 트랜잭션 시작
        em.getTransaction().begin();

        // Country 레코드 저장
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            em.persist(country);
        }

        // 트랜잭션 커밋과 함께 종료
        em.getTransaction().commit();
    }

    @Test
    public void testCountryList() {
        List<Country> countryList = em.createQuery("select c from Country c").getResultList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    public void testCountryListStartsWithA() {
        List<Country> countryList = em.createQuery("select c from Country c where c.name like 'A%'").getResultList();
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }

    @AfterEach
    public void dropDown() {
        em.close();
        emf.close();
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }

}
