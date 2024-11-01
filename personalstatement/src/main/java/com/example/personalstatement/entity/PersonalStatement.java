package com.example.personalstatement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonalStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String intro;
    private String qualifications;
    private String company;
    private String experience;
    private String tone;
    private String generatedStatement;
}
