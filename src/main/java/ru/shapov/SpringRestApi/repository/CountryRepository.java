package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
}