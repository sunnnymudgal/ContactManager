package com.cm.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserForm {
    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;
}
