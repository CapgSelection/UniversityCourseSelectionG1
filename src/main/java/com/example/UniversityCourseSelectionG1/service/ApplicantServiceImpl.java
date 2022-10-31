package com.example.UniversityCourseSelectionG1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.repository.ApplicantRepository;


@Service
public class ApplicantServiceImpl implements ApplicantService {
	
	@Autowired
	ApplicantRepository aplRepository;

	@Override
	public Applicant applyForCourse(Applicant apl) {
		
		return aplRepository.save(apl);
	}

	@Override
	public Optional<Applicant> getById(int applicantId) {
		
		return aplRepository.findById(applicantId);
	}

	@Override
	public Applicant updateApplicant(Applicant applicant) {
		
		return aplRepository.save(applicant);
	}

	@Override
	public void delApplicant(Applicant applicant) {
		
		if(aplRepository.existsById(applicant.getApplicantId()))
		{
			aplRepository.delete(applicant);
		}
	}

}
