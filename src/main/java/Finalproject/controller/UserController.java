package Finalproject.controller;

import Finalproject.dto.*;
import Finalproject.entity.Course;
import Finalproject.entity.User;
import Finalproject.security.UserDetailsImpl;
import Finalproject.service.CourseService;
import Finalproject.service.UserService;
import Finalproject.exception.user.UserAlreadyTakenException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/user")
@ControllerAdvice
public class UserController {

    private final UserService userService;
    private final CourseService courseService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, CourseService courseService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.courseService = courseService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        User existingUser = userService.findUserByEmail(userCreateDto.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyTakenException("Email is already in use");
        } else {
            userService.saveUser(userCreateDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).toList();

        LoginResponse response = new LoginResponse();
        response.setFullName(userDetails.getUserNameInfo());
        response.setEmail(userDetails.getUsername()); //here we will show the user email address
        response.setRole(roles.get(0));
        response.setCourses(userDetails.getUserCourses());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserInfoDto>> getAllUsers() {
        List<UserInfoDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/findUserByEmail")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/enrollInACourse")
    public ResponseEntity<UserInfoDto> enrollUserInACourse(@RequestParam String courseName, @RequestParam String email) {
        return ResponseEntity.ok(userService.enrollUserInACourse(courseName, email));

    }

    @GetMapping("/getAllCoursesFromUsername")
    public ResponseEntity<List<CourseInfoDto>> getAllCoursesFromUsername(@RequestParam String email) {
        return ResponseEntity.ok(userService.getAllCoursesFromUsername(email));
    }
}
