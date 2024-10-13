package com.blacksw.spring;

import java.util.Objects;

public class Passenger {
    private String name;
    private Country country;

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("Passenger{name='%s', country='%s'}", name, country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Passenger passenger = (Passenger) o;
        if (passenger.getName() != null && passenger.getName().equals(name) &&
            passenger.getCountry() != null && passenger.getCountry().equals(country)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
