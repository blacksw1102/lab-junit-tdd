package com.blacksw.ch17springbootbeans.beans;

import com.blacksw.ch17springbootbeans.model.Country;
import com.blacksw.ch17springbootbeans.model.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestBeans {

    @Bean
    Passenger createPassenger() {
        Passenger passenger = new Passenger("John Smith");
        passenger.setCountry(createCountry());
        passenger.setIsRegistered(false);
        return passenger;
    }

    @Bean
    Country createCountry() {
        Country country = new Country("USA", "US");
        return country;
    }
}
