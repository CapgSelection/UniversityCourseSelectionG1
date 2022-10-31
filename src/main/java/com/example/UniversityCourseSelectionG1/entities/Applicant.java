package com.example.UniversityCourseSelectionG1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Applicant {
	
	@Id
	@Column(name= "Applicant ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int applicantId;
	
	@Column(name= "Applicant Name")
	String applicantName;
	
	@Column(name= "Mobile Number")
	long mobileNumber;
	
	@Column(name= "Applicant Degree")
	String applicantDegree;
	
	@Column(name= "Applicant Graduation")
	double applicantGraduation;
	
	@Column(name= "Password")
	String password;
	//Admission admission;
	//AdmissionStatus status;

	
	public Applicant(int applicantId, String applicantName, long mobileNumber, String applicantDegree,
			double applicantGraduation, String password) {
		super();
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.mobileNumber = mobileNumber;
		this.applicantDegree = applicantDegree;
		this.applicantGraduation = applicantGraduation;
		this.password = password;
	}


	public Applicant() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getApplicantId() {
		return applicantId;
	}


	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}


	public String getApplicantName() {
		return applicantName;
	}


	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}


	public long getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getApplicantDegree() {
		return applicantDegree;
	}


	public void setApplicantDegree(String applicantDegree) {
		this.applicantDegree = applicantDegree;
	}


	public double getApplicantGraduation() {
		return applicantGraduation;
	}


	public void setApplicantGraduation(double applicantGraduation) {
		this.applicantGraduation = applicantGraduation;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}

