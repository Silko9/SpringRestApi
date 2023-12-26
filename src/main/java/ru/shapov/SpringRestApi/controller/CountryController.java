package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Country;
import ru.shapov.SpringRestApi.repository.CountryRepository;

import java.util.List;

/**
 * Контроллер стран
 * <p>
 * Контроллер содержит в себе энд-поинты для взаимодйствия с странами через API сервера.
 * Корневой маршрут для всех энд-поинтов /countries
 *
 * @author ShapovAA
 */
@RestController
@RequestMapping("/countries")
public class CountryController {
    /**
     * Репозиторий стран
     */
    private final CountryRepository repository;

    /**
     * Конструктор для контроллера. Вызывается посредством spring, подставляя все необходимые зависимости через @Autowired
     * @param repository репозиторий стран
     */
    @Autowired
    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод получение всех стран через энд поинт /get-all
     * @return лист стран
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = (List<Country>) repository.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Метод получения страны по уникальному идентификатору через энд-поинт /get/{id}
     * @param id уникальный идентификатор страны
     * @return сущность страны
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        return repository.findById(id)
                .map(country -> new ResponseEntity<>(country, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Метод добавление страны в БД, через энд-поинт /add
     * @param country сущность страны, должен содержаться в теле запроса
     * @return сущность созданной страны
     */
    @PostMapping("/add")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country createCountry = repository.save(country);
        return new ResponseEntity<>(createCountry, HttpStatus.CREATED);
    }

    /**
     * Метод изменение сущности страны в БД, через энд-поинт /edit{id}
     * @param id уникальный идентификатор страны
     * @param updateCountry сущность изменненой страны
     * @return сущность страны после изменений
     */
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

    /**
     * Метод удаление сущности страны из БД, через энд-поинт /delete/{id}
     * @param id уникальный идентификатор страны
     * @return сущность со статусом запроса
     */
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
