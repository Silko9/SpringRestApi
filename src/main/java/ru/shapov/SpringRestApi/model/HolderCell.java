package ru.shapov.SpringRestApi.model;

import jakarta.persistence.*;

@Entity
public class HolderCell {
    /** Поле идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    /** Поле идентификатор монеты */
    private int coinId;
    /** Поле монета */
    private boolean available;
    /** Поле идентификатор страницы */
    private int pageId;
    /** Поле столбец */
    private int columnHolder;
    /** Поле строка */
    private int lineHolder;
    /** Поле название */
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getColumnHolder() {
        return columnHolder;
    }

    public void setColumnHolder(int columnHolder) {
        this.columnHolder = columnHolder;
    }

    public int getLineHolder() {
        return lineHolder;
    }

    public void setLineHolder(int lineHolder) {
        this.lineHolder = lineHolder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
