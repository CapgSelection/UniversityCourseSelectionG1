package com.example.UniversityCourseSelectionG1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "staff_sequence")
	@SequenceGenerator(name = "staff_sequence", 
						sequenceName = "staff_sequence", 
						allocationSize = 1)
	private Integer staffId;
	
	@Column(name = "staff_name")
	@NotEmpty(message = "Staff name cannot be empty or null")
	private String staffName;
	
	@NotEmpty(message = "Password cannot be empty or null")
	private String password;
	
	
	private String role;

}
