package com.backend.plant1.Service;

import com.backend.plant1.Entities.User;
import com.backend.plant1.Repository.UserRepo;
import com.backend.plant1.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPhone(userDto.getPhone());
        user.setDate(userDto.getDate());

        User savedUser = this.userRepo.save(user);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setName(savedUser.getName());
        savedUserDto.setEmail(savedUser.getEmail());
        savedUserDto.setPassword(savedUser.getPassword());
        savedUserDto.setAbout(savedUser.getAbout());
        savedUserDto.setGender(savedUser.getGender());
        savedUserDto.setPhone(savedUser.getPhone());
        savedUserDto.setDate(savedUser.getDate());

        return savedUserDto;
    }
}
