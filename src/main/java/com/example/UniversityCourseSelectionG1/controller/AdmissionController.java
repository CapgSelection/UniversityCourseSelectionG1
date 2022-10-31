package com.example.UniversityCourseSelectionG1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.service.AdmissionService;


@RestController
public class AdmissionController {
	
	@Autowired
	private AdmissionService admissionServ;
	
	@PostMapping("/addAdmission")
	public ResponseEntity<String> addAddmission(@RequestBody Admission admission)throws NotFoundException{
		admissionServ.addAddmission(admission);
		return new ResponseEntity<String>("Added Successfully",HttpStatus.OK);
		
	}
	@GetMapping("/admissions")
	public ResponseEntity<List<Admission>> getAdmission(){
		List<Admission> admissions=admissionServ.getAdmissions();
		return new ResponseEntity<List<Admission>>(admissions,HttpStatus.OK);
	}
	@DeleteMapping("/delAdmissions")
	public ResponseEntity<String> delAll(){
		String s=admissionServ.delAdmissions();
		return new ResponseEntity<String>(s,HttpStatus.OK);	
	}
	@DeleteMapping("delAdmissions/{id}")
	public ResponseEntity<String> delById(@PathVariable int id)throws NotFoundException {
		String s=admissionServ.delAdmissionsById(id);
		return new ResponseEntity<String>(s,HttpStatus.OK);	
	}
	
	@GetMapping("admissions/{id}")
	public ResponseEntity<List<Admission>> getAdmissionbyCourse(@PathVariable int cId){
		List<Admission> admissions=admissionServ.getAdmissionbyCourse(cId);
		return new ResponseEntity<List<Admission>>(admissions,HttpStatus.OK);
	}
	

}

