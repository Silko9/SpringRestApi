package ru.shapov.SpringRestApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Модель сущность страны
 * <p>
 * Модель сущность страны необходима для работы с базой данных. Данные полученные из бд, переводяться в модель сущность, и также
 * для записи в бд, данные приходят в виде модели сущности.
 *
 * @author ShapovAA
 */
@Entity
public class Country {
    /**
     * Поле уникального идентификатора страны
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Поле название страны
     */
    private String name;

    /**
     * Геттер для получения уникального идентификатора страны
     * @return уникальный идентификатор страны
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для утстановки уникального идентификатора страны
     * @param id уникальный идентификатор страны
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для получения названия страны
     * @return название страны
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для установки названия страны
     * @param name название страны
     */
    public void setName(String name) {
        this.name = name;
    }
}
