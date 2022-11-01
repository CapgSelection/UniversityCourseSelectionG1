package com.example.UniversityCourseSelectionG1.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Data
@Entity
@Table(name="Admission")
public class Admission {
	@Id
	@Column(name="ID")
	@GeneratedValue
	private int admissionId;
	@Column(name="course_Id")
	private int courseId;
	@Column(name="applicant_Id")
	private int applicantId;
	@Column(name="admission_Date")
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(pattern = "dd-MMM-yyyy")
	private LocalDate admissionDate;
	
	@OneToOne(mappedBy = "admission")
	private Applicant applicant;
	private AdmissionStatus status;
	
	public Admission() {
		status=AdmissionStatus.PENDING;
		// TODO Auto-generated constructor stub
	}


	public Admission(int admissionId, int courseId, int applicantId, LocalDate admissionDate) {
		super();
		this.admissionId = admissionId;
		this.courseId = courseId;
		this.applicantId = applicantId;
		this.admissionDate = admissionDate;
		this.status=AdmissionStatus.PENDING;
	}


	public int getAdmissionId() {
		return admissionId;
	}


	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public int getApplicantId() {
		return applicantId;
	}


	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}


	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
	//added
	public AdmissionStatus getStatus() {
		return status;
	}

	public void setStatus(AdmissionStatus status) {
		this.status =status;
	}
}
