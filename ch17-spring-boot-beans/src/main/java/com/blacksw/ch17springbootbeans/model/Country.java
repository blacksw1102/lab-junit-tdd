package com.blacksw.ch17springbootbeans.model;

import java.util.Objects;

public class Country {
    private String name;
    private String codeName;

    public Country(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Override
    public String toString() {
        return String.format("Country{ name='%s', codeName='%s' }", this.name, this.codeName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Country country = (Country) obj;
        return Objects.equals(this.name, country.getName()) && Objects.equals(this.codeName, country.getCodeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.codeName);
    }
}
