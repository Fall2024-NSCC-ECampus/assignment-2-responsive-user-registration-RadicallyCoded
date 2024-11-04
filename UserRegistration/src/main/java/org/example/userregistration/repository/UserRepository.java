package org.example.userregistration.repository;

import org.example.userregistration.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUsernameAndPassword(String username, String password);
    UserModel findByUsername(String username);
    Optional<UserModel> findFirstByUsernameAndEmail(String username, String email);

}
