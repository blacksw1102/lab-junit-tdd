package com.blacksw.producers;

import com.blacksw.Flight;
import com.blacksw.FlightBuilderUtil;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class FlightProducer {
    @Produces
    public Flight createFlight() throws IOException {
        return FlightBuilderUtil.buildFlightFromCsv();
    }
}
