package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.web.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;

    public ResponseEntity<List<User>> getUsers()
    {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    public ResponseEntity<List<User>> createUsers(List<UserDTO> userDtos) {
        List<User> userEntities = userDtos.stream().map(UserDTO::toEntity).collect(Collectors.toList());
        List<User> savedUsers = userRepository.saveAll(userEntities);
        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }

    public ResponseEntity<User> getUserById(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(EntityNotFoundException::new);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException nfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<User> updateUser(Long userId, UserDTO userDtos) {
        try {
            User oldUser = userRepository.findById(userId)
                    .orElseThrow(EntityNotFoundException::new);
            oldUser.setAge(userDtos.getAge());
            oldUser.setName(userDtos.getName());
            User newUser = userRepository.save(oldUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (EntityNotFoundException nfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
