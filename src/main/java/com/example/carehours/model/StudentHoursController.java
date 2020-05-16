package com.example.carehours.model;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String addHours(@PathVariable("id") Long id, @Valid StudentHours studentHours, Model model) {
        return studentRepo.findById(id).map(student -> {
        	studentHours.setStudent(student);
            hoursRepo.save(studentHours);

            model.addAttribute("student", student);
            return "view-student";
        }).orElseThrow(() -> new ResourceNotFoundException("Student Id " + id + " not found"));
    }
	
	@GetMapping("/addhours/{id}")
    public String showAddHoursForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + id));
        
        studentHours.setStudent(student);
        model.addAttribute("studentHours", studentHours);
        return "add-hours";
    }    
	
	@GetMapping("/edithours/{hoursid}")
    public String showUpdateForm(@PathVariable("hoursid") long hoursid, Model model) {
        StudentHours studentHours = hoursRepo.findById(hoursid)
          .orElseThrow(() -> new IllegalArgumentException("Invalid student hours Id:" + hoursid));
         
        model.addAttribute("studentHours", studentHours);
        return "update-student-hours";
    }
	
	@PostMapping("/updatehours/{hoursid}/{id}")
    public String updateStudentHours(@PathVariable long hoursid, @PathVariable long id, @Valid StudentHours studentHours, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            studentHours.setHoursId(hoursid);
            return "update-student";
        }
             
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + id));
              
        studentHours.setStudent(student);
        hoursRepo.save(studentHours);
        
        model.addAttribute("student", student);
        return "view-student";
    }
	
	@GetMapping("/deletehours/{hoursid}")
    public String deleteStudent(@PathVariable("hoursid") long hoursid, Model model) {
        StudentHours studentHours = hoursRepo.findById(hoursid)
          .orElseThrow(() -> new IllegalArgumentException("Invalid student hours Id:" + hoursid));
        hoursRepo.delete(studentHours);
        
        Student student = studentRepo.findById(studentHours.getStudent().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + studentHours.getStudent().getId()));

        model.addAttribute("student", student);
        return "view-student";
    }

}
