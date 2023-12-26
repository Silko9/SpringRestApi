package ru.shapov.SpringRestApi.model;

import jakarta.persistence.*;

/**
 * Модель сущность монетного двора
 * <p>
 * Модель сущность монетного двора необходима для работы с базой данных. Данные полученные из бд, переводяться в модель сущность, и также
 * для записи в бд, данные приходят в виде модели сущности.
 *
 * @author ShapovAA
 */
@Entity
public class Mint {
    /**
     * Поле уникального идентификатора монетного двора
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Поле название монетного двора
     */
    private String name;

    /** Поле идентификатор страны */
    private int countryId;

    /**
     * Геттер для получения уникального идентификатора монетного двора
     * @return уникальный идентификатор монетного двора
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для утстановки уникального идентификатора монетного двора
     * @param id уникальный идентификатор монетного двора
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для получения названия монетного двора
     * @return название монетного двора
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для установки названия монетного двора
     * @param name название монетного двора
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер для получения уникального идентификатора страны
     * @return уникальный идентификатор страны
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Сеттер для установки уникального идентификатора страны
     * @param countryId уникальный идентификатор страны
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
