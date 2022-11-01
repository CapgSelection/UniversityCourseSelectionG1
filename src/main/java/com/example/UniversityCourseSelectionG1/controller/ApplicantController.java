package com.example.UniversityCourseSelectionG1.controller;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
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
	
//	@GetMapping("/get/{id}")
//	public ResponseEntity<Optional<Applicant>> getApplicantById(@PathVariable int applicantId)
//	{
//		Optional<Applicant> getApl= aplService.getById(applicantId);
//		return new ResponseEntity<Optional<Applicant>>(getApl, HttpStatus.FOUND);
//	}
	
	@PostMapping("/update")
	public ResponseEntity<Applicant> updateApplicant(Applicant applicant)
	{
		Applicant updateApl=aplService.updateApplicant(applicant);
		return new ResponseEntity<Applicant>(updateApl, HttpStatus.ACCEPTED);
				
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteApplicant(@RequestBody Applicant applicant) {
		
		aplService.delApplicant(applicant);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	

}
