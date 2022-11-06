package com.example.UniversityCourseSelectionG1.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.entities.AdmissionStatus;
import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.repository.ApplicantRepository;
import com.example.UniversityCourseSelectionG1.repository.CourseRepository;

import org.junit.jupiter.api.Assertions;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ApplicantServiceImplTest {

	@Mock
	private ApplicantRepository apprepo;
	
	@Mock
	private CourseRepository courserepo;

	@InjectMocks
	private ApplicantServiceImpl appservice;

	static Applicant app1 = new Applicant();
	static Applicant app2 = new Applicant();
	static Applicant app3 = new Applicant();

	@BeforeAll
	static void initMethod() {
		app1.setApplicantId(1);
		app1.setAdmission(new Admission());
		app2.setApplicantId(2);
		app2.setAdmission(new Admission());
		app3.setApplicantId(3);
		app3.setAdmission(new Admission());
		app3.setStatus(AdmissionStatus.CONFIRMED);

	}

	@Test
	void testAddApplicant_success() {
		Course course= new Course();
		course.setCourseId(1);
		Mockito.when(apprepo.save(app1)).thenReturn(app1);
		Mockito.when(courserepo.existsById(1)).thenReturn(true);
		Mockito.when(courserepo.findById(1)).thenReturn(Optional.ofNullable(course));
		app1.getAdmission().setCourseId(1);
		
		

		assertEquals(app1, appservice.applyForCourse(app1));
	}

	@Test
	void testUpdateApplicant_success() {
		Mockito.when(apprepo.existsById(2)).thenReturn(true);
		Mockito.when(courserepo.existsById(1)).thenReturn(true);
		Course course= new Course();
		course.setCourseId(1);
		
		app2.getAdmission().setCourseId(1);
		Mockito.when(apprepo.save(app2)).thenReturn(app2);
		Mockito.when(courserepo.findById(1)).thenReturn(Optional.ofNullable(course));
		assertEquals(app2, appservice.updateApplicant(app2));
	}

	@Test
	void testDeleteApplicant_success() {
		Mockito.when(apprepo.existsById(1)).thenReturn(true);
		boolean success = true;
		try {
			appservice.delApplicant(1);
		} catch (Exception e) {
			success = false;
		}
		assertEquals(false, success);
	}

	@Test
	void testViewApplicant_success() {
		Mockito.when(apprepo.existsById(1)).thenReturn(true);
		Mockito.when(apprepo.findById(1)).thenReturn(Optional.ofNullable(app1));
		assertTrue(appservice.getById(1).isPresent());

	}

	@Test
	void testViewAllApplicants_success() {
		List<Applicant> applicants = new ArrayList<>();
		applicants.add(app1);
		applicants.add(app2);
		
		Mockito.when(apprepo.viewAllApplicants()).thenReturn(applicants);

		Assertions.assertIterableEquals(applicants, apprepo.viewAllApplicants());
	}

	@Test
	void testAddApplicant_failure() {

		assertThrows(NotFoundException.class, () -> {
			appservice.applyForCourse(app3);
		});
	}

	@Test
	void testUpdateApplicant_failure() {

		assertThrows(NotFoundException.class, () -> {
			appservice.applyForCourse(app3);
		});
	}
	
	@Test
	void testDeleteApplicant_failure() {
		Applicant app4=new Applicant();
		app4.setApplicantId(4);
		Mockito.when(apprepo.existsById(4)).thenReturn(false);
		assertThrows(NotFoundException.class,()->appservice.delApplicant(4));
	}
	
	@Test
	void testViewApplicant_failure() {
		Mockito.when(apprepo.existsById(4)).thenReturn(false);
		assertThrows(NotFoundException.class,()->appservice.getById(4));
	}
	
	@Test
	void testViewAllApplicants_failure() {
		List<Applicant> list=null;
		Mockito.when(apprepo.viewAllApplicants()).thenReturn(list);
		assertEquals(list, appservice.viewAllApplicants());
	}

}
