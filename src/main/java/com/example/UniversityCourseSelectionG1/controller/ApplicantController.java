package com.example.UniversityCourseSelectionG1.controller;

import java.util.List;



import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.exception.NotLoggedInException;
import com.example.UniversityCourseSelectionG1.service.ApplicantService;


@RestController
@RequestMapping("/applicant")
public class ApplicantController {
	
	@Autowired
	ApplicantService aplService;
	
	public boolean checkSession(HttpServletRequest request, String type) {
		HttpSession session = request.getSession();

		boolean validLogin = true;
		if (session.isNew())
			return false;
		if (session.getAttribute(type) == null)
			return false;
		int userId = (int) session.getAttribute(type);
		if (userId == 0)
			validLogin = false;

		return validLogin;
	}
	
	@PostMapping("/apply")
	public ResponseEntity<Applicant> applyForCourse(@Valid @RequestBody Applicant applicant)
	{
		Applicant applyApl= aplService.applyForCourse(applicant);
		return new ResponseEntity<Applicant>(applyApl,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<Applicant>> getApplicantById(@PathVariable int applicantId, HttpServletRequest request)
	{
		boolean valid = checkSession(request, "applicant") || checkSession(request, "commitee")
				|| checkSession(request, "staffMember");
		String host = String.valueOf(request.getServerPort());
		if (!valid) {
			throw new NotLoggedInException(
					"Kindly login to view your details.  click " + host + "/login/applicant to login.");

		}
		HttpSession session = request.getSession();

		Optional<Applicant> getApl= aplService.getById(applicantId);
		if (getApl.isEmpty()) {
			throw new NotFoundException("No user with given Id is present");

		}
		if (checkSession(request, "applicant")) {
			int attId = (int) session.getAttribute("applicant");
			if (attId != applicantId) {
				getApl.get().setPassword("******");
			}
		} else {
			getApl.get().setPassword("******");
		}
		
		
		return new ResponseEntity<Optional<Applicant>>(getApl, HttpStatus.FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Applicant> updateApplicant(@Valid @RequestBody Applicant applicant,HttpServletRequest request)
	{
		boolean valid=checkSession(request,"applicant");
		String host = String.valueOf(request.getServerPort());
		if(!valid) {
			throw new NotLoggedInException("Please Login to update details, click " + host
					+ "/login/applicant to login");
		}
		
//		if (applicant == null || applicant.getApplicantId() == null) {
//			throw new NotFoundException("Applicant or Id can't be null!");
//		}
		HttpSession session=request.getSession();
		if(applicant.getApplicantId()!=(int)session.getAttribute("applicant")){
			throw new NotLoggedInException("You can only update your own details");
		}
		Applicant updateApl=aplService.updateApplicant(applicant);
		return new ResponseEntity<Applicant>(updateApl, HttpStatus.ACCEPTED);
				
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteApplicant(@Valid @RequestBody Applicant applicant,HttpServletRequest request) {
		
		boolean valid = checkSession(request, "applicant");
		String host = String.valueOf(request.getServerPort());
		
		if (!valid) {
			throw new NotLoggedInException(
					"Accessible to commitee members only. If you are a registered commitee member, click " + host
							+ "/login/commitee to login.");

		}
//		if (applicant == null || applicant.getApplicantId() == null) {
//			throw new NotFoundException("Applicant or Id can't be null!");
//		}
		HttpSession session=request.getSession();
		if(applicant.getApplicantId()!=(int)session.getAttribute("applicant")){
			throw new NotLoggedInException("You can only update your own details");
		}
		aplService.delApplicant(applicant);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/getAll/{status}")
	public ResponseEntity<List<Applicant>> getAllApplicants(@PathVariable int status, HttpServletRequest request)
	{
		boolean valid = checkSession(request, "commitee");
		String host = String.valueOf(request.getServerPort());
		if (!valid) {
			throw new NotLoggedInException(
					"Accessible to commitee members only. If you are a registered commitee member, click " + host
							+ "/login/commitee to login.");

		}
		
		List<Applicant> res = aplService.viewAllApplicantsByStatus(status);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	

}
