package com.nav.coderhack.controller;



import com.nav.coderhack.dto.UpdateScoreDto;
import com.nav.coderhack.dto.UserRegisterDto;
import com.nav.coderhack.entity.UserEntity;
import com.nav.coderhack.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Log4j2
public class UserController {


    final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers(){
        return  ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") String userId){
        return  ResponseEntity.ok(userService.getUserDetails(userId));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> unregisterUser(@PathVariable("id") String userId){
        userService.unregisterUser(userId);
        return  ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<UserEntity> registerUser(@Valid @RequestBody UserRegisterDto user){
        UserEntity createdUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }



    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateScore(
            @PathVariable(name = "id") String userId,
            @Valid @RequestBody UpdateScoreDto score){
        UserEntity updatedUser = userService.updateScore(score.getScore(),userId);
        log.debug(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

}
