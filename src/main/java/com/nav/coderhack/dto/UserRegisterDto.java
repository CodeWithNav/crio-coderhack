package com.nav.coderhack.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    @NotBlank
    @NotNull
    private  String userId;

    @NotBlank
    @NotNull
    @Length(min = 3,max = 50)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only letters (a-z, A-Z)")
    private String username;

}
