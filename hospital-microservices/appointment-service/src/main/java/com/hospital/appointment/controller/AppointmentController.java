package com.hospital.appointment.controller;

import com.hospital.appointment.dto.DoctorDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Controller
public class AppointmentController {
    private final AppointmentService service;
    private final RestTemplate restTemplate;

    @Value("${patient.service.url}")
    private String patientServiceUrl;

    @Value("${doctor.service.url}")
    private String doctorServiceUrl;

    public AppointmentController(AppointmentService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String root() { return "redirect:/appointments"; }

    private List<PatientDTO> patients() {
        try {
            ResponseEntity<List<PatientDTO>> response = restTemplate.exchange(
                    patientServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private List<DoctorDTO> doctors() {
        try {
            ResponseEntity<List<DoctorDTO>> response = restTemplate.exchange(
                    doctorServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @GetMapping("/appointments")
    public String list(Model model) {
        model.addAttribute("appointments", service.getAll());
        return "appointments/list";
    }

    @GetMapping("/appointments/add")
    public String addForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patients());
        model.addAttribute("doctors", doctors());
        return "appointments/form";
    }

    @PostMapping("/appointments/add")
    public String add(@ModelAttribute Appointment appointment) {
        service.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", service.getById(id).orElseThrow());
        model.addAttribute("patients", patients());
        model.addAttribute("doctors", doctors());
        return "appointments/form";
    }

    @PostMapping("/appointments/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Appointment appointment) {
        appointment.setId(id);
        service.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/appointments";
    }
}
