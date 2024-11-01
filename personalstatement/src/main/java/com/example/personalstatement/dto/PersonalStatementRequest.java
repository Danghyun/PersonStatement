package com.example.personalstatement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalStatementRequest {
    private String username;
    private String intro;
    private String qualifications;
    private String company;
    private String experience;
    private String tone;
}
