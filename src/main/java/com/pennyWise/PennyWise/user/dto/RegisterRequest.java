package com.pennyWise.PennyWise.user.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String name;
    @Email @NotBlank
    private String email;
    @NotBlank @Size(min = 6)
    private String password;
}
