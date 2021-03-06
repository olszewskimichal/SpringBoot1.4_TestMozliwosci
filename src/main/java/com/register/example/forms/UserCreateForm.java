package com.register.example.forms;

import com.register.example.validators.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserCreateForm {

    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    @NotBlank
    @Size(min = 3, max = 30)
    private String lastName;

    @ValidEmail
    @Size(max = 50)
    private String email;

    @NotBlank
    private String login;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{3,}$")
    private String passForm;

    private String confirmPassword;


    public UserCreateForm(String name, String lastName, String email, String login, String password, String confirmPassword) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.passForm = password;
        this.confirmPassword = confirmPassword;
    }
}
