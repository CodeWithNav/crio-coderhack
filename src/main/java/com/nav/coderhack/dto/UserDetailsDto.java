package com.nav.coderhack.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    private  String userId;
    private  String username;
    private Set<String> badges;
    private  int score;
}
