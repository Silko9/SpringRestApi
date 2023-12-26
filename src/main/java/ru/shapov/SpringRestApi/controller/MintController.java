package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Mint;
import ru.shapov.SpringRestApi.repository.MintRepository;

import java.util.List;

/**
 * Контроллер монетных дворов
 * <p>
 * Контроллер содержит в себе энд-поинты для взаимодйствия с монетными дворами через API сервера.
 * Корневой маршрут для всех энд-поинтов /mints
 *
 * @author ShapovAA
 */
@RestController
@RequestMapping("/mints")
public class MintController {
    /**
     * Репозиторий монетных дворов
     */
    private final MintRepository repository;

    /**
     * Конструктор для контроллера. Вызывается посредством spring, подставляя все необходимые зависимости через @Autowired
     * @param repository репозиторий монетных дворов
     */
    @Autowired
    public MintController(MintRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод получение всех монетных дворов через энд поинт /get-all
     * @return лист монетных дворов
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Mint>> getAllMints() {
        List<Mint> mints = (List<Mint>) repository.findAll();
        return new ResponseEntity<>(mints, HttpStatus.OK);
    }

    /**
     * Метод получения монетного двора по уникальному идентификатору через энд-поинт /get/{id}
     * @param id уникальный идентификатор монетного двора
     * @return сущность монетного двора
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Mint> getMintById(@PathVariable Long id) {
        return repository.findById(id)
                .map(mint -> new ResponseEntity<>(mint, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод добавление монетного двора в БД, через энд-поинт /add
     * @param mint сущность монетного двора, должен содержаться в теле запроса
     * @return сущность созданного монетного двора
     */
    @PostMapping("/add")
    public ResponseEntity<Mint> createMint(@RequestBody Mint mint) {
        Mint createMint = repository.save(mint);
        return new ResponseEntity<>(createMint, HttpStatus.CREATED);
    }

    /**
     * Метод изменение сущности монетного двора в БД, через энд-поинт /edit{id}
     * @param id уникальный идентификатор монетного двора
     * @param updateMint сущность изменненого монетного двора
     * @return сущность монетного двора после изменений
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Mint> updateMint(@PathVariable Long id, @RequestBody Mint updateMint) {
        return repository.findById(id)
                .map(mint -> {
                    mint.setName(updateMint.getName());
                    mint.setCountryId(0);
                    Mint savedMint = repository.save(mint);
                    return new ResponseEntity<>(savedMint, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод удаление сущности монетного двора из БД, через энд-поинт /delete/{id}
     * @param id уникальный идентификатор монетного двора
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
