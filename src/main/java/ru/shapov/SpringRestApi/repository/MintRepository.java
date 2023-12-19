package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.Mint;

public interface MintRepository extends CrudRepository<Mint, Long> {
}
