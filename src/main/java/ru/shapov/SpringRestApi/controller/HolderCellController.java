package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.HolderCell;
import ru.shapov.SpringRestApi.model.Page;
import ru.shapov.SpringRestApi.repository.HolderCellRepository;

import java.util.List;

@RestController
@RequestMapping("/holderCells")
public class HolderCellController {
    private final HolderCellRepository repository;

    @Autowired
    public HolderCellController(HolderCellRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<HolderCell>> getAllHolderCells() {
        List<HolderCell> holderCells = (List<HolderCell>) repository.findAll();
        return new ResponseEntity<>(holderCells, HttpStatus.OK);
    }

    @GetMapping("/get-by-page-id/{id}")
    public ResponseEntity<List<HolderCell>> getPagesByPageId(@PathVariable Long id) {
        List<HolderCell> holderCells = (List<HolderCell>) repository.findByPageId(id);
        return new ResponseEntity<>(holderCells, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HolderCell> getHolderCellById(@PathVariable Long id) {
        return repository.findById(id)
                .map(holderCell -> new ResponseEntity<>(holderCell, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<HolderCell> createHolderCell(@RequestBody HolderCell holderCell) {
        HolderCell createHolderCell = repository.save(holderCell);
        return new ResponseEntity<>(createHolderCell, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HolderCell> updateHolderCell(@PathVariable Long id, @RequestBody HolderCell updateHolderCell) {
        return repository.findById(id)
                .map(holderCell -> {
                    holderCell.setAvailable(false);
                    holderCell.setColumnHolder(updateHolderCell.getColumnHolder());
                    holderCell.setLineHolder(updateHolderCell.getLineHolder());
                    holderCell.setCoinId(updateHolderCell.getCoinId());
                    holderCell.setPageId(updateHolderCell.getPageId());
                    holderCell.setTitle(updateHolderCell.getTitle());
                    HolderCell savedHolderCell = repository.save(holderCell);
                    return new ResponseEntity<>(savedHolderCell, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHolderCell(@PathVariable Long id) {
        return repository.findById(id)
                .map(holderCell -> {
                    repository.delete(holderCell);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}