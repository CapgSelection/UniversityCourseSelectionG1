package com.example.UniversityCourseSelectionG1.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer courseId;
	private String courseName;
	private String courseDuration;
	
	private LocalDate courseStartDate;
	private LocalDate courseEndDate;
	private String courseFees;
	private double courseCriteria;
	private String status;
	
	public Course() {
		status="ACTIVE";
	}
}
