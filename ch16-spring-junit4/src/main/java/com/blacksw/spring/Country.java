package com.blacksw.spring;

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
        return String.format("Country{name='%s', codeName='%s'}", name, codeName);
    }

    @Override
    public boolean equals(Object o) {
        // 두 객체간 메모리 주소가 같으면 같은 객체로 인정
        if (this == o) {
            return true;
        }
        // 두 객체의 codeNamr과 name 필드의 값이 일치하면 같은 객체로 인정.
        Country country = (Country) o;
        if (codeName != null && codeName.equals(country.codeName) &&
                name != null && name.equals(country.name)) {
            return true;
        }
        // 그 외 싹다 같지 않은 객체로 판단.
        return false;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (codeName != null ? codeName.hashCode() : 0);
        return result;
    }
}
