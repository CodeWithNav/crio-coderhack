package com.nav.coderhack.services;

import com.nav.coderhack.dto.UserDetailsDto;
import com.nav.coderhack.dto.UserRegisterDto;
import com.nav.coderhack.entity.UserEntity;

import java.util.List;

public interface UserService {



    List<UserEntity> getAllUsers();

    UserEntity getUserDetails(String userId);
    UserEntity registerUser(UserRegisterDto user);
    UserEntity updateScore(int newScore,String userId);
    void unregisterUser(String userId);
}
