package com.example.carehours.model;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.carehours.exception.ResourceNotFoundException;

@Controller
public class StudentHoursController {
	
	@Autowired
	private StudenetHoursRepository hoursRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private StudentHours studentHours;
	
	@PostMapping("/addhours/{id}")
    public String addHours(@PathVariable (value = "id") Long id,
                                 @Valid StudentHours studentHours, Model model) {
        return studentRepo.findById(id).map(student -> {
        	studentHours.setStudent(student);
            hoursRepo.save(studentHours);
            model.addAttribute("students", studentRepo.findAll());
            
            return "index";
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
    }
	
	@GetMapping("/addhours/{id}")
    public String showAddHoursForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        
        studentHours.setStudent(student);
        model.addAttribute("studentHours", studentHours);
        return "add-hours";
    }    

}
