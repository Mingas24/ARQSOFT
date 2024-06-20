package com.example.UserManagement.repository;

import com.example.UserManagement.domain.entities.Manager;
import com.example.UserManagement.domain.valueObjects.user.Email;
import com.example.UserManagement.domain.valueObjects.user.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    Optional<Manager> findByUsername(Username username);
    Optional<Manager> findByEmail(Email email);
}
