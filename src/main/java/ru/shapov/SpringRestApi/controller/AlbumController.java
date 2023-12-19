package ru.shapov.SpringRestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Album;
import ru.shapov.SpringRestApi.repository.AlbumRepository;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumRepository repository;

    @Autowired
    public AlbumController(AlbumRepository albumRepository) {
        this.repository = albumRepository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = (List<Album>) repository.findAll();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return repository.findById(id)
                .map(album -> new ResponseEntity<>(album, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album createdAlbum = repository.save(album);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

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
