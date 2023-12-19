package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Country;
import ru.shapov.SpringRestApi.repository.CountryRepository;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryRepository repository;

    @Autowired
    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = (List<Country>) repository.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        return repository.findById(id)
                .map(country -> new ResponseEntity<>(country, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country createCountry = repository.save(country);
        return new ResponseEntity<>(createCountry, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country updateCountry) {
        return repository.findById(id)
                .map(country -> {
                    country.setName(updateCountry.getName());
                    Country savedCountry = repository.save(country);
                    return new ResponseEntity<>(savedCountry, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        return repository.findById(id)
                .map(country -> {
                    repository.delete(country);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
