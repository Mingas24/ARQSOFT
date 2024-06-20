package com.example.UserManagement.repository;

import com.example.UserManagement.domain.entities.Costumer;
import com.example.UserManagement.domain.valueObjects.costumer.CostumerNIF;
import com.example.UserManagement.domain.valueObjects.user.Email;
import com.example.UserManagement.domain.valueObjects.user.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer,Long> {

    Optional<Costumer> findByUsername(Username username);
    Optional<Costumer> findByEmail(Email email);
    Optional<Costumer> findCostumerByCostumerNIF(CostumerNIF costumerNIF);
}
