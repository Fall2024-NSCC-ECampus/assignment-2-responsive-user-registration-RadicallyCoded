package org.example.userregistration.service;

import org.example.userregistration.model.UserModel;
import org.example.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String name, String email, String username, String password) {
        if (username == null || password == null) {
            return null;
        } else {
            if(userRepository.findFirstByUsernameAndEmail(username, email).isPresent()) {
                System.out.println("User already exists");
                return null;
            }
            UserModel userModel = new UserModel();
            userModel.setUsername(username);
            userModel.setPassword(password);
            userModel.setName(name);
            userModel.setEmail(email);

            return userRepository.save(userModel);
        }
    }

    public UserModel authenticateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
