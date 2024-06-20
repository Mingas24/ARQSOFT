package com.example.Shop.repositories;

import com.example.Shop.domain.entities.ShopSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScheduleRepository extends JpaRepository<ShopSchedule, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE ss.* FROM shop_schedule ss INNER JOIN shop s ON ss.shop_id = s.shop_id WHERE (s.shop_id =:id)", nativeQuery = true)
    void deleteScheduleByShopId(@Param("id") Long shopId);

}
