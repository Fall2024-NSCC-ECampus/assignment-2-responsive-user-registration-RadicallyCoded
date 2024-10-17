package org.example.userregistration.repository;

import org.example.userregistration.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUsernameAndPassword(String username, String password);

    Optional<UserModel> findFirstByUsernameAndEmail(String username, String email);
}
