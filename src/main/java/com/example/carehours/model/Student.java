package com.example.carehours.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message="Name can't be blank")
	private String name;
	
	@OneToMany(mappedBy = "student")
	private Set<StudentHours> studentHours;

	public Student() {
		// TODO Auto-generated constructor stub
	}


	public Student(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<StudentHours> getStudentHours() {
		return studentHours;
	}


	public void setStudentHours(Set<StudentHours> studentHours) {
		this.studentHours = studentHours;
	}
	
	

}
