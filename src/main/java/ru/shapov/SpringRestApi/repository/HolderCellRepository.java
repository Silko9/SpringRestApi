package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.HolderCell;

import java.util.List;

/**
 * Репозиторий холдеров
 * <p>
 * Наследуются от интерфейса CrudRepository, который реализует базовые действия с бд, например:
 * findAll - нахождение всех сущностей
 * findById - нахождение сущности по уникальному идентификатору
 * save - сохранение сущности
 * delete - удаление сущности
 *
 * @author ShapovAA
 */
public interface HolderCellRepository extends CrudRepository<HolderCell, Long> {
    /**
     * Метод получение всех холдеров по уникальному идентификатору страницы
     * @param pageId уникальный идентификатор страницы
     * @return лист холдеров
     */
    List<HolderCell> findByPageId(Long pageId);
}
