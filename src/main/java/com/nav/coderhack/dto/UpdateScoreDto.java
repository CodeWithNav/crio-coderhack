package com.nav.coderhack.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateScoreDto {

    @Min(value = 1,message = "Score should be greater than 0.")
    @Max(value = 100,message = "Score should be less than or equal to 100.")
    @NotNull(message = "Score is required")
    private int score;
}
