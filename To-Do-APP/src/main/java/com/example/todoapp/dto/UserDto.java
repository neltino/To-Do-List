package com.example.todoapp.dto;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


    public class UserDto {

        private String firstname;
        private String lastname;
        private String email;
        private String password;

}
