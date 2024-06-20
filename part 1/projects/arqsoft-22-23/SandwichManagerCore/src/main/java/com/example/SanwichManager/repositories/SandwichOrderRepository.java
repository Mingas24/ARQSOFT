package com.example.SanwichManager.repositories;

import com.example.SanwichManager.domain.entities.SandwichOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SandwichOrderRepository extends JpaRepository<SandwichOrder, Long> {

}