package Finalproject.dto;

import Finalproject.entity.Course;
import Finalproject.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    private String firstName;
    private String lastName;

    private Integer id;

    private String email;

    private String phoneNumber;

    private LocalDate createdOn;

}
