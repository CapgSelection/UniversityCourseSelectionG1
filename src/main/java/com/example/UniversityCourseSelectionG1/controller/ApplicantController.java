package com.example.UniversityCourseSelectionG1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.service.ApplicantService;

@RestController("/applicant")
public class ApplicantController {
	
	@Autowired
	ApplicantService aplService;
	
	@PostMapping("/apply")
	public ResponseEntity<Applicant> applyForCourse(@RequestBody Applicant applicant)
	{
		Applicant applyApl= aplService.applyForCourse(applicant);
		return new ResponseEntity<Applicant>(applyApl,HttpStatus.ACCEPTED);
	}

}
