package Finalproject.controller;

import Finalproject.dto.CourseCreateDto;
import Finalproject.dto.CourseInfoDto;
import Finalproject.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/course")
@ControllerAdvice
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<CourseInfoDto> createCourse(@RequestBody @Valid CourseCreateDto courseCreateDto) {
        CourseInfoDto courseInfoDto = courseService.createCourse(courseCreateDto);
        return ResponseEntity.ok(courseInfoDto);
        
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<CourseInfoDto>> getAllCourses() {
        List<CourseInfoDto> allCourses = courseService.getAllCourses();
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping("/findCourseByName")
    public ResponseEntity<CourseInfoDto> findCourseByName(@RequestParam String name) {
        CourseInfoDto courseInfoDto = courseService.findCourseByName(name);

        return ResponseEntity.ok(courseInfoDto);
    }
}
