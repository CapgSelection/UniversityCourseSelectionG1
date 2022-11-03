package com.example.UniversityCourseSelectionG1.entities;

//import javax.persistence.Entity;
//
//import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Authorization {
	private int id;
	private String pass;
}
