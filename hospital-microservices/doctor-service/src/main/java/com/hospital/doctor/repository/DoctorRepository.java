package com.hospital.doctor.repository;

import com.hospital.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByNameContainingIgnoreCase(String name);
}
