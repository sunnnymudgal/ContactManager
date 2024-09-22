package com.cm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserForm {
    @NotNull(message = "username is required")
    @Size(min=3, message = "min 3 character is required")
    private String name;
    @Email(message = "invailed email address")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 6,message = "min 6 character is required")
    private String password;
    @NotBlank
    private String about;
    @Size(min = 8,max = 12, message = "invalid phone number")
    private String phoneNumber;
}
