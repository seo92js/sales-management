package com.seojs.salesmanagement.domain.order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findByCustomerId(Long id);
}
