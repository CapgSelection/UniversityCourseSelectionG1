package com.example.UniversityCourseSelectionG1.controller;

import java.util.List;

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

import com.capgemini.UniversityCourseSelection.services.IApplicantService;
import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.entities.AdmissionStatus;
import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.service.AdmissionCommiteeMemberService;
import com.example.UniversityCourseSelectionG1.service.ApplicantService;

@RestController
@RequestMapping("/uni/commitee")
public class AdmissionCommiteeMemberController 
{
	@Autowired
	AdmissionCommiteeMemberService AdmissionServ;
	@Autowired
	private ApplicantService applicantService; 
	
	@PostMapping("/add")
	public ResponseEntity<AdmissionCommiteeMember> addCommiteeMember(@RequestBody AdmissionCommiteeMember member)
	{
		AdmissionCommiteeMember savedMember= AddmissionServ.addCommiteeMember(member);
		return new ResponseEntity<AdmissionCommiteeMember>(savedMember, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<AdmissionCommiteeMember> updateCommiteeMember(@RequestBody AdmissionCommiteeMember member)
	{
		AdmissionCommiteeMember updatedMember= AddmissionServ.updateCommiteeMember(member);
		return new ResponseEntity<AdmissionCommiteeMember>(updatedMember, HttpStatus.OK);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<AdmissionCommiteeMember> viewCommiteeMember(@PathVariable int id)
	{
		AdmissionCommiteeMember member= AddmissionServ.viewCommiteeMember(id);
		return new ResponseEntity<AdmissionCommiteeMember>(member, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeCommiteeMember(@PathVariable int id)
	{
		AddmissionServ.removeCommiteeMember(id);
		return new ResponseEntity<String>("Commitee member with id="+id+" deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<AdmissionCommiteeMember>> viewAllCommiteeMembers()
	{
		List<AdmissionCommiteeMember> allMembers= AddmissionServ.viewAllCommiteeMembers();
		return new ResponseEntity<List<AdmissionCommiteeMember>>(allMembers, HttpStatus.OK);
	}
	
	@GetMapping("/getResult/{id}")
	public ResponseEntity<AdmissionStatus> provideAdmissionResult(@PathVariable int id)
	{
		Applicant applicant=applicantService.viewApplicant(id).get();
		Admission admission=applicant.getAdmission();
		
		AdmissionStatus status=AdmissionServ.provideAdmissionResult(applicant, admission);
		
		return new ResponseEntity<AdmissionStatus>(status, HttpStatus.OK);
	}
}
