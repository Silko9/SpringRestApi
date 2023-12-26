package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.Mint;

/**
 * Репозиторий монетных дворов
 * <p>
 * Наследуются от интерфейса CrudRepository, который реализует базовые действия с бд, например:
 * findAll - нахождение всех сущностей
 * findById - нахождение сущности по уникальному идентификатору
 * save - сохранение сущности
 * delete - удаление сущности
 *
 * @author ShapovAA
 */
public interface MintRepository extends CrudRepository<Mint, Long> {
}
