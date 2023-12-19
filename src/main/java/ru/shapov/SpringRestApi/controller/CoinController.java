package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Coin;
import ru.shapov.SpringRestApi.repository.CoinRepository;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {
    private final CoinRepository repository;

    @Autowired
    public CoinController(CoinRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Coin>> getAllCoins() {
        List<Coin> coins = (List<Coin>) repository.findAll();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Coin> getCoinById(@PathVariable Long id) {
        return repository.findById(id)
                .map(coin -> new ResponseEntity<>(coin, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Coin> createCoin(@RequestBody Coin coin) {
        Coin createCoin = repository.save(coin);
        return new ResponseEntity<>(createCoin, HttpStatus.CREATED);
    }

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
