package com.example.UniversityCourseSelectionG1.controller;
//package com.capgemini.UniversityCourseSelection.controllers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.UniversityCourseSelectionG1.entities.Admission;
import com.example.UniversityCourseSelectionG1.entities.AdmissionCommiteeMember;
import com.example.UniversityCourseSelectionG1.entities.AdmissionStatus;
import com.example.UniversityCourseSelectionG1.entities.Applicant;
import com.example.UniversityCourseSelectionG1.entities.Course;
import com.example.UniversityCourseSelectionG1.exception.NotFoundException;
import com.example.UniversityCourseSelectionG1.service.AdmissionCommiteeMemeberServiceImpl;
import com.example.UniversityCourseSelectionG1.service.ApplicantServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
class AdmissionCommitteMemberControllerTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private AdmissionCommiteeMemeberServiceImpl commiteeService;
	
	@Mock
	private ApplicantServiceImpl applicantService;

	@InjectMocks
	private AdmissionCommiteeMemberController commiteeController;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(commiteeController).build();
	}

	AdmissionCommiteeMember member1 = new AdmissionCommiteeMember(1, "member_1", "1234567890", "Adesh Sharma", "Pass@1");
	AdmissionCommiteeMember member2 = new AdmissionCommiteeMember(2, "member_2", "1234567890", "Kumar Varun", "Pass@2");
	AdmissionCommiteeMember member3 = new AdmissionCommiteeMember(3, "member_3", "1234567890", "Ramaya Ramapriya", "Pass@3");



	@Test
	void viewcommiteeMemberById_success() throws Exception {
		AdmissionCommiteeMember updated_member = new AdmissionCommiteeMember(1, "new_member", "8888", "new@1",
				"pass10");

		Mockito.when(commiteeService.viewCommiteeMember(updated_member.getAdminId())).thenReturn(updated_member);
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("commitee", 2);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/uni/commitee/view/1").session(session)
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.adminPassword", is("********")))
				.andExpect(jsonPath("$.adminName", is("new_member")));
	}
	
	
	@Test
	void viewcommiteeMemberById_failureWhenNotFound() throws Exception {
		AdmissionCommiteeMember updated_member = new AdmissionCommiteeMember(1, "new_member", "8888", "new@1",
				"pass10");

		Mockito.when(commiteeService.viewCommiteeMember(updated_member.getAdminId())).thenThrow(new NotFoundException("commitee member with id "+updated_member.getAdminId()+" not found !"));
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("commitee", 2);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/uni/commitee/view/1").session(session)
				.contentType(MediaType.APPLICATION_JSON);

		assertThatThrownBy(()-> mockMvc.perform(mockRequest))
		.hasRootCause(new NotFoundException("commitee member with id "+updated_member.getAdminId()+" not found !"));
	}
	
	
	@Test
	void viewAllCommiteeMembers_success() throws Exception {
		List<AdmissionCommiteeMember> list = new ArrayList<>(Arrays.asList(member1, member2, member3));
		
		Mockito.when(commiteeService.viewAllCommiteeMembers()).thenReturn(list);
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("commitee", 2);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/uni/commitee/viewAll").session(session)
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(mockRequest)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$[0].adminName", is("member_1")))
		.andExpect(jsonPath("$[1].adminPassword", is("*******")))
		.andExpect(jsonPath("$[2].adminContact", is("1234567890")));
	}
	
	
	@Test
	void viewAllCommiteeMembers_NoRecordsFound() throws Exception {
		List<AdmissionCommiteeMember> list = new ArrayList<>(Arrays.asList());
		
		Mockito.when(commiteeService.viewAllCommiteeMembers()).thenReturn(list);
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("commitee", 2);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/uni/commitee/viewAll").session(session)
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(mockRequest)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", is(list)));
	}

	
	@Test
	void deletecommiteeMember_success() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("commitee", 2);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/uni/commitee/delete/3").session(session)
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(mockRequest)
		.andExpect(status().isOk());
	}
	

//	@Test
//	void deletecommiteeMember_DoesNotExists() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		session.setAttribute("commitee", 2);
//		
//		Mockito.doThrow(NotFoundException.class).when(commiteeService).removecommiteeMember(30);
//
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/uni/commitee/delete/30").session(session)
//				.contentType(MediaType.APPLICATION_JSON);
//		
//		assertThatThrownBy(()-> mockMvc.perform(mockRequest)).hasRootCauseInstanceOf(NotFoundException.class);
//
//	} 
	
	
	@Test
	void testGetAdmissionResult_Confirmed() throws Exception
	{
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("commitee", 2);
		
		Admission admission = new Admission(1, 1, 1, LocalDate.of(2022, 3, 10));
		Applicant applicant = new Applicant(1, "Adesh", "9874563728", "B.Tech", 91, "pass1", admission);
		Mockito.when(applicantService.getById(applicant.getApplicantId())).thenReturn(Optional.of(applicant));		
		Mockito.when(commiteeService.provideAdmissionResult(applicant, admission)).thenReturn(AdmissionStatus.CONFIRMED);

		String updatedBody = objectWriter.writeValueAsString(applicant);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/uni/commitee/getResult/1").session(session)
				.contentType(MediaType.APPLICATION_JSON).content(updatedBody).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", is("CONFIRMED")));
	}
	
	
	@Test
	void testGetAdmissionResult_Pending() throws Exception
	{
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("commitee", 2);
		
		Admission admission = new Admission(1, 1, 1, LocalDate.of(2022, 3, 10));
//		Course course=new Course(1,"Maths","10",LocalDate.of(2022, 9, 1),LocalDate.of(2022, 9, 30),"1000",7.0D);
		Applicant applicant = new Applicant(1, "Adesh", "9475839012", "B.Tech", 5, "pass@1", admission);
		
		Mockito.when(applicantService.getById(applicant.getApplicantId())).thenReturn(Optional.of(applicant));
		Mockito.when(commiteeService.provideAdmissionResult(applicant, admission)).thenReturn(AdmissionStatus.PENDING);

		String updatedBody = objectWriter.writeValueAsString(applicant);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/uni/commitee/getResult/1").session(session)
				.contentType(MediaType.APPLICATION_JSON).content(updatedBody).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", is("PENDING")));
	}
	
	
	@Test
	void testGetAdmissionResult_Rejected() throws Exception
	{
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("commitee", 2);
		
		Admission admission = new Admission(1, 1, 1, LocalDate.of(2022, 3, 10));
		Applicant applicant = new Applicant(1, "Adesh","9485738294", "B.Tech", 91, "pass1", admission);
		Mockito.when(applicantService.getById(applicant.getApplicantId())).thenReturn(Optional.of(applicant));
		Mockito.when(commiteeService.provideAdmissionResult(applicant, admission)).thenReturn(AdmissionStatus.REJECTED);

		String updatedBody = objectWriter.writeValueAsString(applicant);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/uni/commitee/getResult/1").session(session)
				.contentType(MediaType.APPLICATION_JSON).content(updatedBody).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", is("REJECTED")));
	}
}
