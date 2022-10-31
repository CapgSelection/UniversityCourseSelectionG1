package com.example.UniversityCourseSelectionG1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="admission_commitee_member")
public class AdmissionCommiteeMember {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="admin_name")
	private String adminName;
	
	@Column(name="admin_contact")
	private String adminContact;
	
	@Column(name="admin_username")
	private String adminUsername;
	
	@Column(name="admin_password")
	private String adminPassword;
}