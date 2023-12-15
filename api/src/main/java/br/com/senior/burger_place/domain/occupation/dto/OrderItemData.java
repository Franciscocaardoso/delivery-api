package br.com.senior.burger_place.domain.occupation.dto;

import br.com.senior.burger_place.domain.occupation.OrderItem;
import br.com.senior.burger_place.domain.occupation.OrderItemStatus;

public record OrderItemData(
        Long id,
        Long productId,
        String productDescription,
        Double itemValue,
        Integer amount,
        OrderItemStatus status,
        String observation
) {
    public OrderItemData(OrderItem orderItem) {
        this(
                orderItem.getId(),
                orderItem.getProduct().getId(),
                orderItem.getProduct().getDescription(),
                orderItem.getItemValue(),
                orderItem.getAmount(),
                orderItem.getStatus(),
                orderItem.getObservation()
        );
    }
}
