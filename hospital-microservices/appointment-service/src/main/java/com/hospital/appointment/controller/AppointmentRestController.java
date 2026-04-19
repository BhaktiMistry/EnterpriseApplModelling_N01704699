package com.hospital.appointment.controller;

import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentRestController {
    private final AppointmentService service;

    public AppointmentRestController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Appointment> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Appointment> search(@RequestParam String type) { return service.searchByType(type); }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody Appointment appointment) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        appointment.setId(id);
        return ResponseEntity.ok(service.save(appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
