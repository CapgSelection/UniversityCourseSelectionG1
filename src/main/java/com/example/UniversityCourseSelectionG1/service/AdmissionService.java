package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import com.example.UniversityCourseSelectionG1.entities.Admission;


public interface AdmissionService {
	void addAddmission(Admission admission);

	List<Admission> getAdmissions();

	String delAdmissions();
}
