package com.example.OrderManagement.repository;

import com.example.OrderManagement.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM Orders o WHERE (o.costumerEmail =:email)", nativeQuery = true)
    Optional<List<Order>> findOrdersByCostumer(@Param("email") String costumerEmail);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET orders.order_status =:orderStatus WHERE orders.order_id =:id", nativeQuery = true)
    int updateOrderStatus(@Param("id") Long orderId, @Param("orderStatus") int orderStatus);

}