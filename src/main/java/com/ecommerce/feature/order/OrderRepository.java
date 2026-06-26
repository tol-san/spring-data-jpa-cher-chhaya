package com.ecommerce.feature.order;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.isDeleted = true WHERE o.id = :id")
    void softDeleteById(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status = true WHERE o.id = :id")
    void setPaymentStatus(@Param("id") UUID id);
}
