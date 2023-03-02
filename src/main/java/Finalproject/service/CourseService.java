package Finalproject.service;

import Finalproject.dto.CourseCreateDto;
import Finalproject.dto.CourseInfoDto;
import Finalproject.entity.Course;

import java.util.List;

public interface CourseService {

    CourseInfoDto createCourse(CourseCreateDto courseCreateDto);

    List<CourseInfoDto> getAllCourses();

    CourseInfoDto findCourseByName(String name);

}
