package com.example.UniversityCourseSelectionG1.entities;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

@Entity

@Table(name="admission_commitee_member", schema = "public")
public class AdmissionCommiteeMember {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="admin_name")
	@NotEmpty(message="Name can not be empty")
	private String adminName;
	
	@Column(name="admin_contact")
	@Size(min = 10,max=10)
	@Pattern(regexp="^[0-9]*$", message="Contact can only contain numbers")
	private String adminContact;
	
	@Column(name="admin_username")
	@Size(min = 3)
	@Pattern(regexp="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message="username not in proper format")
	private String adminUsername;
	
	@Column(name="admin_password")
	@Size(min = 5)
	@Pattern(regexp="^[A-Za-z0-9@$!%*#?&]*$", message="password not in proper format")
	private String adminPassword;

	 @OneToMany(cascade = CascadeType.ALL,mappedBy = "admissionCommiteeMember")
	 private List<Applicant> applicants;

	public AdmissionCommiteeMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdmissionCommiteeMember(int adminId, @NotEmpty(message = "Name can not be empty") String adminName,
			@Size(min = 10, max = 10) @Pattern(regexp = "^[0-9]*$", message = "Contact can only contain numbers") String adminContact,
			@Size(min = 3) @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "username not in proper format") String adminUsername,
			@Size(min = 5) @Pattern(regexp = "^[A-Za-z0-9@$!%*#?&]*$", message = "password not in proper format") String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	 
	 

	
}