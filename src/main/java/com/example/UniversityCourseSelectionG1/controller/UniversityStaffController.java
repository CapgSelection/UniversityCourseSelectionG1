package com.example.UniversityCourseSelectionG1.controller;

import java.util.List;

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

import com.example.UniversityCourseSelectionG1.exception.NotLoggedInException;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.entities.UniversityStaffMember;
import com.example.UniversityCourseSelectionG1.service.CourseService;
import com.example.UniversityCourseSelectionG1.service.UniversityStaffMemberService;

@RestController
@RequestMapping("/uni/staff")
public class UniversityStaffController {
	// commit test
	@Autowired
	private UniversityStaffMemberService usmService;

	@Autowired
	private CourseService courseService;

	public boolean checkSession(HttpServletRequest request, String type) {
		HttpSession session = request.getSession(true);
		if(session.isNew() || session.getAttribute(type) == null) {
			return false;
		}
		int userId = (int) session.getAttribute(type);
		if (userId == 0) {
			return false;
		}
		return true;
	}

	@PostMapping("/add")
	public ResponseEntity<UniversityStaffMember> addStaff(@Valid @RequestBody UniversityStaffMember usm, HttpServletRequest request) {
		if(!checkSession(request, "staffMember")) {
			String port = String.valueOf(request.getServerPort());			
			throw new NotLoggedInException("Accessible to staff only. If you are a registered staff member, click http://localhost:"+port+"/login/staffMember/auth to login.");
		}
		UniversityStaffMember savedUSM = usmService.addStaff(usm);
		return new ResponseEntity<>(savedUSM, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<UniversityStaffMember> updateStaff(@Valid @RequestBody UniversityStaffMember usm, HttpServletRequest request) {
		if(!checkSession(request, "staffMember")) {
			String port = String.valueOf(request.getServerPort());
			throw new NotLoggedInException("Accessible to staff only. If you are a registered staff member, click http://localhost:"+port+"/login/staffMember/auth to login.");
		}
		if(usm == null || usm.getStaffId() == null) {
			throw new NotFoundException("Staff record or ID cannot be null!");
		}
		UniversityStaffMember updatedUSM = usmService.updateStaff(usm);
		return new ResponseEntity<>(updatedUSM, HttpStatus.OK);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<UniversityStaffMember> viewStaffById(@PathVariable int id, HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		Integer loginStaffId = null;
		
		if(!session.isNew()) {
			loginStaffId = (int)session.getAttribute("staffMember");
		}
		
		if(loginStaffId!=null && loginStaffId==id) {
			UniversityStaffMember ref = usmService.viewStaff(id);
			return new ResponseEntity<>(ref, HttpStatus.OK);
		}
		
		UniversityStaffMember ref = usmService.viewStaff(id);
		ref.setPassword("*******");
		return new ResponseEntity<>(ref, HttpStatus.OK);	
	}

	@GetMapping("/view/all")
	public ResponseEntity<List<UniversityStaffMember>> viewAllStaff() {
		
		
		List<UniversityStaffMember> ref = usmService.viewAllStaffs();
		ref.forEach(s->s.setPassword("*******"));
		return new ResponseEntity<>(ref, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStaffById(@PathVariable int id, HttpServletRequest request) {
		if(!checkSession(request, "staffMember")) {
			String port = String.valueOf(request.getServerPort());
			throw new NotLoggedInException("Accessible to staff only. If you are a registered staff member, click http://localhost:"+port+"/login/staffMember/auth to login.");
		}
		usmService.removeStaff(id);
		return new ResponseEntity<String>("Staff with id: " + id + " deleted successfully!", HttpStatus.OK);
	}

	@PostMapping("/course/add")
	public ResponseEntity<Course> addCourse(@RequestBody Course course, HttpServletRequest request) {
		if(!checkSession(request, "staffMember")) {
			String port = String.valueOf(request.getServerPort());
			throw new NotLoggedInException("Accessible to staff only. If you are a registered staff member, click http://localhost:"+port+"/login/staffMember/auth to login.");
		}
		Course savedCourse = courseService.addCourse(course);
		return new ResponseEntity<>(savedCourse, HttpStatus.OK);
	}

	@PutMapping("/course/update")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course, HttpServletRequest request) {
		if(!checkSession(request, "staffMember")) {
			String port = String.valueOf(request.getServerPort());
			throw new NotLoggedInException("Accessible to staff only. If you are a registered staff member, click http://localhost:"+port+"/login/staffMember/auth to login.");
		}
		Course updatedourse = courseService.updateCourse(course);
		return new ResponseEntity<>(updatedourse, HttpStatus.OK);
	}

	@DeleteMapping("/course/delete/{id}")
	public ResponseEntity<String> deleteCourseById(@PathVariable int id, HttpServletRequest request) {
		if(!checkSession(request, "staffMember")) {
			String port = String.valueOf(request.getServerPort());
			throw new NotLoggedInException("Accessible to staff only. If you are a registered staff member, click http://localhost:"+port+"/login/staffMember/auth to login.");
		}
		courseService.removeCourse(id);
		return new ResponseEntity<>("Course with id: " + id + " deleted successfully!", HttpStatus.OK);
	}

}
