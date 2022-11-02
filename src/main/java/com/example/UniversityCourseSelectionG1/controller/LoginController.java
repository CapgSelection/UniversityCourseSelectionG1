package com.example.UniversityCourseSelectionG1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UniversityCourseSelectionG1.entities.Authorization;
import com.example.UniversityCourseSelectionG1.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

//	@GetMapping("/applicant/{userName}/{password}")	
//	public ResponseEntity<String> applicantLogin(@PathVariable int userName, @PathVariable String password,
//			HttpServletRequest request) {
//		
//		Integer loggedUser = (Integer)request.getSession().getAttribute("applicant");
//		if(loggedUser != null && loggedUser == userName) {
//			return new ResponseEntity<String>("User already logged in!", HttpStatus.FORBIDDEN);
//		}
//		
//		if (loginService.loginAsApplicant(userName, password))
//		{
//			HttpSession session = request.getSession(true);
//			session.setAttribute("applicant", userName);
//			return new ResponseEntity<>("Logged in successfully!",HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.FORBIDDEN);
//
//	}
	@PostMapping("/applicant/auth")	
	public ResponseEntity<String> applicantLogin(@RequestBody Authorization auth , HttpServletRequest request) {
		
		Integer loggedUser = (Integer)request.getSession().getAttribute("applicant");
		if(loggedUser != null && loggedUser == auth.getId()) {
			return new ResponseEntity<String>("User already logged in!", HttpStatus.FORBIDDEN);
		}
		
		if (loginService.loginAsApplicant(auth.getId(), auth.getPass()))
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("applicant", auth.getId());
			return new ResponseEntity<>("Logged in successfully!",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.FORBIDDEN);
		
	}
	@GetMapping("/staffMember/auth")	
	public ResponseEntity<String> staffLogin(@RequestBody Authorization auth , HttpServletRequest request) {
		
		Integer loggedUser = (Integer)request.getSession().getAttribute("staffMember");
		if(loggedUser != null && loggedUser == auth.getId()) {
			return new ResponseEntity<String>("User already logged in!", HttpStatus.FORBIDDEN);
		}
		
		if (loginService.loginAsUniversityStaffMember(auth.getId(), auth.getPass()))
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("staffMember", auth.getId());
			return new ResponseEntity<>("Logged in successfully!",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.FORBIDDEN);
		
	}
	@PostMapping("/commitee/auth")	
	public ResponseEntity<String> commiteeLogin(@RequestBody Authorization auth , HttpServletRequest request) {
		
		Integer loggedUser = (Integer)request.getSession().getAttribute("commitee");
//<<<<<<< HEAD
//		if(loggedUser != null && loggedUser == userName) {
//=======
//		System.out.println(loggedUser);
		if(loggedUser != null && loggedUser == auth.getId()) {
//>>>>>>> branch 'main' of https://github.com/CapgSelection/UniversityCourseSelectionG1.git
			return new ResponseEntity<String>("User already logged in!", HttpStatus.FORBIDDEN);
		}
//<<<<<<< HEAD
//=======
	
		if (loginService.loginAsAdmissionCommiteeMember(auth.getId(), auth.getPass()))
//>>>>>>> branch 'main' of https://github.com/CapgSelection/UniversityCourseSelectionG1.git
		
//		if (loginService.loginAsAdmissionCommiteeMember(userName, password))
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("commitee", auth.getId());
			return new ResponseEntity<>("Logged in successfully!",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.FORBIDDEN);
		
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutAll(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		if(!session.getAttributeNames().asIterator().hasNext())
			return new ResponseEntity<String>("You are already logged out!",HttpStatus.METHOD_NOT_ALLOWED);
		
		try {
		session.invalidate();
		return new ResponseEntity<String>("Logged out Successfully!",HttpStatus.OK);
		}
		catch(IllegalStateException ise) {
			return new ResponseEntity<String>("You are already logged out!",HttpStatus.METHOD_NOT_ALLOWED);
		}
		
	}

}
