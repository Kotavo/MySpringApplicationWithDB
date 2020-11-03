package com.example.MySpringApplicationWithDB.dto;

import lombok.Data;

@Data
public class AuthenticationDto {
    private String mail;
    private String password;
}
