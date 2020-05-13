package com.example.carehours.model;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class StudentController {
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	StudentHours studentHours;

	@GetMapping("/")
    public String home(Model model) {
		model.addAttribute("students", studentRepo.findAll());
        return "index";
    }
	
	@GetMapping("/signup")
    public String showSignUpForm(Student student) {
        return "add-student";
    }
     
    @PostMapping("/addstudent")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }
         
        studentRepo.save(student);
        model.addAttribute("students", studentRepo.findAll());
        return "index";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
         
        model.addAttribute("student", student);
        return "update-student";
    }
    

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }
             
        studentRepo.save(student);
        model.addAttribute("students", studentRepo.findAll());
        return "index";
    }
    
    @GetMapping("/view/{id}")
    public String showViewForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
         
        model.addAttribute("student", student);
        return "view-student";
    }
         
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student student = studentRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        studentRepo.delete(student);
        model.addAttribute("students", studentRepo.findAll());
        return "index";
    }

}
