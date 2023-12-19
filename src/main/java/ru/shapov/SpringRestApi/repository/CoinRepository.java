package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.Coin;

public interface CoinRepository extends CrudRepository<Coin, Long> {
}
