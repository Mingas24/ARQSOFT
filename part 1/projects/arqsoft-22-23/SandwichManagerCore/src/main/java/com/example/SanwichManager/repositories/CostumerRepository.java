package com.example.SanwichManager.repositories;

import com.example.SanwichManager.domain.entities.Costumer;
import com.example.SanwichManager.domain.valueObjects.CostumerNIF;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    Optional<Costumer> findCostumerByCostumerEmail(Email costumerEmail);

    Optional<Costumer> findCostumerByCostumerNIF(CostumerNIF costumerNIF);

}
