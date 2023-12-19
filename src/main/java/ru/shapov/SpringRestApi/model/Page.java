package ru.shapov.SpringRestApi.model;

import jakarta.persistence.*;

@Entity
public class Page {
    /** Поле идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Поле идентификатор альбома */
    private int albumId;

    /** Поле id предыдущей страницы */
    private int previousPageId;

    /** Поле id следующей страницы */
    private int nextPageId;

    /** Поле заголовок */
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getPreviousPageId() {
        return previousPageId;
    }

    public void setPreviousPageId(int previousPageId) {
        this.previousPageId = previousPageId;
    }

    public int getNextPageId() {
        return nextPageId;
    }

    public void setNextPageId(int nextPageId) {
        this.nextPageId = nextPageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
