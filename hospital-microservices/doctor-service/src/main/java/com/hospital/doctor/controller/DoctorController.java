package com.hospital.doctor.controller;

import com.hospital.doctor.model.Doctor;
import com.hospital.doctor.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String root() { return "redirect:/doctors"; }

    @GetMapping("/doctors")
    public String list(Model model) {
        model.addAttribute("doctors", service.getAll());
        return "doctors/list";
    }

    @GetMapping("/doctors/add")
    public String addForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctors/form";
    }

    @PostMapping("/doctors/add")
    public String add(@ModelAttribute Doctor doctor) {
        service.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", service.getById(id).orElseThrow());
        return "doctors/form";
    }

    @PostMapping("/doctors/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Doctor doctor) {
        doctor.setId(id);
        service.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/doctors";
    }
}
