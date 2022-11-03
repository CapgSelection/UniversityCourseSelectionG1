package com.example.UniversityCourseSelectionG1.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Data
@Entity
@Table(name="course")
public class Course {

	@Id
	@Column(name="course_id")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE)
	private Integer courseId;
	
	@Column(name="course_name")
	private String courseName;
	
	@Column(name="course_duration")
	private String courseDuration;
	
	@Column(name="course_start_date")
	@JsonFormat(pattern="dd-MMM-yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
//	@JsonFormat(pattern = "dd-MMM-yyyy")
	private LocalDate courseStartDate;
	
	@Column(name="course_end_date")
	@JsonFormat(pattern="dd-MMM-yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
//	@JsonFormat(pattern = "dd-MMM-yyyy")
	private LocalDate courseEndDate;
	
	@Column(name="course_fees")
	private String courseFees;
	
	@Column(name="course_criteria")
	private double courseCriteria;
	
	@ManyToMany(mappedBy = "course")
	List<Applicant> applicant;
	
	private String status;
	
	public Course() {
		status="ACTIVE";
	}

	public Course(int i, String string, String string2, LocalDate of, LocalDate of2, String string3, double d) {
		this.courseId=i;
		this.courseName=string;
		this.courseDuration=string2;
		this.courseStartDate=of;
		this.courseEndDate=of2;
		this.courseFees=string3;
		this.courseCriteria=d;
		// TODO Auto-generated constructor stub
	}

	
}
