package com.blacksw;

import com.blacksw.Flight;
import com.blacksw.Passenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlightBuilderUtil {

    public static Flight buildFlightFromCsv() throws IOException {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/flights_information.csv"))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] passengerString = line.split(";");
                    Passenger passenger = new Passenger(passengerString[0].trim(), passengerString[1].trim(), passengerString[2].trim());
                    flight.addPassenger(passenger);
                }
            } while (line != null);
        }

        return flight;
    }
}