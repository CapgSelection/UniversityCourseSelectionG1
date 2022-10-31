package com.example.UniversityCourseSelectionG1.controller;

import java.util.List;

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

import com.example.UniversityCourseSelectionG1.entities.UniversityStaffMember;
import com.example.UniversityCourseSelectionG1.service.UniversityStaffMemberService;

@RestController
@RequestMapping("/uni/staff")
public class UniversityStaffController {
	
	private UniversityStaffMemberService usmService;
	
	@PostMapping("/add")
	public ResponseEntity<UniversityStaffMember> addStaff(@RequestBody UniversityStaffMember usm) {
		UniversityStaffMember savedUSM = usmService.addStaff(usm);
		return new ResponseEntity<>(savedUSM, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UniversityStaffMember> updateStaff(@RequestBody UniversityStaffMember usm) {
		UniversityStaffMember updatedUSM = usmService.updateStaff(usm);
		return new ResponseEntity<>(updatedUSM, HttpStatus.OK);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<UniversityStaffMember> viewStaffById(@PathVariable int id) {
		UniversityStaffMember fetchedUSM = usmService.viewStaff(id);
		return new ResponseEntity<>(fetchedUSM, HttpStatus.FOUND);
	}
	
	@GetMapping("/view/all")
	public ResponseEntity<List<UniversityStaffMember>> viewAllStaff() {
		List<UniversityStaffMember> fetchedUSMList = usmService.viewAllStaffs();
		return new ResponseEntity<>(fetchedUSMList, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStaffById(@PathVariable int id) {
		usmService.removeStaff(id);
		return new ResponseEntity<String>("Staff with id: "+id+" deleted successfully!", HttpStatus.OK);
	}
	
	
	
	
}
