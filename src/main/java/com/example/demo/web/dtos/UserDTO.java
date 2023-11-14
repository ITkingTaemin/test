package com.example.demo.web.dtos;

import com.example.demo.models.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 저장요청DTO")
public class UserDTO extends User {
    public User toEntity()
    {
        return User.builder()
                .name(this.getName())
                .age(this.getAge())
                .build();
    }


}
