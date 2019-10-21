package com.ga.service;

import java.util.List;

import com.ga.entity.Course;

public interface CourseService {

	public Course createCourse(Course course);

    	public List<Course> listCourses();
}