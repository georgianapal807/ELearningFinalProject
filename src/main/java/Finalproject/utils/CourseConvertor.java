package Finalproject.utils;

import Finalproject.dto.CourseCreateDto;
import Finalproject.dto.CourseInfoDto;
import Finalproject.entity.Course;

public class CourseConvertor {

    public static Course createDtoToEntity(CourseCreateDto courseCreateDto) {
        Course course = new Course();
        course.setName(courseCreateDto.getName());
        course.setShortDescription(courseCreateDto.getShortDescription());
        course.setCategory(courseCreateDto.getCategory());
        return course;
    }

    public static CourseInfoDto entityToInfoDto(Course course) {
        CourseInfoDto courseInfoDto = new CourseInfoDto();
        courseInfoDto.setName(course.getName());
        courseInfoDto.setShortDescription(course.getShortDescription());
        courseInfoDto.setId(course.getId());
        courseInfoDto.setCategory(course.getCategory());
        return courseInfoDto;
    }
}
