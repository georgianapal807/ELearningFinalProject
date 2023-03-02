package Finalproject.service;

import Finalproject.dto.CourseInfoDto;
import Finalproject.dto.UserCreateDto;
import Finalproject.dto.UserInfoDto;
import Finalproject.entity.User;

import java.util.List;

public interface UserService {


//    UserInfoDto findByUserName(String name); DO NOT USE!

    User findUserByEmail(String email);

    void saveUser(UserCreateDto UserCreateDto);

    List<UserInfoDto> getAllUsers();

    UserInfoDto enrollUserInACourse(String courseName, String username);

    List<CourseInfoDto> getAllCoursesFromUsername(String username);
}
