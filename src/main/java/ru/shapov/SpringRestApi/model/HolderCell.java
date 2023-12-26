package ru.shapov.SpringRestApi.model;

import jakarta.persistence.*;

/**
 * Модель сущность холдера
 * <p>
 * Модель сущность холдера необходима для работы с базой данных. Данные полученные из бд, переводяться в модель сущность, и также
 * для записи в бд, данные приходят в виде модели сущности.
 *
 * @author ShapovAA
 */
@Entity
public class HolderCell {
    /**
     * Поле уникального идентификатора холдера
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Поле идентификатор монеты
     */
    private int coinId;

    /**
     * Поле наличия монеты в холдере
     */
    private boolean available;

    /**
     * Поле идентификатор страницы
     */
    private int pageId;

    /**
     * Поле номера столбца
     */
    private int columnHolder;

    /**
     * Поле номер строки
     */
    private int lineHolder;

    /**
     * Поле заголовки страницы
     */
    private String title;

    /**
     * Геттер для получения уникального идентификатора холдера
     * @return уникальный идентификатор холдера
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для утстановки уникального идентификатора холдера
     * @param id уникальный идентификатор холдера
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для получения уникального идентификатора монеты в холдере
     * @return уникальный идентификатор монеты
     */
    public int getCoinId() {
        return coinId;
    }

    /**
     * Сеттер для установки уникального идентификатора монеты в холдере
     * @param coinId уникальный идентификатор монеты
     */
    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    /**
     * Проверяет наличие монеты в холдере
     * @return наличие монеты в холдере
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Сеттер для установки наличие монеты в холдере
     * @param available наличие монеты в холдоре
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Геттер для получения уникального идентификатора страницы у холдера
     * @return уникальный идентификатор страницы
     */
    public int getPageId() {
        return pageId;
    }

    /**
     * Сеттер для установки уникального идентификатора страницы у холдера
     * @param pageId уникальный идентификатор страницы
     */
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    /**
     * Геттер для получения номера колонки холдера
     * @return номер колонки холдера
     */
    public int getColumnHolder() {
        return columnHolder;
    }

    /**
     * Сеттер для установки номера колонки холдера
     * @param columnHolder номер колонки холдера
     */
    public void setColumnHolder(int columnHolder) {
        this.columnHolder = columnHolder;
    }

    /**
     * Геттер для получения номера строки холдера
     * @return номер строки холдера
     */
    public int getLineHolder() {
        return lineHolder;
    }

    /**
     * Сеттер для установки номера строки холдера
     * @param lineHolder номер строки холдера
     */
    public void setLineHolder(int lineHolder) {
        this.lineHolder = lineHolder;
    }

    /**
     * Геттер для получения заголовка холдера
     * @return заголовок холдера
     */
    public String getTitle() {
        return title;
    }

    /**
     * Сеттер для установки заголовка холдера
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
