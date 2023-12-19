package ru.shapov.SpringRestApi.model;

import jakarta.persistence.*;

@Entity
public class Mint {
    /** Поле идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Поле название */
    private String name;
    /** Поле идентификатор страны */
    private int countryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
