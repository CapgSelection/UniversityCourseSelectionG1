package com.example.UniversityCourseSelectionG1.entities;

import java.util.Objects;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

@Table(name = "Applicant")

public class Applicant {
	
	@Id
	@Column(name= "Applicant_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int applicantId;
		
	@Column(name= "Applicant Name")
	String applicantName;
	
	@Size(min = 10,max=10)
	@Pattern(regexp="^[0-9]*$", message="Contact can only contain numbers")
	@Column(name= "Mobile Number")
	String mobileNumber;
	
	@Column(name= "Applicant Degree")
	String applicantDegree;
	
	@Max(value = 10)
	@Column(name= "Applicant Graduation")
	double applicantGraduation;
	
	@Size(min = 5)
	@Pattern(regexp="^[A-Za-z0-9@$!%*#?&]*$", message="password not in proper format")
	@Column(name= "Password")
	String password;

	@OneToOne
	Admission admission;
	@ManyToOne
    @JoinColumn(name = "admissioncommiteeMember_id")
    AdmissionCommiteeMember admissionCommiteeMember;
	
	AdmissionStatus status;


	
	public Applicant() {
		super();
		status = AdmissionStatus.APPLIED;
	}
	
	public Applicant(int applicantId, String applicantName, String mobileNumber, String applicantDegree,
			double applicantGraduation, String password, Admission admission, AdmissionStatus status,AdmissionCommiteeMember admissionCommiteeMember) {
		super();
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.mobileNumber = mobileNumber;
		this.applicantDegree = applicantDegree;
		this.applicantGraduation = applicantGraduation;
		this.password = password;

		this.admission = admission;
		this.status = status;
		this.admissionCommiteeMember =admissionCommiteeMember;

	}

	public AdmissionCommiteeMember getAdmissionCommiteeMember() {
		return admissionCommiteeMember;
	}

	public void setAdmissionCommiteeMember(AdmissionCommiteeMember admissionCommiteeMember) {
		this.admissionCommiteeMember = admissionCommiteeMember;
	}

	public Admission getAdmission() {
		return admission;
	}



	public void setAdmission(Admission admission) {
		this.admission = admission;
	}


	public AdmissionStatus getStatus() {
		return status;
	}


	public void setStatus(AdmissionStatus status) {
		this.status = status;
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


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
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

	@Override
	public int hashCode() {
		return Objects.hash(admission, applicantDegree, applicantGraduation, applicantId, applicantName, mobileNumber,
				password, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applicant other = (Applicant) obj;
		return Objects.equals(admission, other.admission) && Objects.equals(applicantDegree, other.applicantDegree)
				&& Double.doubleToLongBits(applicantGraduation) == Double.doubleToLongBits(other.applicantGraduation)
				&& applicantId == other.applicantId && Objects.equals(applicantName, other.applicantName)
				&& mobileNumber == other.mobileNumber && Objects.equals(password, other.password)
				&& status == other.status;
	}
	
	
}

