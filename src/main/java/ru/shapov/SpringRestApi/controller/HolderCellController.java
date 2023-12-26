package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.HolderCell;
import ru.shapov.SpringRestApi.model.Page;
import ru.shapov.SpringRestApi.repository.HolderCellRepository;

import java.util.List;

/**
 * Контроллер холдеров
 * <p>
 * Контроллер содержит в себе энд-поинты для взаимодйствия с холдерами через API сервера.
 * Корневой маршрут для всех энд-поинтов /holderCells
 *
 * @author ShapovAA
 */
@RestController
@RequestMapping("/holderCells")
public class HolderCellController {
    /**
     * Репозиторий холдеров
     */
    private final HolderCellRepository repository;

    /**
     * Конструктор для контроллера. Вызывается посредством spring, подставляя все необходимые зависимости через @Autowired
     * @param repository репозиторий холдеров
     */
    @Autowired
    public HolderCellController(HolderCellRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод получение всех холдеров через энд поинт /get-all
     * @return лист холдеров
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<HolderCell>> getAllHolderCells() {
        List<HolderCell> holderCells = (List<HolderCell>) repository.findAll();
        return new ResponseEntity<>(holderCells, HttpStatus.OK);
    }

    /**
     * Метод получение всех холдеров по уникальному идентификатору страницы через энд-поинт /get-by-page-id/{id}
     * @param id уникальный идентификатор страницы
     * @return лист холдеров
     */
    @GetMapping("/get-by-page-id/{id}")
    public ResponseEntity<List<HolderCell>> getPagesByPageId(@PathVariable Long id) {
        List<HolderCell> holderCells = (List<HolderCell>) repository.findByPageId(id);
        return new ResponseEntity<>(holderCells, HttpStatus.OK);
    }

    /**
     * Метод получения холдера по уникальному идентификатору через энд-поинт /get/{id}
     * @param id уникальный идентификатор холдера
     * @return сущность холдера
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<HolderCell> getHolderCellById(@PathVariable Long id) {
        return repository.findById(id)
                .map(holderCell -> new ResponseEntity<>(holderCell, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод добавление холдера в БД, через энд-поинт /add
     * @param holderCell сущность холдера, должен содержаться в теле запроса
     * @return сущность созданного холдера
     */
    @PostMapping("/add")
    public ResponseEntity<HolderCell> createHolderCell(@RequestBody HolderCell holderCell) {
        HolderCell createHolderCell = repository.save(holderCell);
        return new ResponseEntity<>(createHolderCell, HttpStatus.CREATED);
    }

    /**
     * Метод изменение сущности холдера в БД, через энд-поинт /edit{id}
     * @param id уникальный идентификатор холдера
     * @param updateHolderCell сущность изменненого холдера
     * @return сущность холдера после изменений
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<HolderCell> updateHolderCell(@PathVariable Long id, @RequestBody HolderCell updateHolderCell) {
        return repository.findById(id)
                .map(holderCell -> {
                    holderCell.setAvailable(false);
                    holderCell.setColumnHolder(updateHolderCell.getColumnHolder());
                    holderCell.setLineHolder(updateHolderCell.getLineHolder());
                    holderCell.setCoinId(updateHolderCell.getCoinId());
                    holderCell.setPageId(updateHolderCell.getPageId());
                    holderCell.setTitle(updateHolderCell.getTitle());
                    HolderCell savedHolderCell = repository.save(holderCell);
                    return new ResponseEntity<>(savedHolderCell, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод удаление сущности холдера из БД, через энд-поинт /delete/{id}
     * @param id уникальный идентификатор холдера
     * @return сущность со статусом запроса
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHolderCell(@PathVariable Long id) {
        return repository.findById(id)
                .map(holderCell -> {
                    repository.delete(holderCell);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}