package com.hospital.patient.controller;

import com.hospital.patient.model.Patient;
import com.hospital.patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientRestController {
    private final PatientService service;

    public PatientRestController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Patient> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Patient> search(@RequestParam String name) { return service.searchByName(name); }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        patient.setId(id);
        return ResponseEntity.ok(service.save(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
