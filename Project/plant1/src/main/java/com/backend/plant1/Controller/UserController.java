package com.backend.plant1.Controller;


import java.util.Date;

import com.backend.plant1.Service.UserService;
import com.backend.plant1.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("/user")

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
    {
        Date date = new Date();
        userDto.setDate(date);
        UserDto save = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(save, HttpStatus.CREATED);
    }
}