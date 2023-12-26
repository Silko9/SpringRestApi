package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Coin;
import ru.shapov.SpringRestApi.repository.CoinRepository;

import java.util.List;

/**
 * Контроллер монет
 * <p>
 * Контроллер содержит в себе энд-поинты для взаимодйствия с монетами через API сервера.
 * Корневой маршрут для всех энд-поинтов /coins
 *
 * @author ShapovAA
 */
@RestController
@RequestMapping("/coins")
public class CoinController {
    /**
     * Репозиторий монет
     */
    private final CoinRepository repository;

    /**
     * Конструктор для контроллера. Вызывается посредством spring, подставляя все необходимые зависимости через @Autowired
     * @param repository репозиторий монет
     */
    @Autowired
    public CoinController(CoinRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод получение всех монет через энд поинт /get-all
     * @return лист монет
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Coin>> getAllCoins() {
        List<Coin> coins = (List<Coin>) repository.findAll();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    /**
     * Метод получения монеты по уникальному идентификатору через энд-поинт /get/{id}
     * @param id уникальный идентификатор монеты
     * @return сущность монеты
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Coin> getCoinById(@PathVariable Long id) {
        return repository.findById(id)
                .map(coin -> new ResponseEntity<>(coin, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод добавление монеты в БД, через энд-поинт /add
     * @param coin сущность монеты, должен содержаться в теле запроса
     * @return сущность созданной монеты
     */
    @PostMapping("/add")
    public ResponseEntity<Coin> createCoin(@RequestBody Coin coin) {
        Coin createCoin = repository.save(coin);
        return new ResponseEntity<>(createCoin, HttpStatus.CREATED);
    }

    /**
     * Метод изменение сущности монеты в БД, через энд-поинт /edit{id}
     * @param id уникальный идентификатор монеты
     * @param updateCoin сущность изменненой монеты
     * @return сущность монеты после изменений
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Coin> updateCoin(@PathVariable Long id, @RequestBody Coin updateCoin) {
        return repository.findById(id)
                .map(coin -> {
                    coin.setCountryId(updateCoin.getCountryId());
                    coin.setCurrencyId(updateCoin.getCurrencyId());
                    coin.setDenomination(updateCoin.getDenomination());
                    coin.setMintId(updateCoin.getMintId());
                    coin.setPicturePath(updateCoin.getPicturePath());
                    coin.setYearMinting(updateCoin.getYearMinting());
                    Coin savedCoin = repository.save(coin);
                    return new ResponseEntity<>(savedCoin, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод удаление сущности монеты из БД, через энд-поинт /delete/{id}
     * @param id уникальный идентификатор монеты
     * @return сущность со статусом запроса
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCoin(@PathVariable Long id) {
        return repository.findById(id)
                .map(coin -> {
                    repository.delete(coin);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
