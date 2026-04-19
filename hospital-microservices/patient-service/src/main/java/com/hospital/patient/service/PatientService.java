package com.hospital.patient.service;

import com.hospital.patient.model.Patient;
import com.hospital.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> getAll() { return repository.findAll(); }
    public Optional<Patient> getById(Long id) { return repository.findById(id); }
    public Patient save(Patient patient) { return repository.save(patient); }
    public void delete(Long id) { repository.deleteById(id); }
    public List<Patient> searchByName(String name) { return repository.findByNameContainingIgnoreCase(name); }
}
