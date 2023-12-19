package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
