package com.example.UniversityCourseSelectionG1.service;

import java.time.LocalDate;
import java.util.List;

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;


public interface AdmissionService {
	void addAddmission(Admission admission) throws NotFoundException;

	List<Admission> getAdmissions();

	String delAdmissions();

	String delAdmissionsById(int id)throws NotFoundException ;

	List<Admission> getAdmissionbyCourse(int cId);

	List<Admission> showAllAdmissionbyDate(LocalDate localdate);
}
