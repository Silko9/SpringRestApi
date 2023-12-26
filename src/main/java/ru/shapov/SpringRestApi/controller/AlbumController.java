package ru.shapov.SpringRestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Album;
import ru.shapov.SpringRestApi.repository.AlbumRepository;

import java.util.List;

/**
 * Контроллер альбомов
 * <p>
 * Контроллер содержит в себе энд-поинты для взаимодйствия с альбомами через API сервера.
 * Корневой маршрут для всех энд-поинтов /albums
 *
 * @author ShapovAA
 */
@RestController
@RequestMapping("/albums")
public class AlbumController {
    /**
     * Репозиторий альбомов
     */
    private final AlbumRepository repository;

    /**
     * Конструктор для контроллера. Вызывается посредством spring, подставляя все необходимые зависимости через @Autowired
     * @param albumRepository репозиторий альбома
     */
    @Autowired
    public AlbumController(AlbumRepository albumRepository) {
        this.repository = albumRepository;
    }

    /**
     * Метод получение всех альбомов через энд поинт /get-all
     * @return лист альбомов
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = (List<Album>) repository.findAll();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    /**
     * Метод получения альбома по уникальному идентификатору через энд-поинт /get/{id}
     * @param id уникальный идентификатор альбома
     * @return сущность альбома
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return repository.findById(id)
                .map(album -> new ResponseEntity<>(album, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод добавление альбома в БД, через энд-поинт /add
     * @param album сущность альбома, должен содержаться в теле запроса
     * @return сущность созданного альбома
     */
    @PostMapping("/add")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album createdAlbum = repository.save(album);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

    /**
     * Метод изменение сущности альбома в БД, через энд-поинт /edit{id}
     * @param id уникальный идентификатор альбома
     * @param updatedAlbum сущность изменненого альбома
     * @return сущность альбома после изменений
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album updatedAlbum) {
        return repository.findById(id)
                .map(album -> {
                    album.setTitle(updatedAlbum.getTitle());
                    Album savedAlbum = repository.save(album);
                    return new ResponseEntity<>(savedAlbum, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод удаление сущности альбома из БД, через энд-поинт /delete/{id}
     * @param id уникальный идентификатор альбома
     * @return сущность со статусом запроса
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        return repository.findById(id)
                .map(album -> {
                    repository.delete(album);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
