package com.shopping_cli.server.repository;

import com.shopping_cli.server.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository <OrderItem, Integer> {}
