package com.example.UniversityCourseSelectionG1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.service.AdmissionCommiteeMemberService;

@RestController
@RequestMapping("/uni/commitee")
public class AdmissionCommiteeMemberController 
{
	@Autowired
	AdmissionCommiteeMemberService AddmissionServ;
	
	@RequestMapping("/add")
	public ResponseEntity<AdmissionCommiteeMember> addCommiteeMember(@RequestBody AdmissionCommiteeMember member)
	{
		AdmissionCommiteeMember savedMember= AddmissionServ.addCommiteeMember(member);
		return new ResponseEntity<AdmissionCommiteeMember>(savedMember, HttpStatus.OK);
	}
	
	@RequestMapping("/update")
	public ResponseEntity<AdmissionCommiteeMember> updateCommiteeMember(@RequestBody AdmissionCommiteeMember member)
	{
		AdmissionCommiteeMember updatedMember= AddmissionServ.updateCommiteeMember(member);
		return new ResponseEntity<AdmissionCommiteeMember>(updatedMember, HttpStatus.OK);
	}
	
	@RequestMapping("/view/{id}")
	public ResponseEntity<AdmissionCommiteeMember> viewCommiteeMember(@PathVariable int id)
	{
		AdmissionCommiteeMember member= AddmissionServ.viewCommiteeMember(id);
		return new ResponseEntity<AdmissionCommiteeMember>(member, HttpStatus.OK);
	}
	
	@RequestMapping("/delete/{id}")
	public ResponseEntity<String> removeCommiteeMember(@PathVariable int id)
	{
		AddmissionServ.removeCommiteeMember(id);
		return new ResponseEntity<String>("Commitee member with id="+id+" deleted successfully", HttpStatus.OK);
	}
	
	@RequestMapping("/viewAll")
	public ResponseEntity<List<AdmissionCommiteeMember>> viewAllCommiteeMembers()
	{
		List<AdmissionCommiteeMember> allMembers= AddmissionServ.viewAllCommiteeMembers();
		return new ResponseEntity<List<AdmissionCommiteeMember>>(allMembers, HttpStatus.OK);
	}
}
