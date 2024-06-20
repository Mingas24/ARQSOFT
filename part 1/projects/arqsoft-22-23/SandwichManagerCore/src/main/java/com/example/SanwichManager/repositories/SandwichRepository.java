package com.example.SanwichManager.repositories;

import com.example.SanwichManager.domain.entities.Sandwich;
import com.example.SanwichManager.domain.valueObjects.shared.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SandwichRepository extends JpaRepository<Sandwich, Long> {

    Optional<Sandwich> getSandwichBySandwichId(Long sandwichId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Sandwich SET Sandwich.price = :price WHERE Sandwich.id = :id", nativeQuery = true)
    int updateSandwichPrice(@Param("id") Long sandwichID,
                                      @Param("price") String price);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Sandwich SET Sandwich.description = :description WHERE Sandwich.id = :id", nativeQuery = true)
    int updateSandwichDescription(@Param("id") Long sandwichID,
                            @Param("description") String description);

}
