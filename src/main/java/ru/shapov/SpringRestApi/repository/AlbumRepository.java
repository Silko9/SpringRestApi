package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {
}
