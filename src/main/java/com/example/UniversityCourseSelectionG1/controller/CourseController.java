package com.example.UniversityCourseSelectionG1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UniversityCourseSelectionG1.entities.Course;
import  com.example.UniversityCourseSelectionG1.exception.NotFoundException;

import com.example.UniversityCourseSelectionG1.service.CourseService;

@RestController
@RequestMapping("uni/course")
public class CourseController {

	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/view/{courseId}")
	public ResponseEntity<Course> viewCourseById(@PathVariable("courseId") int courseId) {
		
		Course viewCourse = courseService.viewCourse(courseId);
		return new ResponseEntity<Course>(viewCourse, HttpStatus.OK); 
	}
	
	@GetMapping("/view/all")
	public ResponseEntity<List<Course>> viewAllCourses(){
		
		List<Course> courses = courseService.viewAllCourses();
		
		if(!courses.isEmpty()) {
			return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
		}		else {
			throw new NotFoundException("Sorry!!!, no courses available..");
	}	
	}

}
