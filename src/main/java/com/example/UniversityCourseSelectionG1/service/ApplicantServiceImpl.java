package com.example.UniversityCourseSelectionG1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.repository.ApplicantRepository;


@Service
public class ApplicantServiceImpl implements ApplicantService {
	
	@Autowired
	ApplicantRepository aplRepositary;

	@Override
	public Applicant applyForCourse(Applicant apl) {
		
		return aplRepositary.save(apl);
	}

}
