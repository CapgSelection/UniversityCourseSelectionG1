package com.example.UniversityCourseSelectionG1.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.exception.NotLoggedInException;
import com.example.UniversityCourseSelectionG1.service.AdmissionService;
import com.example.UniversityCourseSelectionG1.service.CourseService;


@RestController
public class AdmissionController {
	
	@Autowired
	private AdmissionService admissionServ;
	
	@Autowired
	private CourseService courseServ;
	
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
	
	@PostMapping("/addAdmission")
	public ResponseEntity<Admission> addAddmission(@RequestBody Admission admission,HttpServletRequest request){
		boolean valid=checkSession(request,"applicant");
		String host = String.valueOf(request.getServerPort());
		if(!valid) {
			throw new NotLoggedInException("Please Login to update details, click http://localhost:" + host
					+ "/login/applicant/auth to login");
		}
		HttpSession session=request.getSession();
		if(admission.getApplicantId()!=(int)session.getAttribute("applicant")){
			throw new NotLoggedInException("You can only update your own details");
		}
		Admission add=admissionServ.addAdmission(admission);
		return new ResponseEntity<Admission>(add,HttpStatus.OK);
		
	}
	
	@PutMapping("/update")
    public ResponseEntity<Admission> updateAdmission(@RequestBody Admission admission,HttpServletRequest request){
		
		if (admission == null || admission.getApplicantId() == 0) {
			throw new NotFoundException("Applicant or Id can't be null!");
		} 
		
		boolean valid = checkSession(request, "commitee");
		String host = String.valueOf(request.getServerPort());
		if (!valid) {
			throw new NotLoggedInException(
					"Accessible to commitee members only. If you are a registered commitee member, click http://localhost: " + host
							+ "/login/commitee/auth to login.");

		}
		
		Admission ref = admissionServ.updateAdmission(admission);
		return new ResponseEntity<>(ref,HttpStatus.OK);
	} 
	
	@GetMapping("/admissions")
	public ResponseEntity<List<Admission>> getAdmission(HttpServletRequest request){
		boolean valid = checkSession(request, "commitee");
		String host = String.valueOf(request.getServerPort());
		if (!valid) {
			throw new NotLoggedInException(
					"Accessible to commitee members only. If you are a registered commitee member, click http://localhost:" + host
							+ "/login/commitee/auth to login.");

		}
		List<Admission> admissions=admissionServ.getAdmissions();
		return new ResponseEntity<List<Admission>>(admissions,HttpStatus.OK);
	}
	@DeleteMapping("/delAdmissions")
	public ResponseEntity<String> delAll(HttpServletRequest request){
		boolean valid = checkSession(request, "commitee");
		String host = String.valueOf(request.getServerPort());
		if (!valid) {
			throw new NotLoggedInException(
					"Accessible to commitee members only. If you are a registered commitee member, click http://localhost:" + host
							+ "/login/commitee/auth to login.");

		}
		String s=admissionServ.delAdmissions();
		return new ResponseEntity<String>(s,HttpStatus.OK);	
	}
	@DeleteMapping("delAdmissions/{id}")
	public ResponseEntity<String> delById(@PathVariable int id,HttpServletRequest request){
		boolean valid = checkSession(request, "commitee");
		String host = String.valueOf(request.getServerPort());
		if (!valid) {
			throw new NotLoggedInException(
					"Accessible to commitee members only. If you are a registered commitee member, click http://localhost:" + host
							+ "/login/commitee/auth to login.");

		}
		String s=admissionServ.delAdmissionsById(id);
		return new ResponseEntity<String>(s,HttpStatus.OK);	
	}
	
	@GetMapping("/admission/{cId}")
	public ResponseEntity<List<Admission>> getAdmissionbyCourse(@PathVariable int cId,HttpServletRequest request){
		
		boolean valid = checkSession(request, "commitee");
		String host = String.valueOf(request.getServerPort());
		if (!valid) {
			throw new NotLoggedInException(
					"Accessible to commitee members only. If you are a registered commitee member, click http://localhost:" + host
							+ "/login/commitee/auth to login.");

		}
	
		
		List<Admission> admissions=admissionServ.getAdmissionbyCourse(cId);
		return new ResponseEntity<List<Admission>>(admissions,HttpStatus.OK);
	}
	
	@GetMapping("/allbyDate/{date}/{month}/{year}")
    public ResponseEntity<List<Admission>> showAllAdmissionByDate(@PathVariable String date,@PathVariable String month,@PathVariable String year,HttpServletRequest request){
	
		boolean valid = checkSession(request, "commitee");
		
		String host = String.valueOf(request.getServerPort());
		if (!valid) {
			throw new NotLoggedInException(
					"Accessible to commitee members only. If you are a registered commitee member, click http://localhost:" + host
							+ "/login/commitee/auth to login.");

		}
		DateTimeFormatter dTF = new DateTimeFormatterBuilder().parseCaseInsensitive()
	            .appendPattern("dd-MMM-yyyy")
	            .toFormatter();
		String datestring  = date+"-"+month+"-"+year;
		LocalDate localdate  = LocalDate.parse(datestring,dTF);
		List<Admission> ref = admissionServ.showAllAdmissionbyDate(localdate);
		return new ResponseEntity<>(ref, HttpStatus.OK);
	}
}

