package org.example.userregistration.service;

import org.example.userregistration.model.UserModel;
import org.example.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String firstName, String lastName, String email, String username, String password) {
        UserModel userModel = new UserModel();

        if (username == null || password == null) {
            return null;
        } else {
            if(userRepository.findFirstByUsernameAndEmail(username, email).isPresent()) {
                System.out.println("User already exists");
                return null;
            }
            String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            userModel.setFirstName(firstName);
            userModel.setLastName(lastName);
            userModel.setUsername(username);
            userModel.setPassword(encodedPassword);
            userModel.setEmail(email);

            return userRepository.save(userModel);
        }
    }

    public boolean authenticateUser(String username, String password) {
        UserModel userModel = userRepository.findByUsername(username);
        boolean isPasswordMatch = BCrypt.checkpw(password, userModel.getPassword());
        if(isPasswordMatch) {
            return true;
        } else {
            return false;
        }
    }

}