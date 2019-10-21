package com.ga.dao;

import java.util.List;

import com.ga.entity.Course;

public interface CourseDao {
	
	public Course createCourse(Course course);

    	public List<Course> listCourses();

}