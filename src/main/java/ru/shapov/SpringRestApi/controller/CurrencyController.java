package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Currency;
import ru.shapov.SpringRestApi.repository.CurrencyRepository;

import java.util.List;

/**
 * Контроллер валют
 * <p>
 * Контроллер содержит в себе энд-поинты для взаимодйствия с валютами через API сервера.
 * Корневой маршрут для всех энд-поинтов /currencies
 *
 * @author ShapovAA
 */
@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    /**
     * Репозиторий валют
     */
    private final CurrencyRepository repository;

    /**
     * Конструктор для контроллера. Вызывается посредством spring, подставляя все необходимые зависимости через @Autowired
     * @param repository репозиторий валют
     */
    @Autowired
    public CurrencyController(CurrencyRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод получение всех валют через энд поинт /get-all
     * @return лист валют
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        List<Currency> currencies = (List<Currency>) repository.findAll();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    /**
     * Метод получения валюты по уникальному идентификатору через энд-поинт /get/{id}
     * @param id уникальный идентификатор валюты
     * @return сущность валюты
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
        return repository.findById(id)
                .map(currency -> new ResponseEntity<>(currency, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод добавление валюты в БД, через энд-поинт /add
     * @param currency сущность валюты, должен содержаться в теле запроса
     * @return сущность созданной валюты
     */
    @PostMapping("/add")
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
        Currency createCurrency = repository.save(currency);
        return new ResponseEntity<>(createCurrency, HttpStatus.CREATED);
    }

    /**
     * Метод изменение сущности валюты в БД, через энд-поинт /edit{id}
     * @param id уникальный идентификатор валюты
     * @param updateCurrency сущность изменненого валюты
     * @return сущность валюты после изменений
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable Long id, @RequestBody Currency updateCurrency) {
        return repository.findById(id)
                .map(currency -> {
                    currency.setName(updateCurrency.getName());
                    Currency savedCurrency = repository.save(currency);
                    return new ResponseEntity<>(savedCurrency, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод удаление сущности валюты из БД, через энд-поинт /delete/{id}
     * @param id уникальный идентификатор валюты
     * @return сущность со статусом запроса
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        return repository.findById(id)
                .map(currency -> {
                    repository.delete(currency);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
