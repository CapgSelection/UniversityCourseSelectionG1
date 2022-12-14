package com.example.UniversityCourseSelectionG1.entities;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UniversityStaffMember {

	@Id
	@Column(name = "staff_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer staffId;
	
	@Column(name = "staff_name")
	@NotEmpty(message = "Staff name cannot be empty or null")
	private String staffName;
	
	@NotEmpty(message = "Password cannot be empty or null")
	@Pattern(regexp="(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message="password not in proper format")
	private String password;
	
	@NotEmpty(message = "Role cannot be empty or null")
	private String role;

}
