package ru.shapov.SpringRestApi.model;

import jakarta.persistence.*;

/**
 * Модель сущность страницы
 * <p>
 * Модель сущность страницы необходима для работы с базой данных. Данные полученные из бд, переводяться в модель сущность, и также
 * для записи в бд, данные приходят в виде модели сущности.
 *
 * @author ShapovAA
 */
@Entity
public class Page {
    /**
     * Поле уникального идентификатора страницы
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Поле идентификатор альбома
     */
    private int albumId;

    /**
     * Поле id предыдущей страницы
     */
    private int previousPageId;

    /**
     * Поле id следующей страницы
     */
    private int nextPageId;

    /**
     * Поле заголовок
     */
    private String title;

    /**
     * Геттер для получения уникального идентификатора страницы
     * @return уникальный идентификатор страницы
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для утстановки уникального идентификатора страницы
     * @param id уникальный идентификатор страницы
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для получения уникального идентификатора альбома
     * @return уникальный идентификатор альбома
     */
    public int getAlbumId() {
        return albumId;
    }

    /**
     * Сеттер для установки уникального идентификатора альбома
     * @param albumId уникальный идентификатор альбома
     */
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    /**
     * Геттер для получения уникального идентификатора предыдущей страницы
     * @return уникальный идентификатор предыдущей страницы
     */
    public int getPreviousPageId() {
        return previousPageId;
    }

    /**
     * Сеттер для установки уникального идентификатора предыдущей страницы
     * @param previousPageId уникальный идентификатор предыдущей старницы
     */
    public void setPreviousPageId(int previousPageId) {
        this.previousPageId = previousPageId;
    }

    /**
     * Геттер для получения уникального идентификатора следующей страницы
     * @return уникальный идентификатор следующей страницы
     */
    public int getNextPageId() {
        return nextPageId;
    }

    /**
     * Сеттер для установки уникального идентификатора следующей страницы
     * @param nextPageId уникальный идентификатор следующей страницы
     */
    public void setNextPageId(int nextPageId) {
        this.nextPageId = nextPageId;
    }

    /**
     * Геттер для получения заголовка страницы
     * @return заголовок страницы
     */
    public String getTitle() {
        return title;
    }

    /**
     * Сеттер для установки заголовка страницы
     * @param title заголовок страницы
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
