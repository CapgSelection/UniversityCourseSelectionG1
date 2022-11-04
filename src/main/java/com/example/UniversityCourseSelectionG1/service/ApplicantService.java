package com.example.UniversityCourseSelectionG1.service;

import java.util.List;

import java.util.Optional;

import com.example.UniversityCourseSelectionG1.entities.Applicant;

public interface ApplicantService {

	Applicant applyForCourse(Applicant apl);

	Optional<Applicant> getById(int applicantId);

	Applicant updateApplicant(Applicant applicant);

	Applicant delApplicant(Applicant applicant);

	List<Applicant> viewAllApplicants();

}
