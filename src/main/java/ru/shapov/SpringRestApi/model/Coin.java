package ru.shapov.SpringRestApi.model;

import jakarta.persistence.*;

@Entity
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int denomination;

    private int currencyId;

    private int countryId;

    private int mintId;

    private int yearMinting;

    private String picturePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getMintId() {
        return mintId;
    }

    public void setMintId(int mintId) {
        this.mintId = mintId;
    }

    public int getYearMinting() {
        return yearMinting;
    }

    public void setYearMinting(int yearMinting) {
        this.yearMinting = yearMinting;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
