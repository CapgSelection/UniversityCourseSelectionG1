package com.example.UniversityCourseSelectionG1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
	private String staffName;
	private String password;
	private String role;

}
