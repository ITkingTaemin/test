package com.example.demo.web.dtos;

import com.example.demo.models.User;

public class UserDTO extends User {
    public User toEntity()
    {
        return User.builder()
                .name(this.getName())
                .age(this.getAge())
                .build();
    }


}
