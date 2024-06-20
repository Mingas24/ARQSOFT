package com.example.promotionmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.promotionmanagement.domain.entities.Promotion;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Optional<Promotion> getPromotionByPromotionId(Long promotionId);

    @Query(value = "SELECT * " +
            "FROM Promotion p " +
            "WHERE p.shop_id = :shopId " +
            "AND p.sandwich_id = :sandwichId " +
            "AND p.start_date < CURDATE() " +
            "AND p.end_date > CURDATE() ",
            nativeQuery = true)
    Optional<Promotion> getActiveLocalPromotionBySandwich(@Param("sandwichId") long sandwichId, @Param("shopId") long shopId);

    @Query(value = "SELECT * " +
            "FROM Promotion p " +
            "WHERE p.sandwich_id = :sandwichId " +
            "AND p.start_date < CURDATE() " +
            "AND p.end_date > CURDATE() ",
            nativeQuery = true)
    Optional<Promotion> getActiveGlobalPromotionBySandwich(@Param("sandwichId") long sandwichId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Promotion SET Promotion.percentage = :percentage WHERE Promotion.promotion_id = :id",
            nativeQuery = true)
    int updatePercentage(@Param("id") Long promotionId, @Param("percentage") float percentage);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Promotion SET Promotion.start_date = :startDate WHERE Promotion.promotion_id = :id",
            nativeQuery = true)
    int updateStartDate(@Param("id") Long promotionId, @Param("startDate") String startDate);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Promotion SET Promotion.end_date = :endDate WHERE Promotion.promotion_id = :id",
            nativeQuery = true)
    int updateEndDate(@Param("id") Long promotionId, @Param("endDate") String endDate);
}

