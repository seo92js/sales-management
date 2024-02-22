package com.seojs.salesmanagement.domain.orders;

import java.util.List;

public interface OrdersRepositoryCustom {
    List<Orders> findByCustomerId(Long id);
    List<Orders> findByCustomerIdAndOrderStatus(Long id, OrderStatus orderStatus);
}
