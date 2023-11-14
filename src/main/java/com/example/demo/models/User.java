package com.example.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "사용자 ID")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "사용자 이름")
    private String name;

    @Column(nullable = false)
    @Schema(description = "사용자 나이")
    private int age;

    @Builder
    public User(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
}
