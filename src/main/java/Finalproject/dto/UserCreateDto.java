package Finalproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "Please inset your first name")
    private String firstName;
    @NotBlank(message = "Please inset your last name")
    private String lastName;
    private String phoneNumber;

    @Length(min = 4, message = "Password must have at least 4 characters!")
    private String password;

    @Email(message = "Invalid email")
    @NotBlank(message = "Contact information required")
    private String email;

}
