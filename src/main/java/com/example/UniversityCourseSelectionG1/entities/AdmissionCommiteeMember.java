package com.example.UniversityCourseSelectionG1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
//	@Pattern(regexp="^[0-9]$", message="Contact can only contain numbers")
	private String adminContact;
	
	@Column(name="admin_username")
	@Size(min = 10,max=10)
	@Pattern(regexp="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message="username not in proper format")
	private String adminUsername;
	
	@Column(name="admin_password")
	@Size(min = 5)
//	@Pattern(regexp="^[A-Za-z0-9]$", message="password not in proper format")
	private String adminPassword;

	public AdmissionCommiteeMember(int adminId, String adminName, String adminContact, String adminUsername,
			String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}
	
}