package ru.shapov.SpringRestApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapov.SpringRestApi.model.Mint;
import ru.shapov.SpringRestApi.repository.MintRepository;

import java.util.List;

@RestController
@RequestMapping("/mints")
public class MintController {
    private final MintRepository repository;

    @Autowired
    public MintController(MintRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Mint>> getAllMints() {
        List<Mint> mints = (List<Mint>) repository.findAll();
        return new ResponseEntity<>(mints, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Mint> getMintById(@PathVariable Long id) {
        return repository.findById(id)
                .map(mint -> new ResponseEntity<>(mint, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Mint> createMint(@RequestBody Mint mint) {
        Mint createMint = repository.save(mint);
        return new ResponseEntity<>(createMint, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Mint> updateMint(@PathVariable Long id, @RequestBody Mint updateMint) {
        return repository.findById(id)
                .map(mint -> {
                    mint.setName(updateMint.getName());
                    mint.setCountryId(0);
                    Mint savedMint = repository.save(mint);
                    return new ResponseEntity<>(savedMint, HttpStatus.OK);
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
