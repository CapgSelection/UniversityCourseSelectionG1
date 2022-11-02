package com.example.UniversityCourseSelectionG1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.entities.AdmissionStatus;
import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.exception.NotLoggedInException;
import com.example.UniversityCourseSelectionG1.service.AdmissionCommiteeMemberService;
import com.example.UniversityCourseSelectionG1.service.ApplicantService;

@RestController
@RequestMapping("/uni/commitee")
public class AdmissionCommiteeMemberController 
{
	@Autowired
	AdmissionCommiteeMemberService admissionServ;
	@Autowired
	private ApplicantService applicantService; 
	
	private boolean checkSession(HttpServletRequest request, String type) {
		HttpSession session = request.getSession();

		boolean validLogin = true;
		if (session.isNew()) {
			validLogin = false;
		} 
		else if( session.getAttribute(type) == null)
		{
			validLogin = false;
		}
		else 
		{
			int userId = (int) session.getAttribute(type);
			if (userId != 0)
				validLogin = true;
		}
		return validLogin;
	}
	
//	@GetMapping("/check")
//	public ResponseEntity<Object> check(HttpServletRequest request)
//	{
//		Object ob=request.getSession().getAttributeNames();
//		System.out.println(ob);
//		
//		return new ResponseEntity<Object>(ob, HttpStatus.OK);
//	}
	
	@PostMapping("/add")
	public ResponseEntity<AdmissionCommiteeMember> addCommiteeMember(@RequestBody AdmissionCommiteeMember member, HttpServletRequest request)
	{
		if(!checkSession(request, "commitee")) {
			String port = String.valueOf(request.getServerPort());			
			throw new NotLoggedInException("Accessible to commitee only. If you are a registered commitee member, click http://localhost:"+port+"/login/commitee to login.");
		}
		
		AdmissionCommiteeMember savedMember= admissionServ.addCommiteeMember(member);
		return new ResponseEntity<AdmissionCommiteeMember>(savedMember, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<AdmissionCommiteeMember> updateCommiteeMember(@RequestBody AdmissionCommiteeMember member, HttpServletRequest request)
	{
		if(!checkSession(request, "commitee")) {
			String port = String.valueOf(request.getServerPort());			
			throw new NotLoggedInException("Accessible to commitee only. If you are a registered commitee member, click http://localhost:"+port+"/login/commitee to login.");
		}
		AdmissionCommiteeMember updatedMember= admissionServ.updateCommiteeMember(member);
		return new ResponseEntity<AdmissionCommiteeMember>(updatedMember, HttpStatus.OK);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<AdmissionCommiteeMember> viewCommiteeMember(@PathVariable int id, HttpServletRequest request)
	{
		if(!checkSession(request, "commitee")) {
			String port = String.valueOf(request.getServerPort());			
			throw new NotLoggedInException("Accessible to commitee only. If you are a registered commitee member, click http://localhost:"+port+"/login/commitee to login.");
		}
		
		HttpSession session = request.getSession();
		int LoginId = (int) session.getAttribute("commitee");
		
		AdmissionCommiteeMember member= admissionServ.viewCommiteeMember(id);
		
		if(LoginId!= id)
		{
			member.setAdminPassword("********");
		}
		
		return new ResponseEntity<AdmissionCommiteeMember>(member, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeCommiteeMember(@PathVariable int id, HttpServletRequest request)
	{
		try {
			admissionServ.removeCommiteeMember(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Commitee Member with id: " + id + " not found! ", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Commitee member with id="+id+" deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<AdmissionCommiteeMember>> viewAllCommiteeMembers(HttpServletRequest request)
	{
		if(!checkSession(request, "commitee")) {
			String port = String.valueOf(request.getServerPort());			
			throw new NotLoggedInException("Accessible to commitee only. If you are a registered commitee member, click http://localhost:"+port+"/login/commitee to login.");
		}
		List<AdmissionCommiteeMember> allMembers= admissionServ.viewAllCommiteeMembers();
		allMembers.forEach(s->s.setAdminPassword("*******"));
		return new ResponseEntity<List<AdmissionCommiteeMember>>(allMembers, HttpStatus.OK);
	}
	
	@GetMapping("/getResult/{id}")
	public ResponseEntity<AdmissionStatus> provideAdmissionResult(@PathVariable int id, HttpServletRequest request)
	{
		if(!checkSession(request, "commitee")) {
			String port = String.valueOf(request.getServerPort());			
			throw new NotLoggedInException("Accessible to commitee only. If you are a registered commitee member, click http://localhost:"+port+"/login/commitee to login.");
		}
		
		Applicant applicant=applicantService.getById(id).get();
		Admission admission=applicant.getAdmission();
		
		AdmissionStatus status=admissionServ.provideAdmissionResult(applicant, admission);
		
		return new ResponseEntity<AdmissionStatus>(status, HttpStatus.OK);
	}
}
