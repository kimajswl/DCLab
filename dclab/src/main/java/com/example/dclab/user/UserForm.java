package com.example.dclab.user;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm { // 로그인시 입력받을 값들

    @Column(length = 25, unique = true)
    private String username;

    @Column(length = 100, unique = true)
    private String password;

    @Column(length = 50)
    private String email;
}
