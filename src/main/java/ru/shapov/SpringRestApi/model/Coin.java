package ru.shapov.SpringRestApi.model;

import jakarta.persistence.*;

/**
 * Модель сущность монеты
 * <p>
 * Модель сущность монеты необходима для работы с базой данных. Данные полученные из бд, переводяться в модель сущность, и также
 * для записи в бд, данные приходят в виде модели сущности.
 *
 * @author ShapovAA
 */
@Entity
public class Coin {
    /**
     * Поле уникального идентификатора монеты
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Поле номинала монеты
     */
    private int denomination;

    /**
     * Поле уникального идентификатора валюты монеты
     */
    private int currencyId;

    /**
     * Поле уникального идентификатора страны монеты
     */
    private int countryId;

    /**
     * Поле уникального идентификатора монетного двора монеты
     */
    private int mintId;

    /**
     * Поле года выпуска монеты
     */
    private int yearMinting;

    /**
     * Поле пути изображения монеты
     */
    private String picturePath;

    /**
     * Геттер для получения уникального идентификатора монеты
     * @return уникальный идентификатор монеты
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для утстановки уникального идентификатора монеты
     * @param id уникальный идентификатор монеты
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для получения номинала монеты
     * @return номинал монеты
     */
    public int getDenomination() {
        return denomination;
    }

    /**
     * Сеттер для установки номинала монеты
     * @param denomination номинал монеты
     */
    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    /**
     * Геттер для получения уникального идентификатора валюты монеты
     * @return уникальный идентификатор валюты монеты
     */
    public int getCurrencyId() {
        return currencyId;
    }

    /**
     * Сеттер для установки уникального идентификатора валюты монеты
     * @param currencyId уникальный идентификатор валюты монеты
     */
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * Геттер для получения уникального идентификатора страны монеты
     * @return уникальный идентификатор страны монеты
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Сеттер для установки уникального идентификатора старны монеты
     * @param countryId уникальный идентификатор страны монеты
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Геттер для получения уникального идентификатора монетного двора монеты
     * @return уникальный идентификатор монетного двора монеты
     */
    public int getMintId() {
        return mintId;
    }

    /**
     * Сеттер для установки уникального идентификатора монетного двора монеты
     * @param mintId уникальный идентификатор монетного двора монеты
     */
    public void setMintId(int mintId) {
        this.mintId = mintId;
    }

    /**
     * Геттер для получения года выпуска монеты
     * @return год выпуска монеты
     */
    public int getYearMinting() {
        return yearMinting;
    }

    /**
     * Сеттер для установки года выпуска монеты
     * @param yearMinting год выпуска монеты
     */
    public void setYearMinting(int yearMinting) {
        this.yearMinting = yearMinting;
    }

    /**
     * Геттер для получения пути изображения монеты
     * @return путь изображения монеты
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * Сеттер для установки пути изображения монеты
     * @param picturePath путь изображения монеты
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
