package com.blacksw.ch18rest;

import com.blacksw.ch18rest.beans.FlightBuilder;
import com.blacksw.ch18rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
@Import(FlightBuilder.class)
public class Ch18RestApplication {

    @Autowired
    private Flight flight;

    @Autowired
    private Map<String, Country> countriesMap;

    public static void main(String[] args) {
        SpringApplication.run(Ch18RestApplication.class, args);
    }

    @Bean
    CommandLineRunner configureRepository(CountryRepository countryRepository, PassengerRepository passengerRepository) {
        return args -> {
            for (Country country : countriesMap.values()) {
                countryRepository.save(country);
            }

            for (Passenger passenger : flight.getPassengers()) {
                passengerRepository.save(passenger);
            }
        };
    }

}
