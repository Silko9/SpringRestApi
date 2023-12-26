package ru.shapov.SpringRestApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Модель сущность альбома
 * <p>
 * Модель сущность альбома необходима для работы с базой данных. Данные полученные из бд, переводяться в модель сущность, и также
 * для записи в бд, данные приходят в виде модели сущности.
 *
 * @author ShapovAA
 */
@Entity
public class Album {
    /**
     * Поле уникального идентификатора альбома
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле заголовка альбома
     */
    private String title;

    /**
     * Геттер для получения уникального идентификатора альбома
     * @return уникальный идентификатор альбома
     */
    public Long getId() {
        return id;
    }

    /**
     * Сеттер для утстановки уникального идентификатора альбома
     * @param id уникальный идентификатор альбома
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Геттер для получения заголовка альбома
     * @return заголовок альбома
     */
    public String getTitle() {
        return title;
    }

    /**
     * Сеттер для установки заголовка альбома
     * @param title загаловок альбома
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
