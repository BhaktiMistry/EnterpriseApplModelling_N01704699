package com.hospital.appointment.service;

import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> getAll() { return repository.findAll(); }
    public Optional<Appointment> getById(Long id) { return repository.findById(id); }
    public Appointment save(Appointment appointment) { return repository.save(appointment); }
    public void delete(Long id) { repository.deleteById(id); }
    public List<Appointment> searchByType(String type) { return repository.findByTypeContainingIgnoreCase(type); }
}
