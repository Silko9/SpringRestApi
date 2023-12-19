package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Currency;
import ru.shapov.SpringRestApi.repository.CurrencyRepository;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyRepository repository;

    @Autowired
    public CurrencyController(CurrencyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        List<Currency> currencies = (List<Currency>) repository.findAll();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
        return repository.findById(id)
                .map(currency -> new ResponseEntity<>(currency, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
        Currency createCurrency = repository.save(currency);
        return new ResponseEntity<>(createCurrency, HttpStatus.CREATED);
    }

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
