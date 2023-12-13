package br.com.senior.delivery.domain.order;

import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    public static Specification<Order> filterByOrderStatus(OrderStatus orderStatusParam) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), orderStatusParam);
    }

}
