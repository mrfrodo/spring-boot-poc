package no.frodo.poc.controller;

import no.frodo.poc.exception.ResourceNotFoundException;
import no.frodo.poc.model.Poc;
import no.frodo.poc.repository.PocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PocController {

    @Autowired
    PocRepository pocRepository;

    // Get All Pocs
    @GetMapping("/pocs")
    public List<Poc> getAllPocs() {
        return pocRepository.findAll();
    }

    // Create a new Poc
    @PostMapping("/pocs")
    public Poc createPoc(@Valid @RequestBody Poc poc) {
        return pocRepository.save(poc);
    }

    // Get a Single Poc
    @GetMapping("/pocs/{id}")
    public Poc getPocById(@PathVariable(value = "id") Long pocId) {
        return pocRepository.findById(pocId)
                .orElseThrow(() -> new ResourceNotFoundException("Poc", "id", pocId));
    }

    // Update a Poc
    @PutMapping("/pocs/{id}")
    public Poc updatePoc(@PathVariable(value = "id") Long pocId,
                           @Valid @RequestBody Poc pocDetails) {

        Poc poc = pocRepository.findById(pocId)
                .orElseThrow(() -> new ResourceNotFoundException("Poc", "id", pocId));

        poc.setTitle(pocDetails.getTitle());
        poc.setContent(pocDetails.getContent());

        Poc updatedPoc = pocRepository.save(poc);
        return updatedPoc;
    }

    // Delete a Poc
    @DeleteMapping("/pocs/{id}")
    public ResponseEntity<?> deletePoc(@PathVariable(value = "id") Long pocId) {
        Poc poc = pocRepository.findById(pocId)
                .orElseThrow(() -> new ResourceNotFoundException("Poc", "id", pocId));

        pocRepository.delete(poc);

        return ResponseEntity.ok().build();
    }

    // Delete all Pocs
    @DeleteMapping("/pocs/")
    public ResponseEntity<?> deleteAllPoc() {
        List<Poc> pocs = pocRepository.findAll();

        for (Poc p : pocs) {
            pocRepository.delete(p);
        }
        return ResponseEntity.ok().build();
    }
}