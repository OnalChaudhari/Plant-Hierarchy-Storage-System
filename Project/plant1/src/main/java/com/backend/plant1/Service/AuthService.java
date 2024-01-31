package com.backend.plant1.Service;

import com.backend.plant1.Entities.User;
import com.backend.plant1.Repository.UserRepo;
import com.backend.plant1.dto.RootDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    public boolean authenticate(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Check if the password matches
            return user.getPassword().equals(password);
        }

        // User not found by email
        return false;
    }
//    public boolean authenticate(String email, String password) {
//        Optional<User> userOptional = userRepo.findByEmail(email);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            // Check if the password matches
//            return user.getPassword().equals(password);
//        }
//
//        // User not found by email
//        return false;
//    }
}
