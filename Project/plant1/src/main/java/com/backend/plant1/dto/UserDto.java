package com.backend.plant1.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserDto {

        private int user_id;
        private String name;
        private String email;
        private String password;
        private String about;
        private String gender;
        private String phone;
        private Date date;
    }
