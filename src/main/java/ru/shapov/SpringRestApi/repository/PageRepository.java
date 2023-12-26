package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.Page;

import javax.sound.midi.Track;
import java.util.List;

/**
 * Репозиторий страниц
 * <p>
 * Наследуются от интерфейса CrudRepository, который реализует базовые действия с бд, например:
 * findAll - нахождение всех сущностей
 * findById - нахождение сущности по уникальному идентификатору
 * save - сохранение сущности
 * delete - удаление сущности
 *
 * @author ShapovAA
 */
public interface PageRepository extends CrudRepository<Page, Long> {
    /**
     * Метод получения всех страниц по уникальному идентификатору альбома
     * @param albumId уникальный идентификатор альбома
     * @return лист страниц
     */
    List<Page> findByAlbumId(Long albumId);
}
