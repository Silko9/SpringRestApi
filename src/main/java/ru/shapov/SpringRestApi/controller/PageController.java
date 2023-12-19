package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Page;
import ru.shapov.SpringRestApi.repository.PageRepository;

import java.util.List;

@RestController
@RequestMapping("/pages")
public class PageController {
    private final PageRepository repository;

    @Autowired
    public PageController(PageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Page>> getAllPages() {
        List<Page> pages = (List<Page>) repository.findAll();
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("/get-by-album-id/{id}")
    public ResponseEntity<List<Page>> getPagesByAlbumId(@PathVariable Long id) {
        List<Page> pages = (List<Page>) repository.findByAlbumId(id);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Long id) {
        return repository.findById(id)
                .map(page -> new ResponseEntity<>(page, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Page> createPage(@RequestBody Page page) {
        Page createPage = repository.save(page);
        return new ResponseEntity<>(createPage, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Page> updatePage(@PathVariable Long id, @RequestBody Page updatePage) {
        return repository.findById(id)
                .map(page -> {
                    page.setNextPageId(updatePage.getNextPageId());
                    page.setPreviousPageId(updatePage.getPreviousPageId());
                    page.setAlbumId(updatePage.getAlbumId());
                    page.setTitle(updatePage.getTitle());
                    Page savedPage = repository.save(page);
                    return new ResponseEntity<>(savedPage, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        return repository.findById(id)
                .map(page -> {
                    repository.delete(page);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}