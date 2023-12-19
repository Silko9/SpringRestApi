package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.Page;

import javax.sound.midi.Track;
import java.util.List;

public interface PageRepository extends CrudRepository<Page, Long> {
    List<Page> findByAlbumId(Long albumId);
}
