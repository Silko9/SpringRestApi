package ru.shapov.SpringRestApi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shapov.SpringRestApi.model.HolderCell;

import java.util.List;

public interface HolderCellRepository extends CrudRepository<HolderCell, Long> {
    List<HolderCell> findByPageId(Long pageId);
}
