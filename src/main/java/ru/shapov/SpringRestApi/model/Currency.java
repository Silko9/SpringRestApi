package ru.shapov.SpringRestApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Модель сущность валюты
 * <p>
 * Модель сущность валюты необходима для работы с базой данных. Данные полученные из бд, переводяться в модель сущность, и также
 * для записи в бд, данные приходят в виде модели сущности.
 *
 * @author ShapovAA
 */
@Entity
public class Currency {
    /**
     * Поле уникального идентификатора валюты
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Поле название валюты
     */
    private String name;

    /**
     * Геттер для получения уникального идентификатора валюты
     * @return уникальный идентификатор валюты
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для утстановки уникального идентификатора валюты
     * @param id уникальный идентификатор валюты
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для получения названия валюты
     * @return название валюты
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для установки названия валюты
     * @param name название валюты
     */
    public void setName(String name) {
        this.name = name;
    }
}
