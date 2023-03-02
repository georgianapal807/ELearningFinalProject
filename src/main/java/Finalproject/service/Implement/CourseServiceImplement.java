package Finalproject.service.Implement;

import Finalproject.exception.course.CourseNotFoundException;
import Finalproject.utils.CourseConvertor;
import Finalproject.dto.CourseCreateDto;
import Finalproject.dto.CourseInfoDto;
import Finalproject.entity.Course;
import Finalproject.repository.CourseRepository;
import Finalproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImplement implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImplement(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseInfoDto createCourse(CourseCreateDto courseCreateDto) {
        Course course = courseRepository.save(CourseConvertor.createDtoToEntity(courseCreateDto));
        CourseInfoDto courseInfoDto = CourseConvertor.entityToInfoDto(course);
        return courseInfoDto;
    }

    @Override
    public List<CourseInfoDto> getAllCourses() {
        List<CourseInfoDto> courseInfoDto = new ArrayList<>();
        courseRepository.findAll().forEach((course -> courseInfoDto.add(CourseConvertor.entityToInfoDto(course))));
        return courseInfoDto;
    }

    @Override
    public CourseInfoDto findCourseByName(String name) {
        Course course;
        course = courseRepository.findCourseByName(name)
                .orElseThrow(() -> new CourseNotFoundException("Course with name " + name + " not found"));
        return CourseConvertor.entityToInfoDto(course);
    }


}
