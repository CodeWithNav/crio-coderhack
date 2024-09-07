package com.nav.coderhack.services;

import com.nav.coderhack.dto.UserRegisterDto;
import com.nav.coderhack.entity.UserEntity;
import com.nav.coderhack.exceptions.AlreadyExistException;
import com.nav.coderhack.exceptions.NotFoundException;
import com.nav.coderhack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {

        return userRepository.findAll(Sort.by(Sort.Direction.DESC,"score"));
    }

    @Override
    public UserEntity getUserDetails(String userId) {
       Optional<UserEntity> user=  userRepository.findById(userId);
       return user.orElseThrow(()->new NotFoundException("User not found with given id"));
    }

    @Override
    public UserEntity registerUser(UserRegisterDto user) {
        Optional<UserEntity> alreadyUser =  userRepository.findById(user.getUserId());
        if(alreadyUser.isPresent()){
            throw new AlreadyExistException("User already exist with given id");
        }
        UserEntity newUser = new UserEntity(
                user.getUserId(),
                user.getUsername(),
                new HashSet<>(),
                0
        );
        return userRepository.save(newUser);

    }

    @Override
    public UserEntity updateScore(int newScore,String userId) {
        UserEntity user = getUserDetails(userId);

        user.setScore(newScore);
        String badge = getBadge(newScore);
        if(badge != null){
            user.getBadges().add(badge);
        }
        return  userRepository.save(user);
    }

    @Override
    public void unregisterUser(String userId) {
        getUserDetails(userId);
        userRepository.deleteById(userId);
    }

    /*
     1 <= Score < 30 -> Code Ninja
    30 <= Score < 60 -> Code Champ
    60 <= Score <= 100 -> Code Master
     */
    private String getBadge(int score){
        if(score <= 0) return null;
        if(score < 30) return "Code Ninja";
        if(score < 60) return "Code Champ";
        if(score <= 100) return  "Code Master";
        return  null;
    }
}
