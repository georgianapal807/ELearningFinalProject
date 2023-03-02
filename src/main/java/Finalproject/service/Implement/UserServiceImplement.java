package Finalproject.service.Implement;

import Finalproject.dto.CourseInfoDto;
import Finalproject.entity.Course;
import Finalproject.entity.Role;
import Finalproject.entity.User;
import Finalproject.exception.course.CourseNotFoundException;
import Finalproject.repository.CourseRepository;
import Finalproject.repository.RoleRepository;
import Finalproject.repository.UserRepository;
import Finalproject.service.UserService;
import Finalproject.utils.CourseConvertor;
import Finalproject.utils.UserConvertor;
import Finalproject.dto.UserCreateDto;
import Finalproject.dto.UserInfoDto;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplement(UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }
//    !!DO NOT USE WE CHANGED THE LOGIC HERE
//    @Override
//    public UserInfoDto findByUserName(String name) {
//        User user = userRepository.findByUsername(name);
//        return UserConvertor.entityToInfoDto(user);
//    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    @Transactional
    public void saveUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastname(userCreateDto.getLastName());
        user.setEmail(userCreateDto.getEmail());
        //encrypt the password once we integrate spring security
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setCreatedOn(LocalDate.now());
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Transactional
    public Role checkRoleExist() {
        // in mod normal aceasta metoda trebuie sa verifice un rol deja existent in baza de date! Eu il adaug pentru simplitate, chiar daca nu exista!
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public List<UserInfoDto> getAllUsers() {
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
        userRepository.findAll().forEach((user -> userInfoDtoList.add(UserConvertor.entityToInfoDto(user))));
        return userInfoDtoList;
    }

    @Override
    public UserInfoDto enrollUserInACourse(String courseName, String email) {
        Course course = courseRepository.findCourseByName(courseName)
                .orElseThrow(() -> new CourseNotFoundException("Course with name " + courseName + " not found"));
        User user = userRepository.findUserByEmail(email);
        List<Course> userCourses = user.getCourses();
        if (userCourses.contains(course)) {
            throw new IllegalArgumentException("User " + email + " is already enrolled in course " + courseName);
        }
        userCourses.add(course);
        user.setCourses(userCourses);
        return UserConvertor.entityToInfoDto(userRepository.save(user));
    }

    @Override
    public List<CourseInfoDto> getAllCoursesFromUsername(String email) {
        User user = userRepository.findUserByEmail(email);
        List<Course> usernameCourses = user.getCourses();
        List<CourseInfoDto> response = new ArrayList<>();
        usernameCourses.forEach(course -> response.add(CourseConvertor.entityToInfoDto(course)));
        return response;
    }
}