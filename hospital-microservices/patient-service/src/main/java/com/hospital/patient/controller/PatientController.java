package com.hospital.patient.controller;

import com.hospital.patient.model.Patient;
import com.hospital.patient.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String root() { return "redirect:/patients"; }

    @GetMapping("/patients")
    public String list(Model model) {
        model.addAttribute("patients", service.getAll());
        return "patients/list";
    }

    @GetMapping("/patients/add")
    public String addForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/form";
    }

    @PostMapping("/patients/add")
    public String add(@ModelAttribute Patient patient) {
        service.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", service.getById(id).orElseThrow());
        return "patients/form";
    }

    @PostMapping("/patients/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Patient patient) {
        patient.setId(id);
        service.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/patients";
    }
}
