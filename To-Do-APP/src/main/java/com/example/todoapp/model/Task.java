package com.example.todoapp.model;

import com.example.todoapp.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Getter
    @Setter
    @Builder

    public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String createdAt;
    private String updatedAt;
    private String completedAt;
    private Long userId;

    }
