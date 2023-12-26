package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Page;
import ru.shapov.SpringRestApi.repository.PageRepository;

import java.util.List;

/**
 * Контроллер страниц
 * <p>
 * Контроллер содержит в себе энд-поинты для взаимодйствия с страницами через API сервера.
 * Корневой маршрут для всех энд-поинтов /pages
 *
 * @author ShapovAA
 */
@RestController
@RequestMapping("/pages")
public class PageController {
    /**
     * Репозиторий страниц
     */
    private final PageRepository repository;

    /**
     * Конструктор для контроллера. Вызывается посредством spring, подставляя все необходимые зависимости через @Autowired
     * @param repository репозиторий страниц
     */
    @Autowired
    public PageController(PageRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод получение всех страницы через энд поинт /get-all
     * @return лист страниц
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Page>> getAllPages() {
        List<Page> pages = (List<Page>) repository.findAll();
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    /**
     * Метод получение всех страниц по уникальному идентификатору альбома через энд-поинт /get-by-album-id/{id}
     * @param id уникальный идентификатор альбома
     * @return лист страниц
     */
    @GetMapping("/get-by-album-id/{id}")
    public ResponseEntity<List<Page>> getPagesByAlbumId(@PathVariable Long id) {
        List<Page> pages = (List<Page>) repository.findByAlbumId(id);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    /**
     * Метод получения страницы по уникальному идентификатору через энд-поинт /get/{id}
     * @param id уникальный идентификатор страницы
     * @return сущность страницы
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Long id) {
        return repository.findById(id)
                .map(page -> new ResponseEntity<>(page, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод добавление страницы в БД, через энд-поинт /add
     * @param page сущность страницы, должена содержаться в теле запроса
     * @return сущность созданной страницы
     */
    @PostMapping("/add")
    public ResponseEntity<Page> createPage(@RequestBody Page page) {
        Page createPage = repository.save(page);
        return new ResponseEntity<>(createPage, HttpStatus.CREATED);
    }


    /**
     * Метод изменение сущности страницы в БД, через энд-поинт /edit{id}
     * @param id уникальный идентификатор страницы
     * @param updatePage сущность изменненой страницы
     * @return сущность страницы после изменений
     */
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

    /**
     * Метод удаление сущности страницы из БД, через энд-поинт /delete/{id}
     * @param id уникальный идентификатор страницы
     * @return сущность со статусом запроса
     */
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