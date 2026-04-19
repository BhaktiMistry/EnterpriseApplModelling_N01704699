package com.hospital.doctor.service;

import com.hospital.doctor.model.Doctor;
import com.hospital.doctor.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public List<Doctor> getAll() { return repository.findAll(); }
    public Optional<Doctor> getById(Long id) { return repository.findById(id); }
    public Doctor save(Doctor doctor) { return repository.save(doctor); }
    public void delete(Long id) { repository.deleteById(id); }
    public List<Doctor> searchByName(String name) { return repository.findByNameContainingIgnoreCase(name); }
}
