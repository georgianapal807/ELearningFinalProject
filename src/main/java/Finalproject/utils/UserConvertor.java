package Finalproject.utils;

import Finalproject.entity.User;
import Finalproject.dto.UserCreateDto;
import Finalproject.dto.UserInfoDto;

import java.time.LocalDate;

public class UserConvertor {

    public static User createDtoToEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastname(userCreateDto.getLastName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setEmail(userCreateDto.getEmail());
        user.setCreatedOn(LocalDate.now());

        return user;
    }

    public static UserInfoDto entityToInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setFirstName(user.getFirstName());
        userInfoDto.setLastName(user.getLastname());
        userInfoDto.setId(user.getId());
        userInfoDto.setPhoneNumber(user.getPhoneNumber());
        userInfoDto.setCreatedOn(user.getCreatedOn());
        return userInfoDto;
    }
}
