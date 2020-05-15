package com.example.carehours.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Component
public class StudentHours {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long hoursId;
	@NotNull(message="Hours can't be blank")
	private double hours;
	@NotBlank(message="Date can't be blank")
	private String date;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable = false)
	private Student student;
	
	public StudentHours() {
		// TODO Auto-generated constructor stub
	}

	public StudentHours(long hoursId, double hours, String date, Student student) {
		super();
		this.hoursId = hoursId;
		this.hours = hours;
		this.date = date;
		this.student = student;
	}

	public long getHoursId() {
		return hoursId;
	}

	public void setHoursId(long hoursId) {
		this.hoursId = hoursId;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}	
	
}
