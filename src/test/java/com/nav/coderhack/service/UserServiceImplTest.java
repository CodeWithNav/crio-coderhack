package com.nav.coderhack.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nav.coderhack.dto.UserRegisterDto;
import com.nav.coderhack.entity.UserEntity;
import com.nav.coderhack.exceptions.AlreadyExistException;
import com.nav.coderhack.exceptions.NotFoundException;
import com.nav.coderhack.repository.UserRepository;
import com.nav.coderhack.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() {
        List<UserEntity> mockUsers = Arrays.asList(
                new UserEntity("1", "Alice", new HashSet<>(), 70),
                new UserEntity("2", "Bob", new HashSet<>(), 50)
        );

        when(userRepository.findAll(Sort.by(Sort.Direction.DESC, "score"))).thenReturn(mockUsers);

        List<UserEntity> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("Alice", result.getFirst().getUserName());
        verify(userRepository, times(1)).findAll(Sort.by(Sort.Direction.DESC, "score"));
    }

    @Test
    public void testGetUserDetailsSuccess() {
        UserEntity userEntity = new UserEntity("1", "Alice", new HashSet<>(), 50);
        when(userRepository.findById("1")).thenReturn(Optional.of(userEntity));

        UserEntity result = userService.getUserDetails("1");

        assertNotNull(result);
        assertEquals("Alice", result.getUserName());
        verify(userRepository, times(1)).findById("1");
    }

    @Test
    public void testGetUserDetailsNotFound() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUserDetails("1"));
        verify(userRepository, times(1)).findById("1");
    }

    @Test
    public void testRegisterUserSuccess() {
        UserRegisterDto userDto = new UserRegisterDto("1", "Alice");
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        UserEntity savedUser = new UserEntity("1", "Alice", new HashSet<>(), 0);
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserEntity result = userService.registerUser(userDto);

        assertNotNull(result);
        assertEquals("Alice", result.getUserName());
        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testRegisterUserAlreadyExists() {
        UserRegisterDto userDto = new UserRegisterDto("1", "Alice");
        when(userRepository.findById("1")).thenReturn(Optional.of(new UserEntity("1", "Alice", new HashSet<>(), 50)));

        assertThrows(AlreadyExistException.class, () -> userService.registerUser(userDto));
        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    public void testUpdateScore() {
        UserEntity existingUser = new UserEntity("1", "Alice", new HashSet<>(), 50);
        when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));

        UserEntity savedUser = new UserEntity("1", "Alice", new HashSet<>(Set.of("Code Master")), 90);
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserEntity result = userService.updateScore(90, "1");

        assertNotNull(result);
        assertEquals(90, result.getScore());
        assertTrue(result.getBadges().contains("Code Master"));
        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testUnregisterUserSuccess() {
        UserEntity userEntity = new UserEntity("1", "Alice", new HashSet<>(), 50);
        when(userRepository.findById("1")).thenReturn(Optional.of(userEntity));

        userService.unregisterUser("1");

        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(1)).deleteById("1");
    }

    @Test
    public void testUnregisterUserNotFound() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.unregisterUser("1"));
        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(0)).deleteById("1");
    }
}

