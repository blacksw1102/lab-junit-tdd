package com.blacksw.ch17springbootnewfeature.beans;

import com.blacksw.ch17springbootnewfeature.model.Country;
import com.blacksw.ch17springbootnewfeature.model.Flight;
import com.blacksw.ch17springbootnewfeature.model.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@TestConfiguration
public class FlightBuilder {

    private static final Map<String, Country> countriesMap = new HashMap<>();

    // static initializer block (정적 초기화 블록)
    static {
        countriesMap.put("AU", new Country("Australia", "AU"));
        countriesMap.put("US", new Country("USA", "US"));
        countriesMap.put("UK", new Country("United Kingdom", "UK"));
    }

    // .csv 파일에서 승객 정보를 한라인 씩 읽어서 항공편에 귀속 시킨다.
    @Bean
    Flight buildFlightFromCsv() throws IOException {
        Flight flight = new Flight("AA1234", 20);

        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/flights_information.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] passengerString = line.split(";");
                String name = passengerString[0].trim();
                String countryCode = passengerString[1].trim();

                Passenger passenger = new Passenger(name);
                passenger.setCountry(countriesMap.get(countryCode));
                passenger.setIsRegistered(false);
                flight.addPassenger(passenger);
            }
        }

        return flight;
    }

}
