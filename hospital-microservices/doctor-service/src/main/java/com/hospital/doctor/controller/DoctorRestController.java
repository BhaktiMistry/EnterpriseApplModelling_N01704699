package com.hospital.doctor.controller;

import com.hospital.doctor.model.Doctor;
import com.hospital.doctor.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorRestController {
    private final DoctorService service;

    public DoctorRestController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Doctor> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Doctor> search(@RequestParam String name) { return service.searchByName(name); }

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor doctor) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        doctor.setId(id);
        return ResponseEntity.ok(service.save(doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
