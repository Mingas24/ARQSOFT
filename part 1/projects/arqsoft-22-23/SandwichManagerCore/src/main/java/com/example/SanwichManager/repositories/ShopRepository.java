package com.example.SanwichManager.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.SanwichManager.domain.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Query(value = "SELECT s FROM Shop s WHERE s.shop_id = ?1")
    Optional<Shop> getShopByShopId(Long shopId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Shop s WHERE (s.shop_id =:id)", nativeQuery = true)
    void deleteByShopId(@Param("id") Long shopId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Shop SET Shop.promotion_application = :applicationType WHERE Shop.shop_id = :id", nativeQuery = true)
    int updatePromotionApplicatioType(@Param("id") Long shopId,
                                      @Param("applicationType") int applicationType);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Shop SET Shop.address = :address WHERE Shop.shop_id = :id", nativeQuery = true)
    int updateAddress(@Param("id") Long shopId,
                      @Param("address") String address);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Shop SET Shop.designation = :designation WHERE Shop.shop_id = :id", nativeQuery = true)
    int updateDesignation(@Param("id") Long shopId,
                          @Param("designation") String designation);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Shop SET Shop.email = :email WHERE Shop.shop_id = :id", nativeQuery = true)
    int updateEmail(@Param("id") Long shopId,
                    @Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Shop_Schedule s SET s.date = :date WHERE s.shop_id = :shopId AND s.day = :day", nativeQuery = true)
    int updateSchedule(@Param("shopId") Long shopId,
                       @Param("day") String day,
                       @Param("date") String date);
}
