package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.OrderItem;
import com.ecommerce.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem createOrderItem(OrderItem orderItem) {
    	// Obtener la fecha y hora actual y restar 5 horas
	    LocalDateTime now = LocalDateTime.now();
	    
	    // Establecer las fechas de creación y actualización
	    orderItem.setCreatedAt(now);
	    orderItem.setUpdatedAt(now);
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Integer id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id " + id));
    }

    public OrderItem updateOrderItem(Integer id, OrderItem orderItemDetails) {
        OrderItem orderItem = getOrderItemById(id);
        orderItem.setProduct(orderItemDetails.getProduct());
        orderItem.setId(orderItemDetails.getId());
        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setPrice(orderItemDetails.getPrice());
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Integer id) {
        if (!orderItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("OrderItem not found with id " + id);
        }
        orderItemRepository.deleteById(id);
    }
}
